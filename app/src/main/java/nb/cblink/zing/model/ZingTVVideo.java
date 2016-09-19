package nb.cblink.zing.model;

/**
 * Created by nguyenbinh on 12/09/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ZingTVVideo {

    @SerializedName("response")
    @Expose
    private Response response;

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

    public class DownloadUrl {

        @SerializedName("Video")
        @Expose
        private String video;

        /**
         * @return The video
         */
        public String getVideo() {
            return video;
        }

        /**
         * @param video The Video
         */
        public void setVideo(String video) {
            this.video = video;
        }

    }
    class Hls {}


    public class OtherUrl {

        @SerializedName("Video3GP")
        @Expose
        private String video3GP;
        @SerializedName("Video480")
        @Expose
        private String video480;
        @SerializedName("Video720")
        @Expose
        private String video720;

        /**
         * @return The video3GP
         */
        public String getVideo3GP() {
            return video3GP;
        }

        /**
         * @param video3GP The Video3GP
         */
        public void setVideo3GP(String video3GP) {
            this.video3GP = video3GP;
        }

        /**
         * @return The video480
         */
        public String getVideo480() {
            return video480;
        }

        /**
         * @param video480 The Video480
         */
        public void setVideo480(String video480) {
            this.video480 = video480;
        }

        /**
         * @return The video720
         */
        public String getVideo720() {
            return video720;
        }

        /**
         * @param video720 The Video720
         */
        public void setVideo720(String video720) {
            this.video720 = video720;
        }

    }


    public class ProgramGenre {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        /**
         * @return The id
         */
        public Integer getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * @return The name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name The name
         */
        public void setName(String name) {
            this.name = name;
        }

    }


    public class Response {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("full_name")
        @Expose
        private String fullName;
        @SerializedName("episode")
        @Expose
        private Integer episode;
        @SerializedName("release_date")
        @Expose
        private String releaseDate;
        @SerializedName("duration")
        @Expose
        private Integer duration;
        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;
        @SerializedName("file_url")
        @Expose
        private String fileUrl;
        @SerializedName("other_url")
        @Expose
        private OtherUrl otherUrl;
        @SerializedName("download_url")
        @Expose
        private DownloadUrl downloadUrl;
        @SerializedName("hls")
        @Expose
        private Hls hls;
        @SerializedName("link_url")
        @Expose
        private String linkUrl;
        @SerializedName("program_id")
        @Expose
        private Integer programId;
        @SerializedName("program_name")
        @Expose
        private String programName;
        @SerializedName("program_thumbnail")
        @Expose
        private String programThumbnail;
        @SerializedName("program_genre")
        @Expose
        private List<ProgramGenre> programGenre = new ArrayList<ProgramGenre>();
        @SerializedName("listen")
        @Expose
        private Integer listen;
        @SerializedName("comment")
        @Expose
        private Integer comment;
        @SerializedName("like")
        @Expose
        private Integer like;
        @SerializedName("rating")
        @Expose
        private Double rating;
        @SerializedName("ova_url")
        @Expose
        private String ovaUrl;
        @SerializedName("require_premium")
        @Expose
        private Boolean requirePremium;
        @SerializedName("next_media_id")
        @Expose
        private Integer nextMediaId;
        @SerializedName("prev_media_id")
        @Expose
        private Integer prevMediaId;
        @SerializedName("tracking")
        @Expose
        private Tracking tracking;
        @SerializedName("user_info")
        @Expose
        private UserInfo userInfo;

        /**
         * @return The id
         */
        public Integer getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(Integer id) {
            this.id = id;
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
         * @return The fullName
         */
        public String getFullName() {
            return fullName;
        }

        /**
         * @param fullName The full_name
         */
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        /**
         * @return The episode
         */
        public Integer getEpisode() {
            return episode;
        }

        /**
         * @param episode The episode
         */
        public void setEpisode(Integer episode) {
            this.episode = episode;
        }

        /**
         * @return The releaseDate
         */
        public String getReleaseDate() {
            return releaseDate;
        }

        /**
         * @param releaseDate The release_date
         */
        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
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
         * @return The fileUrl
         */
        public String getFileUrl() {
            return fileUrl;
        }

        /**
         * @param fileUrl The file_url
         */
        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        /**
         * @return The otherUrl
         */
        public OtherUrl getOtherUrl() {
            return otherUrl;
        }

        /**
         * @param otherUrl The other_url
         */
        public void setOtherUrl(OtherUrl otherUrl) {
            this.otherUrl = otherUrl;
        }

        /**
         * @return The downloadUrl
         */
        public DownloadUrl getDownloadUrl() {
            return downloadUrl;
        }

        /**
         * @param downloadUrl The download_url
         */
        public void setDownloadUrl(DownloadUrl downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        /**
         * @return The hls
         */
        public Hls getHls() {
            return hls;
        }

        /**
         * @param hls The hls
         */
        public void setHls(Hls hls) {
            this.hls = hls;
        }

        /**
         * @return The linkUrl
         */
        public String getLinkUrl() {
            return linkUrl;
        }

        /**
         * @param linkUrl The link_url
         */
        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        /**
         * @return The programId
         */
        public Integer getProgramId() {
            return programId;
        }

        /**
         * @param programId The program_id
         */
        public void setProgramId(Integer programId) {
            this.programId = programId;
        }

        /**
         * @return The programName
         */
        public String getProgramName() {
            return programName;
        }

        /**
         * @param programName The program_name
         */
        public void setProgramName(String programName) {
            this.programName = programName;
        }

        /**
         * @return The programThumbnail
         */
        public String getProgramThumbnail() {
            return programThumbnail;
        }

        /**
         * @param programThumbnail The program_thumbnail
         */
        public void setProgramThumbnail(String programThumbnail) {
            this.programThumbnail = programThumbnail;
        }

        /**
         * @return The programGenre
         */
        public List<ProgramGenre> getProgramGenre() {
            return programGenre;
        }

        /**
         * @param programGenre The program_genre
         */
        public void setProgramGenre(List<ProgramGenre> programGenre) {
            this.programGenre = programGenre;
        }

        /**
         * @return The listen
         */
        public Integer getListen() {
            return listen;
        }

        /**
         * @param listen The listen
         */
        public void setListen(Integer listen) {
            this.listen = listen;
        }

        /**
         * @return The comment
         */
        public Integer getComment() {
            return comment;
        }

        /**
         * @param comment The comment
         */
        public void setComment(Integer comment) {
            this.comment = comment;
        }

        /**
         * @return The like
         */
        public Integer getLike() {
            return like;
        }

        /**
         * @param like The like
         */
        public void setLike(Integer like) {
            this.like = like;
        }

        /**
         * @return The rating
         */
        public Double getRating() {
            return rating;
        }

        /**
         * @param rating The rating
         */
        public void setRating(Double rating) {
            this.rating = rating;
        }

        /**
         * @return The ovaUrl
         */
        public String getOvaUrl() {
            return ovaUrl;
        }

        /**
         * @param ovaUrl The ova_url
         */
        public void setOvaUrl(String ovaUrl) {
            this.ovaUrl = ovaUrl;
        }

        /**
         * @return The requirePremium
         */
        public Boolean getRequirePremium() {
            return requirePremium;
        }

        /**
         * @param requirePremium The require_premium
         */
        public void setRequirePremium(Boolean requirePremium) {
            this.requirePremium = requirePremium;
        }

        /**
         * @return The nextMediaId
         */
        public Integer getNextMediaId() {
            return nextMediaId;
        }

        /**
         * @param nextMediaId The next_media_id
         */
        public void setNextMediaId(Integer nextMediaId) {
            this.nextMediaId = nextMediaId;
        }

        /**
         * @return The prevMediaId
         */
        public Integer getPrevMediaId() {
            return prevMediaId;
        }

        /**
         * @param prevMediaId The prev_media_id
         */
        public void setPrevMediaId(Integer prevMediaId) {
            this.prevMediaId = prevMediaId;
        }

        /**
         * @return The tracking
         */
        public Tracking getTracking() {
            return tracking;
        }

        /**
         * @param tracking The tracking
         */
        public void setTracking(Tracking tracking) {
            this.tracking = tracking;
        }

        /**
         * @return The userInfo
         */
        public UserInfo getUserInfo() {
            return userInfo;
        }

        /**
         * @param userInfo The user_info
         */
        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

    }

    class Tracking {}


    class UserInfo {

        @SerializedName("is_premium")
        @Expose
        private Boolean isPremium;
        @SerializedName("from_time")
        @Expose
        private Integer fromTime;
        @SerializedName("to_time")
        @Expose
        private Integer toTime;

        /**
         * @return The isPremium
         */
        public Boolean getIsPremium() {
            return isPremium;
        }

        /**
         * @param isPremium The is_premium
         */
        public void setIsPremium(Boolean isPremium) {
            this.isPremium = isPremium;
        }

        /**
         * @return The fromTime
         */
        public Integer getFromTime() {
            return fromTime;
        }

        /**
         * @param fromTime The from_time
         */
        public void setFromTime(Integer fromTime) {
            this.fromTime = fromTime;
        }

        /**
         * @return The toTime
         */
        public Integer getToTime() {
            return toTime;
        }

        /**
         * @param toTime The to_time
         */
        public void setToTime(Integer toTime) {
            this.toTime = toTime;
        }

    }

}