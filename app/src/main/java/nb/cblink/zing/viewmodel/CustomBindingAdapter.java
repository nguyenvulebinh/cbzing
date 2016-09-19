package nb.cblink.zing.viewmodel;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import nb.cblink.zing.R;

/**
 * Created by phanirajabhandari on 7/8/15.
 */
public class CustomBindingAdapter {


    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext())
                .load("http://image.mp3.zdn.vn/" + url)
                .error(R.drawable.song_icon)
                .into(imageView);
    }

    //http://image.mp3.zdn.vn/tv_media_854_480/
}
