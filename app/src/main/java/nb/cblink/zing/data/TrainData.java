package nb.cblink.zing.data;

import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;

import nb.cblink.zing.R;

import static android.content.ContentValues.TAG;

/**
 * Created by nguyenbinh on 18/09/2016.
 */

public class TrainData {
    public static final int FINISH = -1;
    public static final int END = -2;
    public static final int OPEN_WEB = -3;
    public static final int DISMISS = -4;

    public static int imageData[][] = {{R.drawable.app_zingmp3_1, R.drawable.app_zingmp3_2, R.drawable.app_zingmp3_3},
            {R.drawable.web_zing_setting_0, R.drawable.web_zingmp3_1, R.drawable.web_zingmp3_2, R.drawable.web_zingmp3_3, R.drawable.web_zingmp3_4, R.drawable.web_zingmp3_5},
            {R.drawable.app_zingtv_1, R.drawable.app_zingtv_2, R.drawable.app_zingtv_3},
            {R.drawable.web_zing_setting_0,R.drawable.web_zingmp3_1, R.drawable.web_zingtv_1, R.drawable.web_zingtv_2, R.drawable.web_zingtv_3, R.drawable.web_zingtv_4}
    };
    int index;
    int type;
    int step;
    String url[] = {"https://www.youtube.com/watch?v=W6fOK8H9u6E",
            "https://www.youtube.com/watch?v=6NhZGLNuEu8",
            "https://www.youtube.com/watch?v=ZUBZZ5pmv5I",
            "https://www.youtube.com/watch?v=zJt_2SPxsPs"
    };

    public TrainData(int type) {
        index = 0;
        this.type = type;
        step = 1;
    }

    public int getStep(){
        return step;
    }

    public int getNext() {
        if (index < imageData[type].length - 1 && index != FINISH) {
            index++;
            step = index + 1;
            if (index == imageData[type].length - 1) {
                index = FINISH;
                step = imageData[type].length;
                return imageData[type][imageData[type].length - 1];
            }
            return imageData[type][index];
        } else {
            return OPEN_WEB;
        }
    }

    public int getBack() {
        if (index > 0 && index != END) {
            index--;
            step = index + 1;
            if (index == 1) {
                index = END;
                step = 1;
                return imageData[type][0];
            }
            return imageData[type][index];
        }else if(index == FINISH) {
            index = imageData[type].length - 2;
            step = index + 1;
            return imageData[type][index];
        }else {
            return DISMISS;
        }
    }

    public String urlTrain() {
        return url[type];
    }

    public int getIndex() {
        return index;
    }

}

