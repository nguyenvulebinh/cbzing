package nb.cblink.zing.data;

import nb.cblink.zing.model.ZingTVVideo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nguyenbinh on 12/09/2016.
 */

public interface ZingTVVideoFactory {
    @GET("info")
    Call<ZingTVVideo> loadZingTVVideo(@Query("api_key") String apiKey, @Query("media_id") String mediaID);
}
