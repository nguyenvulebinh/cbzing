package nb.cblink.zing.model;

/**
 * Created by nguyenbinh on 12/09/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ZingMp3Video {

    @SerializedName("video_id")
    @Expose
    private Integer videoId;
    @SerializedName("song_id_encode")
    @Expose
    private String songIdEncode;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("artist_id")
    @Expose
    private String artistId;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("genre_id")
    @Expose
    private String genreId;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("download_status")
    @Expose
    private Integer downloadStatus;
    @SerializedName("copyright")
    @Expose
    private Integer copyright;
    @SerializedName("status_id")
    @Expose
    private Integer statusId;
    @SerializedName("co_id")
    @Expose
    private Integer coId;
    @SerializedName("ad_status")
    @Expose
    private Integer adStatus;
    @SerializedName("license_status")
    @Expose
    private Integer licenseStatus;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("source")
    @Expose
    private Source source;
    @SerializedName("preroll")
    @Expose
    private String preroll;
    @SerializedName("total_play")
    @Expose
    private Integer totalPlay;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("like_this")
    @Expose
    private Boolean likeThis;
    @SerializedName("favourites")
    @Expose
    private Integer favourites;
    @SerializedName("favourite_this")
    @Expose
    private Boolean favouriteThis;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("genre_name")
    @Expose
    private String genreName;
    @SerializedName("response")
    @Expose
    private Response response;

    /**
     * @return The videoId
     */
    public Integer getVideoId() {
        return videoId;
    }

    /**
     * @param videoId The video_id
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /**
     * @return The songIdEncode
     */
    public String getSongIdEncode() {
        return songIdEncode;
    }

    /**
     * @param songIdEncode The song_id_encode
     */
    public void setSongIdEncode(String songIdEncode) {
        this.songIdEncode = songIdEncode;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The artistId
     */
    public String getArtistId() {
        return artistId;
    }

    /**
     * @param artistId The artist_id
     */
    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    /**
     * @return The artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist The artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @return The genreId
     */
    public String getGenreId() {
        return genreId;
    }

    /**
     * @param genreId The genre_id
     */
    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    /**
     * @return The thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail The thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return The duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * @param duration The duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * @return The downloadStatus
     */
    public Integer getDownloadStatus() {
        return downloadStatus;
    }

    /**
     * @param downloadStatus The download_status
     */
    public void setDownloadStatus(Integer downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    /**
     * @return The copyright
     */
    public Integer getCopyright() {
        return copyright;
    }

    /**
     * @param copyright The copyright
     */
    public void setCopyright(Integer copyright) {
        this.copyright = copyright;
    }

    /**
     * @return The statusId
     */
    public Integer getStatusId() {
        return statusId;
    }

    /**
     * @param statusId The status_id
     */
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    /**
     * @return The coId
     */
    public Integer getCoId() {
        return coId;
    }

    /**
     * @param coId The co_id
     */
    public void setCoId(Integer coId) {
        this.coId = coId;
    }

    /**
     * @return The adStatus
     */
    public Integer getAdStatus() {
        return adStatus;
    }

    /**
     * @param adStatus The ad_status
     */
    public void setAdStatus(Integer adStatus) {
        this.adStatus = adStatus;
    }

    /**
     * @return The licenseStatus
     */
    public Integer getLicenseStatus() {
        return licenseStatus;
    }

    /**
     * @param licenseStatus The license_status
     */
    public void setLicenseStatus(Integer licenseStatus) {
        this.licenseStatus = licenseStatus;
    }

    /**
     * @return The link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return The source
     */
    public Source getSource() {
        return source;
    }

    /**
     * @param source The source
     */
    public void setSource(Source source) {
        this.source = source;
    }

    /**
     * @return The preroll
     */
    public String getPreroll() {
        return preroll;
    }

    /**
     * @param preroll The preroll
     */
    public void setPreroll(String preroll) {
        this.preroll = preroll;
    }

    /**
     * @return The totalPlay
     */
    public Integer getTotalPlay() {
        return totalPlay;
    }

    /**
     * @param totalPlay The total_play
     */
    public void setTotalPlay(Integer totalPlay) {
        this.totalPlay = totalPlay;
    }

    /**
     * @return The likes
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * @param likes The likes
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    /**
     * @return The likeThis
     */
    public Boolean getLikeThis() {
        return likeThis;
    }

    /**
     * @param likeThis The like_this
     */
    public void setLikeThis(Boolean likeThis) {
        this.likeThis = likeThis;
    }

    /**
     * @return The favourites
     */
    public Integer getFavourites() {
        return favourites;
    }

    /**
     * @param favourites The favourites
     */
    public void setFavourites(Integer favourites) {
        this.favourites = favourites;
    }

    /**
     * @return The favouriteThis
     */
    public Boolean getFavouriteThis() {
        return favouriteThis;
    }

    /**
     * @param favouriteThis The favourite_this
     */
    public void setFavouriteThis(Boolean favouriteThis) {
        this.favouriteThis = favouriteThis;
    }

    /**
     * @return The comments
     */
    public Integer getComments() {
        return comments;
    }

    /**
     * @param comments The comments
     */
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    /**
     * @return The genreName
     */
    public String getGenreName() {
        return genreName;
    }

    /**
     * @param genreName The genre_name
     */
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    /**
     * @return The response
     */
    public Response getResponse() {
        return response;
    }

    /**
     * @param response The response
     */
    public void setResponse(Response response) {
        this.response = response;
    }

    class Response {

        @SerializedName("msgCode")
        @Expose
        private Integer msgCode;
        @SerializedName("msg")
        @Expose
        private String msg;

        /**
         * @return The msgCode
         */
        public Integer getMsgCode() {
            return msgCode;
        }

        /**
         * @param msgCode The msgCode
         */
        public void setMsgCode(Integer msgCode) {
            this.msgCode = msgCode;
        }

        /**
         * @return The msg
         */
        public String getMsg() {
            return msg;
        }

        /**
         * @param msg The msg
         */
        public void setMsg(String msg) {
            this.msg = msg;
        }

    }

    public class Source {

        @SerializedName("360")
        @Expose
        private String _360;
        @SerializedName("480")
        @Expose
        private String _480;
        @SerializedName("720")
        @Expose
        private String _720;
        @SerializedName("1080")
        @Expose
        private String _1080;

        /**
         * @return The _360
         */
        public String get360() {
            return _360;
        }

        /**
         * @param _360 The 360
         */
        public void set360(String _360) {
            this._360 = _360;
        }

        /**
         * @return The _480
         */
        public String get480() {
            return _480;
        }

        /**
         * @param _480 The 480
         */
        public void set480(String _480) {
            this._480 = _480;
        }

        /**
         * @return The _720
         */
        public String get720() {
            return _720;
        }

        /**
         * @param _720 The 720
         */
        public void set720(String _720) {
            this._720 = _720;
        }

        /**
         * @return The _1080
         */
        public String get1080() {
            return _1080;
        }

        /**
         * @param _1080 The 1080
         */
        public void set1080(String _1080) {
            this._1080 = _1080;
        }

    }


}