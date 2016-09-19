package nb.cblink.zing.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.FrameLayout;
import android.widget.Toast;

import nb.cblink.zing.R;
import nb.cblink.zing.model.ResourceZing;
import nb.cblink.zing.model.ZingTVVideo;
import nb.cblink.zing.view.activity.MainActivity;
import nb.cblink.zing.view.fragment.TrainFragment;
import nb.cblink.zing.view.fragment.ZingMP3AlbumFragment;
import nb.cblink.zing.view.fragment.ZingMP3SongFragment;
import nb.cblink.zing.view.fragment.ZingMp3VideoFragment;
import nb.cblink.zing.view.fragment.ZingTVVideoFragment;

import static nb.cblink.zing.view.fragment.SettingsFragment.KEY_PREF_CLIPBOARD_SWITCH;

/**
 * Created by nguyenbinh on 12/09/2016.
 */

public class MainActivityModelView extends BaseObservable {
    MainActivity context;
    String textSearch;
    String textHint = null;
    public static boolean isUsingApp;

    public MainActivityModelView(MainActivity context) {
        this.context = context;
        init();
    }

    private void loadTrain(){
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        TrainFragment fragmentTrain = new TrainFragment();
        transaction.replace(R.id.main_content_view, fragmentTrain);
        transaction.commit();
    }

    private void init() {
        //Khoi tao co dang su dung app
        isUsingApp = true;
        //Kiem tra xem service lang nghe con hoat dong khong
        if (!SettingModelView.isMyServiceRunning(DetectClipboardService.class, context)) {
            SharedPreferences.Editor prefs = PreferenceManager.getDefaultSharedPreferences(context).edit();
            prefs.putBoolean(KEY_PREF_CLIPBOARD_SWITCH, false);
            prefs.apply();
        }
        //Hien thi huong dan su dung
        loadTrain();
    }

    public void onClickGetLink() {
        ResourceZing resourceZing = new ResourceZing(textSearch);
        if (resourceZing.isMatch()) {
            if (resourceZing.getType().equals("album")) {
                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                ZingMP3AlbumFragment fragmentAlbum = new ZingMP3AlbumFragment();
                Bundle bundle = new Bundle();
                bundle.putString(ZingMP3AlbumSongModelView.ID, resourceZing.getResourceID());
                fragmentAlbum.setArguments(bundle);
                transaction.replace(R.id.main_content_view, fragmentAlbum);
                transaction.commit();
            } else if (resourceZing.getType().equals("bai-hat")) {
                //Load giao dien chinh
                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                ZingMP3SongFragment fragmentSong = new ZingMP3SongFragment();
                Bundle bundle = new Bundle();
                bundle.putString(ZingMP3SongModelView.ID, resourceZing.getResourceID());
                fragmentSong.setArguments(bundle);
                transaction.replace(R.id.main_content_view, fragmentSong);
                transaction.commit();
            } else if (resourceZing.getType().equals("video-clip")) {
                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                ZingMp3VideoFragment fragmentVideo = new ZingMp3VideoFragment();
                Bundle bundle = new Bundle();
                bundle.putString(ZingMp3VideoModelView.ID, resourceZing.getResourceID());
                fragmentVideo.setArguments(bundle);
                transaction.replace(R.id.main_content_view, fragmentVideo);
                transaction.commit();
            } else if (resourceZing.getType().equals("video")) {
                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                ZingTVVideoFragment fragmentVideo = new ZingTVVideoFragment();
                Bundle bundle = new Bundle();
                bundle.putString(ZingTVVideoModelView.ID, resourceZing.getResourceID());
                fragmentVideo.setArguments(bundle);
                transaction.replace(R.id.main_content_view, fragmentVideo);
                transaction.commit();
            }
        } else {
            Toast.makeText(context, "URL not match!", Toast.LENGTH_SHORT).show();
        }
        setTextHint(getTextSearch());
        setTextSearch("");


    }

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
        notifyChange();
    }

    public String getTextHint() {
        return textHint;
    }

    public void setTextHint(String textHint) {
        this.textHint = textHint;
    }

    public TextWatcher onBasicChanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            setTextSearch(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    public void handleIntentShare(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        ResourceZing resourceZing = new ResourceZing(sharedText);
        if (resourceZing.isMatch()) {
            setTextSearch(sharedText);
        } else {
            Toast.makeText(context, "Nội dung không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}
