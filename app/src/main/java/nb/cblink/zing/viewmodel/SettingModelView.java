package nb.cblink.zing.viewmodel;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by nguyenbinh on 13/09/2016.
 */

public class SettingModelView {
    Context context;
    public SettingModelView(Context context){
        this.context = context;
    }

    public void startServiceDetectClipboard(){
        Intent myIntent = new Intent(context, DetectClipboardService.class);
        context.startService(myIntent);
    }

    public void stopServiceDetectClipBoard(){
        // Bind to LocalService
        if(isMyServiceRunning(DetectClipboardService.class, context)) {
            Intent intent = new Intent(context, DetectClipboardService.class);
            context.bindService(intent, new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName className,
                                               IBinder service) {
                    DetectClipboardService.LocalBinder binder = (DetectClipboardService.LocalBinder) service;
                    binder.getService().stopSelf();
                }

                @Override
                public void onServiceDisconnected(ComponentName arg0) {
                }
            }, Context.BIND_AUTO_CREATE);
        }
    }
    public static boolean isMyServiceRunning(Class<?> serviceClass,Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
