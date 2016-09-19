package nb.cblink.zing.model;

/**
 * Created by nguyenbinh on 15/09/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumInfo {

    @SerializedName("playlist_id")
    @Expose
    private String playlistId;
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
    @SerializedName("zaloid")
    @Expose
    private Integer zaloid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("total_track")
    @Expose
    private Integer totalTrack;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_hit")
    @Expose
    private Integer isHit;
    @SerializedName("is_official")
    @Expose
    private Integer isOfficial;
    @SerializedName("is_album")
    @Expose
    private Integer isAlbum;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("license_status")
    @Expose
    private Integer licenseStatus;
    @SerializedName("status_id")
    @Expose
    private Integer statusId;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("total_play")
    @Expose
    private Integer totalPlay;
    @SerializedName("genre_name")
    @Expose
    private String genreName;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("like_this")
    @Expose
    private Boolean likeThis;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("favourites")
    @Expose
    private Integer favourites;
    @SerializedName("favourite_this")
    @Expose
    private Boolean favouriteThis;
    @SerializedName("response")
    @Expose
    private Response response;

    /**
     * @return The playlistId
     */
    public String getPlaylistId() {
        return playlistId;
    }

    /**
     * @param playlistId The playlist_id
     */
    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
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
     * @return The zaloid
     */
    public Integer getZaloid() {
        return zaloid;
    }

    /**
     * @param zaloid The zaloid
     */
    public void setZaloid(Integer zaloid) {
        this.zaloid = zaloid;
    }

    /**
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The cover
     */
    public String getCover() {
        return cover;
    }

    /**
     * @param cover The cover
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * @return The totalTrack
     */
    public Integer getTotalTrack() {
        return totalTrack;
    }

    /**
     * @param totalTrack The total_track
     */
    public void setTotalTrack(Integer totalTrack) {
        this.totalTrack = totalTrack;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The isHit
     */
    public Integer getIsHit() {
        return isHit;
    }

    /**
     * @param isHit The is_hit
     */
    public void setIsHit(Integer isHit) {
        this.isHit = isHit;
    }

    /**
     * @return The isOfficial
     */
    public Integer getIsOfficial() {
        return isOfficial;
    }

    /**
     * @param isOfficial The is_official
     */
    public void setIsOfficial(Integer isOfficial) {
        this.isOfficial = isOfficial;
    }

    /**
     * @return The isAlbum
     */
    public Integer getIsAlbum() {
        return isAlbum;
    }

    /**
     * @param isAlbum The is_album
     */
    public void setIsAlbum(Integer isAlbum) {
        this.isAlbum = isAlbum;
    }

    /**
     * @return The year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year The year
     */
    public void setYear(String year) {
        this.year = year;
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
}