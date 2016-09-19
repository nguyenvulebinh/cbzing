package nb.cblink.zing.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import nb.cblink.zing.R;
import nb.cblink.zing.data.DownloadResourceBroadcastReceiver;
import nb.cblink.zing.databinding.AlbumItemSongBinding;
import nb.cblink.zing.model.AlbumSong;

import static nb.cblink.zing.viewmodel.ZingMP3AlbumSongModelView.URL_IMAGE;

/**
 * Created by nguyenbinh on 15/09/2016.
 */

public class ZingMP3AlbumSongAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AlbumSong albumSong;
    private List<AlbumSong.Doc> mDocs = new ArrayList<>();
    private Context context;
    public ZingMP3AlbumSongModelView modelView;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private OnLoadMoreListener mOnLoadMoreListener;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public ZingMP3AlbumSongAdapter(RecyclerView recyclerView, final AlbumSong albumSong, Context context, ZingMP3AlbumSongModelView modelView) {
        this.albumSong = albumSong;
        this.context = context;
        this.modelView = modelView;

        for (int i = 0; i < 10; i++) {
            if (i < albumSong.getDocs().size()) {
                mDocs.add(albumSong.getDocs().get(i));
            } else {
                break;
            }
        }

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //Cai dat ScollListener cho recycler view
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                if(totalItemCount < albumSong.getDocs().size()) {
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (mOnLoadMoreListener != null) {
                            mOnLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }
            }
        });

    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("ZingMP3AlbumSongAdapter", "possion: " + position);
        return mDocs.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return mDocs == null ? 0 : mDocs.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            AlbumItemSongBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_song, parent, false);
            return new ZingMP3AlbumSongViewHolder(binding);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_loading_item, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    public void addToLoadMore() {
        mDocs.add(null);
        this.notifyItemInserted(mDocs.size() - 1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ZingMP3AlbumSongViewHolder) {
            AlbumItemSongBinding binding = ((ZingMP3AlbumSongViewHolder) holder).getAlbumItemSongBinding();

            binding.setZ3albummv(modelView);
            binding.setSong(mDocs.get(position));
        } else {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void addMoreSongToShow() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDocs.remove(mDocs.size() - 1);
                ZingMP3AlbumSongAdapter.this.notifyItemRemoved(mDocs.size());

                //Load data
                int index = mDocs.size();
                int end = index + 10;
                for (int i = index; i < end; i++) {
                    if (i < albumSong.getDocs().size()) {
                        mDocs.add(albumSong.getDocs().get(i));
                    } else {
                        break;
                    }
                }
                ZingMP3AlbumSongAdapter.this.notifyDataSetChanged();
                ZingMP3AlbumSongAdapter.this.setLoaded();
            }
        }, 1000);
    }


    interface OnLoadMoreListener {
        void onLoadMore();
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }
}

