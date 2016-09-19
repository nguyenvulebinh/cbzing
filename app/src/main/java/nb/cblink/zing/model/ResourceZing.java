package nb.cblink.zing.model;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nguyenbinh on 13/09/2016.
 */

public class ResourceZing {
    public static final String ZING_MP3_SONG_TYPE = "song";
    public static final String ZING_MP3_ALBUM_TYPE = "album";
    public static final String ZING_MP3_VIDEO_TYPE = "video";
    public static final String ZING_TV_VIDEO_TYPE = "zingtv";
    public static final String ZING_URL_REGEX = ".*http://(mp3.zing.vn|tv.zing.vn|m.mp3.zing.vn|m.tv.zing.vn)/(bai-hat|video-clip|album|video)/(.*)/(.*)\\..*";

    private String type = null;
    private String source = null;
    private String resourceID = null;
    private String title = null;
    private boolean isMatch = false;

    public ResourceZing(String input){
        if(input != null) {
            Pattern pattern = Pattern.compile(ZING_URL_REGEX);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                type = matcher.group(2);
                source = matcher.group(1);
                resourceID = matcher.group(4);
                title = matcher.group(3);
                isMatch = true;
                Log.d("ResourceZing", source + "|" + type + "|" + resourceID);

            }
        }
    }

    public boolean isMatch() {
        return isMatch;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
