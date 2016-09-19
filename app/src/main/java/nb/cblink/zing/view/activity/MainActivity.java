package nb.cblink.zing.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.net.InetAddress;

import nb.cblink.zing.R;
import nb.cblink.zing.data.ZingResource;
import nb.cblink.zing.databinding.MainActivityBinding;
import nb.cblink.zing.viewmodel.MainActivityModelView;

public class MainActivity extends AppCompatActivity {
    MainActivityModelView modelView;
    public static InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityBinding mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        modelView = new MainActivityModelView(this);
        mainActivityBinding.setMainActMV(modelView);
        Intent intent = getIntent();
        if (intent != null) handleIntent(intent);
        //Kiem tra ket noi internet
        if (isNetworkConnected()) {
            addAds();
        } else {
            //Ban ra dialog de thong bao ko cos ket noi
            showDialogNotConnectInternet();
        }

        //Lay config tu server
        new ZingResource(this);
    }

    private void addAds() {
        MobileAds.initialize(this, "ca-app-pub-6742359292736580~7748644152");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6742359292736580/3178843752");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                //Cho nguoi dung tai
            }
        });

        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (isNetworkConnected()) {
            super.onNewIntent(intent);
            handleIntent(intent);
        } else {
            showDialogNotConnectInternet();
        }
    }

    void showDialogNotConnectInternet() {
        AlertDialog.Builder builder = new
                AlertDialog.Builder(this);
        builder.setTitle("Không có kết nối internet")
                .setMessage("Bật kết nối internet?")
                .setPositiveButton("Cài đặt",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                                MainActivity.this.finish();
                            }
                        })
                .setNegativeButton("Thoát",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                MainActivity.this.finish();
                            }
                        });
        builder.create().show();
    }

    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                modelView.handleIntentShare(intent);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                String message = "https://play.google.com/store/apps/details?id=nb.cblink.zing";
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, message);

                startActivity(Intent.createChooser(share, "Chia sẻ ứng dụng với mọi người"));
                break;
            case R.id.menu_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_feedback:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","nguyenvulebinh@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Phản hồi người dùng ZDownload");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Xin chào!");
                startActivity(Intent.createChooser(emailIntent, "Chọn Gmail để gửi phản hồi"));
                break;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        modelView.isUsingApp = false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        modelView.isUsingApp = true;
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }
    }
}