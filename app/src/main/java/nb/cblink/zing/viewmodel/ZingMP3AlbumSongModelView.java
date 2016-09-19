package nb.cblink.zing.viewmodel;


import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import nb.cblink.zing.R;
import nb.cblink.zing.data.DownloadResourceBroadcastReceiver;
import nb.cblink.zing.data.ZingMp3Client;
import nb.cblink.zing.data.ZingMp3Factory;
import nb.cblink.zing.data.ZingResource;
import nb.cblink.zing.model.AlbumInfo;
import nb.cblink.zing.model.AlbumSong;
import nb.cblink.zing.model.Song;
import nb.cblink.zing.view.activity.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nguyenbinh on 12/09/2016.
 */

public class ZingMP3AlbumSongModelView extends BaseObservable {

    public static final String URL_IMAGE = ZingResource.getURLIMAGEZINGMP3();
    private Context context;
    public static String ID = "KEY_ID_ALBUM";
    @Bindable
    public String txtAlbumName;
    @Bindable
    public String txtAlbumArtist;
    @Bindable
    public String txtAlbumDescrip;
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
    @Bindable
    public int visibiLoadingSongLayout;
    @Bindable
    public int visibiLoadingSong;

    private AlbumInfo album;
    private AlbumSong albumSong;
    private String mediaID;
    private RecyclerView recyclerView;
    static MediaPlayer mediaPlayer = new MediaPlayer();
    private Handler myHandler = new Handler();
    private SeekBar seekBarPlayMusic;
    private AlbumSong.Doc currentSong;
    private Spinner spinnerQuanlityAlbum;
    private ZingMP3AlbumSongAdapter adapter;
    private CircleImageView albumCoverImage;

    public ZingMP3AlbumSongModelView(Context context, RecyclerView recyclerView, SeekBar seekBarPlayMusic, Spinner spinnerQuanlityAlbum, CircleImageView albumCoverImage) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.seekBarPlayMusic = seekBarPlayMusic;
        this.spinnerQuanlityAlbum = spinnerQuanlityAlbum;
        this.albumCoverImage = albumCoverImage;
        init();
    }


    private void init() {
        visibiLoading = View.VISIBLE;
        visibiTXTShortInfo = View.INVISIBLE;
        visibiClickADPlease = View.INVISIBLE;
        visibiMainContent = View.GONE;
        visibiLoadingSongLayout = View.GONE;
        visibiAD = View.VISIBLE;
        visibiLoadingLayout = View.VISIBLE;
        visibiButtonlickADPlease = View.VISIBLE;
        isPlaying = false;
        notifyChange();
    }


    public void stopStreamMusic() {
        myHandler.removeCallbacks(UpdateSongTime);
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
        seekBarPlayMusic.setMax(100);
        seekBarPlayMusic.setProgress(0);
        mediaPlayer = null;
    }

    private void startStreamMusic() {
        new Thread() {
            @Override
            public void run() {
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                } else {
                    stopStreamMusic();
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
                    mediaPlayer.setDataSource(currentSong.getSource().get128());
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    mediaPlayer.start();
                    myHandler.postDelayed(UpdateSongTime, 100);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Không thể chơi được nhạc", Toast.LENGTH_SHORT).show();
                }
            }
        }.start();
    }

    public void clickSong(AlbumSong.Doc song) {
        if (isPlaying) {
            stopStreamMusic();
        }
        currentSong = song;
        isPlaying = true;

        Picasso.with(context)
                .load("http://image.mp3.zdn.vn/" + currentSong.getThumbnail())
                .error(R.drawable.song_icon)
                .into(albumCoverImage);

        this.txtAlbumName = currentSong.getTitle();
        this.txtAlbumArtist = currentSong.getArtist();

        notifyChange();
        startStreamMusic();
    }

    void getAlbumInfo(String mediaID) {
        ZingMp3Factory factory = ZingMp3Client.getClient().create(ZingMp3Factory.class);
//        Call<AlbumSong> call = factory.getAlbumInfor(CryptUtil.getCrypt(mediaID), "album");
        Call<AlbumSong> call = factory.getAlbumInforMainServer("{\"id\":\"" + mediaID + "\",\"start\":0,\"length\":200}");
        call.enqueue(new Callback<AlbumSong>() {
            @Override
            public void onResponse(Call<AlbumSong> call, Response<AlbumSong> response) {
                albumSong = response.body();
                if (albumSong != null) {
                    setDetailAlbumInfo();
                    currentSong = albumSong.getDocs().get(0);
                } else {
                    Toast.makeText(context, "Không tìm thấy tài nguyên", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AlbumSong> call, Throwable t) {
                Toast.makeText(context, "Không tìm thấy tài nguyên", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAlbumDescrip(String mediaID) {
        this.mediaID = mediaID;
        ZingMp3Factory factory = ZingMp3Client.getClient().create(ZingMp3Factory.class);
//        Call<AlbumInfo> call = factory.getAlbumDescrip(CryptUtil.getCrypt(mediaID), "albuminfo");
        Call<AlbumInfo> call = factory.getAlbumDescripMainServer("{\"id\":\"" + mediaID + "\"}");
        call.enqueue(new Callback<AlbumInfo>() {
            @Override
            public void onResponse(Call<AlbumInfo> call, Response<AlbumInfo> response) {
                album = response.body();
                if (album != null) {
                    setShortAlbumInfo();
                } else {
                    Toast.makeText(context, "Không tìm thấy tài nguyên", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AlbumInfo> call, Throwable t) {
                Toast.makeText(context, "Không tìm thấy tài nguyên", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setShortAlbumInfo() {
        this.txtAlbumName = album.getTitle();
        this.txtAlbumArtist = album.getArtist();
        this.txtAlbumDescrip = album.getDescription();
        visibiLoading = View.INVISIBLE;
        visibiTXTShortInfo = View.VISIBLE;
        visibiClickADPlease = View.VISIBLE;
        notifyChange();
    }

    public void onClickSkipAD() {
        visibiLoadingSongLayout = View.VISIBLE;
        visibiLoadingSong = View.VISIBLE;
        visibiLoading = View.GONE;
        visibiTXTShortInfo = View.GONE;
        visibiClickADPlease = View.GONE;
        visibiButtonlickADPlease = View.GONE;
        visibiLoadingLayout = View.GONE;
        notifyChange();
        getAlbumInfo(mediaID);
    }

    private void setDetailAlbumInfo() {
        showListQuanlity();
        visibiLoadingSong = View.INVISIBLE;
        visibiMainContent = View.VISIBLE;
        Picasso.with(context)
                .load("http://image.mp3.zdn.vn/" + album.getCover())
                .error(R.drawable.song_icon)
                .into(albumCoverImage);
        //Set layout cho recycle view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //Cai dat Adapter cho RecycleView
        adapter = new ZingMP3AlbumSongAdapter(recyclerView, albumSong, context, this);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new ZingMP3AlbumSongAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                adapter.addToLoadMore();
                adapter.addMoreSongToShow();
            }
        });

        recyclerView.setHasFixedSize(true);
        notifyChange();

    }

    public void onClickDownloadAll() {
        new DownloadResourceBroadcastReceiver().startDownload(albumSong, album, spinnerQuanlityAlbum.getSelectedItem().toString(), context);
        if (MainActivity.mInterstitialAd.isLoaded() && !spinnerQuanlityAlbum.getSelectedItem().toString().equals("128")) {
            MainActivity.mInterstitialAd.show();
        }
    }

    private void showListQuanlity() {
        //Load danh sach link
        ArrayList<String> listQuanlity = new ArrayList<>();

        listQuanlity.add("128");

        listQuanlity.add("320");

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, listQuanlity);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerQuanlityAlbum.setAdapter(adapter);
    }

    public void onClickPlay() {
        if (isPlaying) {
            stopStreamMusic();
        } else {
            startStreamMusic();
        }
        isPlaying = !isPlaying;
        notifyChange();
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            if (mediaPlayer == null) {
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

    public void clickDownload(AlbumSong.Doc song) {
        new DownloadResourceBroadcastReceiver().startDownload(song, null, "320", context);
    }

    public void clickShareSong(AlbumSong.Doc song) {
        Intent intent2 = new Intent();
        intent2.setAction(Intent.ACTION_SEND);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_TEXT, song.getSource().get320());
        context.startActivity(Intent.createChooser(intent2, "Chia sẻ link tải "));
    }

}
