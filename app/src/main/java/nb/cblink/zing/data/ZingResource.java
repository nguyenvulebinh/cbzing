package nb.cblink.zing.data;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/**
 * Created by nguyenbinh on 18/09/2016.
 */

public class ZingResource {
    private static String API_ZINGMP3 = "";
    private static String API_ZINGTV = "";
    private static String URL_IMAGE_ZINGMP3 = "";
    private static String URL_IMAGE_ZINGTV = "";


    public static String API_ZINGTV_ID_DEFAULT = "";
    public static String API_ZINGTV_ID;


    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    Context context;

    public ZingResource(Context context) {
        this.context = context;
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        getZingMP3Resource();
    }


    /**
     * Fetch discount from server.
     */
    private void getZingMP3Resource() {

        long cacheExpiration = 24 * 60 * 60;
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@android.support.annotation.NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            API_ZINGTV_ID = mFirebaseRemoteConfig.getString("API_ID_ZINGTV");
                            if(API_ZINGTV_ID.equals("")) API_ZINGTV_ID = API_ZINGTV_ID_DEFAULT;
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            API_ZINGTV_ID = API_ZINGTV_ID_DEFAULT;
                        }
                    }
                });
    }

    public static String getURLIMAGEZINGMP3() {
        String value = new String(Base64.decode(URL_IMAGE_ZINGMP3, Base64.DEFAULT));
        byte[] decodeValue = Base64.decode(value.substring("YUhSMGNEb3ZMMkZ3YVM1YUhSMGNEb3ZMMkZ3YVM1dGNETXVlbWx1Wnk1MmJpOWhjR2t2Ylc5aWFX".length(), value.length()).getBytes(), Base64.DEFAULT);
        return new String(decodeValue);
    }


    public static String getURLIMAGEZINGTV() {
        String value = new String(Base64.decode(URL_IMAGE_ZINGTV, Base64.DEFAULT));
        byte[] decodeValue = Base64.decode(value.substring("YUhSMGNEb3ZMMkZ3YVM1YUhSMGNEb3ZMMkZ3YVM1dGNETXVlbWx1Wnk1MmJpOWhjR2t2Ylc5aWFX".length(), value.length()).getBytes(), Base64.DEFAULT);
        return new String(decodeValue);
    }

    public static String getAPI_ZINGTV() {
        String value = new String(Base64.decode(API_ZINGTV, Base64.DEFAULT));
        byte[] decodeValue = Base64.decode(value.substring("YUhSMGNEb3ZMMkZ3YVM1YUhSMGNEb3ZMMkZ3YVM1dGNETXVlbWx1Wnk1MmJpOWhjR2t2Ylc5aWFX".length(), value.length()).getBytes(), Base64.DEFAULT);
        return new String(decodeValue);
    }

    public static String getApiZingmp3() {
        String value = new String(Base64.decode(API_ZINGMP3, Base64.DEFAULT));
        byte[] decodeValue = Base64.decode(value.substring("YUhSMGNEb3ZMMkZ3YVM1YUhSMGNEb3ZMMkZ3YVM1dGNETXVlbWx1Wnk1MmJpOWhjR2t2Ylc5aWFX".length(), value.length()).getBytes(), Base64.DEFAULT);
        return new String(decodeValue);
    }
}
