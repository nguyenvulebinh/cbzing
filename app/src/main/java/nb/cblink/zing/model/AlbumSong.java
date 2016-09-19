package nb.cblink.zing.model;

/**
 * Created by nguyenbinh on 12/09/2016.
 */

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumSong {

    @SerializedName("numFound")
    @Expose
    private Integer numFound;
    @SerializedName("docs")
    @Expose
    private List<Doc> docs = new ArrayList<Doc>();
    @SerializedName("response")
    @Expose
    private Response response;

    /**
     * @return The numFound
     */
    public Integer getNumFound() {
        return numFound;
    }

    /**
     * @param numFound The numFound
     */
    public void setNumFound(Integer numFound) {
        this.numFound = numFound;
    }

    /**
     * @return The docs
     */
    public List<Doc> getDocs() {
        return docs;
    }

    /**
     * @param docs The docs
     */
    public void setDocs(List<Doc> docs) {
        this.docs = docs;
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


    public class Doc extends BaseObservable {

        @SerializedName("song_id")
        @Expose
        private Integer songId;
        @SerializedName("video_id_encode")
        @Expose
        private String videoIdEncode;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("artist_id")
        @Expose
        private String artistId;
        @SerializedName("artist")
        @Expose
        private String artist;
        @SerializedName("isrc")
        @Expose
        private String isrc;
        @SerializedName("zaloid")
        @Expose
        private Integer zaloid;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("duration")
        @Expose
        private Integer duration;
        @SerializedName("download_status")
        @Expose
        private Integer downloadStatus;
        @SerializedName("copyright")
        @Expose
        private String copyright;
        @SerializedName("co_id")
        @Expose
        private Integer coId;
        @SerializedName("ad_status")
        @Expose
        private Integer adStatus;
        @SerializedName("license_status")
        @Expose
        private Integer licenseStatus;
        @SerializedName("lyrics_file")
        @Expose
        private String lyricsFile;
        @SerializedName("vn_only")
        @Expose
        private Boolean vnOnly;
        @SerializedName("source")
        @Expose
        private Source source;
        @SerializedName("download_disable")
        @Expose
        private Integer downloadDisable;
        @SerializedName("link_download")
        @Expose
        private LinkDownload linkDownload;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;

        /**
         * @return The songId
         */
        public Integer getSongId() {
            return songId;
        }

        /**
         * @param songId The song_id
         */
        public void setSongId(Integer songId) {
            this.songId = songId;
        }

        /**
         * @return The videoIdEncode
         */
        public String getVideoIdEncode() {
            return videoIdEncode;
        }

        /**
         * @param videoIdEncode The video_id_encode
         */
        public void setVideoIdEncode(String videoIdEncode) {
            this.videoIdEncode = videoIdEncode;
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
         * @return The isrc
         */
        public String getIsrc() {
            return isrc;
        }

        /**
         * @param isrc The isrc
         */
        public void setIsrc(String isrc) {
            this.isrc = isrc;
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
        public String getCopyright() {
            return copyright;
        }

        /**
         * @param copyright The copyright
         */
        public void setCopyright(String copyright) {
            this.copyright = copyright;
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
         * @return The lyricsFile
         */
        public String getLyricsFile() {
            return lyricsFile;
        }

        /**
         * @param lyricsFile The lyrics_file
         */
        public void setLyricsFile(String lyricsFile) {
            this.lyricsFile = lyricsFile;
        }

        /**
         * @return The vnOnly
         */
        public Boolean getVnOnly() {
            return vnOnly;
        }

        /**
         * @param vnOnly The vn_only
         */
        public void setVnOnly(Boolean vnOnly) {
            this.vnOnly = vnOnly;
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
         * @return The downloadDisable
         */
        public Integer getDownloadDisable() {
            return downloadDisable;
        }

        /**
         * @param downloadDisable The download_disable
         */
        public void setDownloadDisable(Integer downloadDisable) {
            this.downloadDisable = downloadDisable;
        }

        /**
         * @return The linkDownload
         */
        public LinkDownload getLinkDownload() {
            return linkDownload;
        }

        /**
         * @param linkDownload The link_download
         */
        public void setLinkDownload(LinkDownload linkDownload) {
            this.linkDownload = linkDownload;
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
         * @return The thumbnail
         */
        @Bindable
        public String getThumbnail() {
            return thumbnail;
        }

        /**
         * @param thumbnail The thumbnail
         */
        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

    }

    class LinkDownload {

        @SerializedName("128")
        @Expose
        private String _128;
        @SerializedName("lossless")
        @Expose
        private String lossless;
        @SerializedName("320")
        @Expose
        private String _320;

        /**
         * @return The _128
         */
        public String get128() {
            return _128;
        }

        /**
         * @param _128 The 128
         */
        public void set128(String _128) {
            this._128 = _128;
        }

        /**
         * @return The lossless
         */
        public String getLossless() {
            return lossless;
        }

        /**
         * @param lossless The lossless
         */
        public void setLossless(String lossless) {
            this.lossless = lossless;
        }

        /**
         * @return The _320
         */
        public String get320() {
            return _320;
        }

        /**
         * @param _320 The 320
         */
        public void set320(String _320) {
            this._320 = _320;
        }

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

        @SerializedName("128")
        @Expose
        private String _128;
        @SerializedName("lossless")
        @Expose
        private String lossless;
        @SerializedName("320")
        @Expose
        private String _320;

        /**
         * @return The _128
         */
        public String get128() {
            return _128;
        }

        /**
         * @param _128 The 128
         */
        public void set128(String _128) {
            this._128 = _128;
        }

        /**
         * @return The lossless
         */
        public String getLossless() {
            return lossless;
        }

        /**
         * @param lossless The lossless
         */
        public void setLossless(String lossless) {
            this.lossless = lossless;
        }

        /**
         * @return The _320
         */
        public String get320() {
            return _320;
        }

        /**
         * @param _320 The 320
         */
        public void set320(String _320) {
            this._320 = _320;
        }
    }
}