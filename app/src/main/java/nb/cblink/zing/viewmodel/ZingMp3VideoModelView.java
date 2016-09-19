package nb.cblink.zing.viewmodel;


import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nb.cblink.zing.R;
import nb.cblink.zing.data.DownloadResourceBroadcastReceiver;
import nb.cblink.zing.data.ZingMp3Client;
import nb.cblink.zing.data.ZingMp3Factory;
import nb.cblink.zing.data.ZingResource;
import nb.cblink.zing.model.ZingMp3Video;
import nb.cblink.zing.view.activity.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nguyenbinh on 12/09/2016.
 */

public class ZingMp3VideoModelView extends BaseObservable {

    public static final String URL_IMAGE = ZingResource.getURLIMAGEZINGMP3();
    private Context context;
    public static String ID = "KEY_ID_VIDEO";
    @Bindable
    public String txtVideoName;
    @Bindable
    public String txtVideoArtist;
    @Bindable
    public String txtDuration;
    @Bindable
    public String txtType;

    @Bindable
    public int visibiLoading;
    @Bindable
    public int visibiTXTShortInfo;
    @Bindable
    public int visibiClickADPlease;
    @Bindable
    public int visibiMainContent;
    @Bindable
    public int visibiAD;
    @Bindable
    public int visibiLoadingLayout;
    @Bindable
    public int visibiButtonlickADPlease;

    private ImageView imageViewVideo;
    private ZingMp3Video videoInfo;
    private Spinner spinnerQuality;
    private String currentUrlSourceVideo;

    public ZingMp3VideoModelView(Context context, ImageView imageViewVideo, Spinner spinnerQuality) {
        this.context = context;
        this.imageViewVideo = imageViewVideo;
        this.spinnerQuality = spinnerQuality;
        init();
    }

    private void init() {
        visibiLoading = View.VISIBLE;
        visibiTXTShortInfo = View.INVISIBLE;
        visibiClickADPlease = View.INVISIBLE;
        visibiMainContent = View.GONE;
        visibiAD = View.VISIBLE;
        visibiLoadingLayout = View.VISIBLE;
        visibiButtonlickADPlease = View.VISIBLE;
        notifyChange();
    }

    public void getVideoInfo(String mediaID) {
        ZingMp3Factory factory = ZingMp3Client.getClient().create(ZingMp3Factory.class);
//        Call<ZingMp3Video> call = factory.getVideoInfor(CryptUtil.getCrypt(mediaID), "video");
        Call<ZingMp3Video> call = factory.getVideoInforMainServer("{\"id\":\"" + mediaID + "\"}");
        call.enqueue(new Callback<ZingMp3Video>() {
            @Override
            public void onResponse(Call<ZingMp3Video> call, Response<ZingMp3Video> response) {
                videoInfo = response.body();
                if (videoInfo != null) {
                    setShortVideoInfo();
                    currentUrlSourceVideo = videoInfo.getSource().get360();
                } else {
                    Toast.makeText(context, "Không tìm thấy tài nguyên", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ZingMp3Video> call, Throwable t) {

            }
        });
    }

    private void setShortVideoInfo() {
        this.txtVideoName = videoInfo.getTitle();
        this.txtVideoArtist = videoInfo.getArtist();
        this.txtDuration = videoInfo.getDuration() + "s";
        this.txtType = videoInfo.getGenreName();
        visibiLoading = View.INVISIBLE;
        visibiTXTShortInfo = View.VISIBLE;
        visibiClickADPlease = View.VISIBLE;
        notifyChange();
    }

    private void showThumbImageSong() {
        //Hien thi anh
        Picasso.with(context)
                .load(URL_IMAGE + videoInfo.getThumbnail())
                .error(R.drawable.song_icon)
                .into(imageViewVideo);

    }

    public void onClickSkipAD() {
        visibiMainContent = View.VISIBLE;
        visibiLoading = View.GONE;
        visibiTXTShortInfo = View.GONE;
        visibiClickADPlease = View.GONE;
        visibiButtonlickADPlease = View.GONE;
        visibiLoadingLayout = View.GONE;
        showThumbImageSong();
        showListQuanlity();
        notifyChange();
    }


    private void showListQuanlity() {
        //Load danh sach link
        ArrayList<String> listQuanlity = new ArrayList<>();

        listQuanlity.add("360p");
        listQuanlity.add("480p");
        listQuanlity.add("720p");
        listQuanlity.add("1080p");

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, listQuanlity);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerQuality.setAdapter(adapter);
        spinnerQuality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) currentUrlSourceVideo = videoInfo.getSource().get360();
                else if (i == 1) currentUrlSourceVideo = videoInfo.getSource().get480();
                else if (i == 2) currentUrlSourceVideo = videoInfo.getSource().get720();
                else if (i == 3) currentUrlSourceVideo = videoInfo.getSource().get1080();

                if (currentUrlSourceVideo == null) {
                    if (videoInfo.getSource().get360() != null)
                        currentUrlSourceVideo = videoInfo.getSource().get360();
                    else if (videoInfo.getSource().get480() != null)
                        currentUrlSourceVideo = videoInfo.getSource().get480();
                    else if (videoInfo.getSource().get720() != null)
                        currentUrlSourceVideo = videoInfo.getSource().get720();
                    else if (videoInfo.getSource().get1080() != null)
                        currentUrlSourceVideo = videoInfo.getSource().get1080();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onClickPlay() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(currentUrlSourceVideo));
        context.startActivity(i);
    }

    public void onClickDownload() {
        new DownloadResourceBroadcastReceiver().startDownload(videoInfo,null, currentUrlSourceVideo, context);
        if (MainActivity.mInterstitialAd.isLoaded() && !spinnerQuality.getSelectedItem().toString().equals("360p")) {
            MainActivity.mInterstitialAd.show();
        }
    }

    public void onClickShareVideo() {
        Intent intent2 = new Intent(); intent2.setAction(Intent.ACTION_SEND);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_TEXT, currentUrlSourceVideo);
        context.startActivity(Intent.createChooser(intent2, "Chia sẻ link tải"));
    }

}
