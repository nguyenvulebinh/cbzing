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
import nb.cblink.zing.data.ZingResource;
import nb.cblink.zing.data.ZingTVClient;
import nb.cblink.zing.data.ZingTVVideoFactory;
import nb.cblink.zing.model.AlbumInfo;
import nb.cblink.zing.model.ZingMp3Video;
import nb.cblink.zing.model.ZingTVVideo;
import nb.cblink.zing.view.activity.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nguyenbinh on 12/09/2016.
 */

public class ZingTVVideoModelView extends BaseObservable {
    public static String URL_IMAGE = ZingResource.getURLIMAGEZINGTV();
    public static String API_ID_ZINGTV = ZingResource.API_ZINGTV_ID;
    private Context context;
    public static String ID = "KEY_ID_TV";
    @Bindable
    public String txtVideoName;
    @Bindable
    public String txtTypeVideo;
    @Bindable
    public String txtDuration;

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
    private ZingTVVideo videoInfo;

    private ImageView imageViewVideo;
    private Spinner spinnerQuality;
    private String currentUrlSourceVideo;

    public ZingTVVideoModelView(Context context , ImageView imageViewVideo, Spinner spinnerQuality) {
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


    public void getZingTVVideoInfo(String mediaID){
        ZingTVVideoFactory factory = ZingTVClient.getClient().create(ZingTVVideoFactory.class);
        Call<ZingTVVideo> call = factory.loadZingTVVideo(API_ID_ZINGTV, mediaID);
        call.enqueue(new Callback<ZingTVVideo>() {
            @Override
            public void onResponse(Call<ZingTVVideo> call, Response<ZingTVVideo> response) {
                videoInfo = response.body();
                if(videoInfo != null){
                    setShortVideoInfo();
                    currentUrlSourceVideo = videoInfo.getResponse().getOtherUrl().getVideo3GP();
                }else{
                    Toast.makeText(context, "Không tìm thấy tài nguyên", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ZingTVVideo> call, Throwable t) {
                Log.d("MainActivity", "Error");
            }
        });
    }

    private void setShortVideoInfo(){
        this.txtVideoName = videoInfo.getResponse().getFullName();
        this.txtTypeVideo = videoInfo.getResponse().getProgramGenre().get(0).getName();
        this.txtDuration = videoInfo.getResponse().getDuration() + "s";
        visibiLoading = View.INVISIBLE;
        visibiTXTShortInfo = View.VISIBLE;
        visibiClickADPlease = View.VISIBLE;
        notifyChange();
    }

    public void onClickSkipAD(){
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

    private void showThumbImageSong() {
        //Hien thi anh
        Picasso.with(context)
                .load(URL_IMAGE + videoInfo.getResponse().getThumbnail())
                .error(R.drawable.song_icon)
                .into(imageViewVideo);

    }

    private void showListQuanlity() {
        //Load danh sach link
        ArrayList<String> listQuanlity = new ArrayList<>();

        listQuanlity.add("3GP");
        listQuanlity.add("480p");
        listQuanlity.add("720p");

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, listQuanlity);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerQuality.setAdapter(adapter);
        spinnerQuality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) currentUrlSourceVideo = videoInfo.getResponse().getOtherUrl().getVideo3GP();
                else if (i == 1) currentUrlSourceVideo = videoInfo.getResponse().getOtherUrl().getVideo480();
                else if (i == 2) currentUrlSourceVideo = videoInfo.getResponse().getOtherUrl().getVideo720();

                if (currentUrlSourceVideo == null) {
                    if (videoInfo.getResponse().getOtherUrl().getVideo3GP() != null)
                        currentUrlSourceVideo = videoInfo.getResponse().getOtherUrl().getVideo3GP();
                    else if (videoInfo.getResponse().getOtherUrl().getVideo480() != null)
                        currentUrlSourceVideo = videoInfo.getResponse().getOtherUrl().getVideo480();
                    else if (videoInfo.getResponse().getOtherUrl().getVideo720() != null)
                        currentUrlSourceVideo = videoInfo.getResponse().getOtherUrl().getVideo720();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onClickPlay(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://" + currentUrlSourceVideo));
        context.startActivity(i);
    }

    public void onClickDownload(){
        new DownloadResourceBroadcastReceiver().startDownload(videoInfo,null, "http://" +currentUrlSourceVideo, context);
        if (MainActivity.mInterstitialAd.isLoaded() && !spinnerQuality.getSelectedItem().toString().equals("3GP")) {
            MainActivity.mInterstitialAd.show();
        }
    }

    public void onClickShareVideo(){
        Intent intent2 = new Intent(); intent2.setAction(Intent.ACTION_SEND);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_TEXT, "http://" +currentUrlSourceVideo);
        context.startActivity(Intent.createChooser(intent2, "Chia sẻ link tải"));
    }
}
