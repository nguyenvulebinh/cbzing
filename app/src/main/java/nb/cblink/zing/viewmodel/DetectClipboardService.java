package nb.cblink.zing.viewmodel;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import nb.cblink.zing.R;
import nb.cblink.zing.model.ResourceZing;
import nb.cblink.zing.view.activity.MainActivity;

/**
 * Created by nguyenbinh on 13/09/2016.
 */

public class DetectClipboardService extends Service implements ClipboardManager.OnPrimaryClipChangedListener{
    boolean keepRun;
    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();

    public DetectClipboardService(){
        keepRun = true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                String contentCopy = clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
                ResourceZing resourceZing = new ResourceZing(contentCopy);
                if(resourceZing.isMatch() && !MainActivityModelView.isUsingApp){
                    if(resourceZing.getSource().equals("tv.zing.vn")||resourceZing.getSource().equals("m.tv.zing.vn")){
                        createNotify("ZingTV", resourceZing.getTitle(), contentCopy);
                    }else{
                        createNotify("ZingMP3", resourceZing.getTitle(), contentCopy);
                    }
                }
            }
        });
    }

    public void createNotify(String title, String content, String url){
        Intent activityIntent = new Intent(this,MainActivity.class);
        activityIntent.setAction(Intent.ACTION_SEND);
        activityIntent.setType("text/plain");
        activityIntent.putExtra(Intent.EXTRA_TEXT, url);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, activityIntent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVibrate(new long[]{250,500,250,500,250,500});
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(
                        Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
        notificationManager.notify(0,
                notificationBuilder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("DetectClipboardService", "OnStartService");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onPrimaryClipChanged() {
        Toast.makeText(this, "Somethiang copy", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("DetectClipboardService", "OnBindService");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("DetectClipboardService", "OnDestroyService");
    }

    public class LocalBinder extends Binder {
        public DetectClipboardService getService() {
            // Return this instance of LocalService so clients can call public methods
            return DetectClipboardService.this;
        }
    }
}
