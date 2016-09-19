package nb.cblink.zing.data;

import nb.cblink.zing.model.AlbumInfo;
import nb.cblink.zing.model.AlbumSong;
import nb.cblink.zing.model.Song;
import nb.cblink.zing.model.ZingMp3Video;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by nguyenbinh on 12/09/2016.
 */

public interface ZingMp3Factory {
    @POST("/")
    Call<Song> getSongInfor(@Header("content") String content, @Header("type") String type);

    @GET("song/getsonginfo")
    Call<Song> getSongInforMainServer(@Query("requestdata") String content);


    @GET("video/getvideoinfo")
    Call<ZingMp3Video> getVideoInforMainServer(@Query("requestdata") String content);
    @POST("/")
    Call<ZingMp3Video> getVideoInfor(@Header("content") String content, @Header("type") String type);


    @GET("playlist/getsonglist")
    Call<AlbumSong> getAlbumInforMainServer(@Query("requestdata") String content);

    @POST("/")
    Call<AlbumSong> getAlbumInfor(@Header("content") String content, @Header("type") String type);



    @GET("playlist/getalbuminfo")
    Call<AlbumInfo> getAlbumDescripMainServer(@Query("requestdata") String content);
    @POST("/")
    Call<AlbumInfo> getAlbumDescrip(@Header("content") String content, @Header("type") String type);
}
