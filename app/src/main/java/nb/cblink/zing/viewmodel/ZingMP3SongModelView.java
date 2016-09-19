package nb.cblink.zing.viewmodel;


import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import nb.cblink.zing.R;
import nb.cblink.zing.data.DownloadResourceBroadcastReceiver;
import nb.cblink.zing.data.ZingMp3Client;
import nb.cblink.zing.data.ZingMp3Factory;
import nb.cblink.zing.data.ZingResource;
import nb.cblink.zing.model.Song;
import nb.cblink.zing.view.activity.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nguyenbinh on 12/09/2016.
 */

public class ZingMP3SongModelView extends BaseObservable {
    public static final String URL_IMAGE = ZingResource.getURLIMAGEZINGMP3();
    private Context context;
    public static String ID = "KEY_ID_SONG";
    @Bindable
    public String txtSongName;
    @Bindable
    public String txtSongArtist;
    @Bindable
    public String txtSongType;
    @Bindable
    public boolean isPlaying;

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

    private String currentUrlSourceSong;
    private CircleImageView imageViewSong;
    private Spinner spinnerQuality;
    private Song songInfo;
    private SeekBar seekBarPlayMusic;
    static MediaPlayer mediaPlayer = new MediaPlayer();
    private Handler myHandler = new Handler();

    public ZingMP3SongModelView(Context context, CircleImageView imageViewSong, Spinner spinnerChooseQuality, SeekBar seekBar) {
        this.context = context;
        this.imageViewSong = imageViewSong;
        this.spinnerQuality = spinnerChooseQuality;
        this.seekBarPlayMusic = seekBar;
        init();
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

    public void onClickPlay(){
        if(isPlaying){
            stopStreamMusic();
        }else {
            startStreamMusic();
        }
        isPlaying = !isPlaying;
        notifyChange();
    }

    private void showThumbImageSong(){
        //Hien thi anh
        Picasso.with(context)
                .load(URL_IMAGE + songInfo.getThumbnail())
                .error(R.drawable.song_icon)
                .into(imageViewSong);

    }

    private void showListQuanlity(){
        //Load danh sach link
        ArrayList<String> listQuanlity = new ArrayList<>();
        if( songInfo.getSource().get128() != null && !songInfo.getSource().get128().equals("")) {
            listQuanlity.add("128");
            currentUrlSourceSong = songInfo.getSource().get128();
        }
        if( songInfo.getSource().get320() != null && !songInfo.getSource().get320().equals("")) {
            listQuanlity.add("320");
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item, listQuanlity );
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerQuality.setAdapter(adapter);
        spinnerQuality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0) currentUrlSourceSong = songInfo.getSource().get128();
                else currentUrlSourceSong = songInfo.getSource().get320();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void init() {
        visibiLoading = View.VISIBLE;
        visibiTXTShortInfo = View.INVISIBLE;
        visibiClickADPlease = View.INVISIBLE;
        visibiMainContent = View.GONE;
        visibiAD = View.VISIBLE;
        visibiLoadingLayout = View.VISIBLE;
        visibiButtonlickADPlease = View.VISIBLE;
        isPlaying = false;
        notifyChange();
    }

    public void onClickShareSong(){
        Intent intent2 = new Intent(); intent2.setAction(Intent.ACTION_SEND);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_TEXT, currentUrlSourceSong);
        context.startActivity(Intent.createChooser(intent2, "Chia sẻ link tải"));
    }
    public void onClickDownload(){
        new DownloadResourceBroadcastReceiver().startDownload(songInfo,null, spinnerQuality.getSelectedItem().toString(), context);
        if (MainActivity.mInterstitialAd.isLoaded() && !spinnerQuality.getSelectedItem().toString().equals("128")) {
            MainActivity.mInterstitialAd.show();
        }
    }

    public void getSongInfo(String mediaID) {
        ZingMp3Factory factory = ZingMp3Client.getClient().create(ZingMp3Factory.class);
//        Call<Song> call = factory.getSongInfor(CryptUtil.getCrypt(mediaID), "song");
        Call<Song> call = factory.getSongInforMainServer("{\"id\":\""+mediaID+"\"}");
        call.enqueue(new Callback<Song>() {
            @Override
            public void onResponse(Call<Song> call, Response<Song> response) {
                songInfo = response.body();
                if (songInfo != null) {
                    setShortInfo(songInfo.getTitle(), songInfo.getArtist(), songInfo.getGenreName());
                } else
                    Toast.makeText(context, "Không tìm thấy tài nguyên", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Song> call, Throwable t) {

            }
        });
    }

    private void setShortInfo(String songName, String songArtist, String songType) {
        this.txtSongName = songName;
        this.txtSongArtist = songArtist;
        this.txtSongType = songType;
        visibiLoading = View.INVISIBLE;
        visibiTXTShortInfo = View.VISIBLE;
        visibiClickADPlease = View.VISIBLE;
        notifyChange();
    }


    public void stopStreamMusic(){
        myHandler.removeCallbacks(UpdateSongTime);
        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
        seekBarPlayMusic.setMax(100);
        seekBarPlayMusic.setProgress(0);
        mediaPlayer = null;
    }

    private void startStreamMusic() {
        new Thread(){
            @Override
            public void run() {
                if(mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                }
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        stopStreamMusic();
                        isPlaying = false;
                        notifyChange();
                    }
                });
                try {
                    mediaPlayer.setDataSource(songInfo.getSource().get128());
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    mediaPlayer.start();
                    myHandler.postDelayed(UpdateSongTime,100);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context,"Không thể chơi được nhạc",Toast.LENGTH_SHORT).show();
                }
            }
        }.start();
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            if(mediaPlayer == null){
                myHandler.postDelayed(this, 100);
                return;
            }
            int duration = mediaPlayer.getDuration();
            int currentPosition = mediaPlayer.getCurrentPosition();
            seekBarPlayMusic.setMax(duration);
            seekBarPlayMusic.setProgress((int) currentPosition);
            myHandler.postDelayed(this, 100);
        }
    };

}
