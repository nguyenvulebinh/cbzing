package nb.cblink.zing.viewmodel;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import nb.cblink.zing.databinding.AlbumItemSongBinding;

/**
 * Created by nguyenbinh on 15/09/2016.
 */

public class ZingMP3AlbumSongViewHolder extends RecyclerView.ViewHolder {
    private AlbumItemSongBinding albumItemSongBinding;
    public ZingMP3AlbumSongViewHolder(AlbumItemSongBinding albumItemSongBinding) {
        super(albumItemSongBinding.getRoot());
        this.albumItemSongBinding = albumItemSongBinding;
    }

    public AlbumItemSongBinding getAlbumItemSongBinding(){
        return albumItemSongBinding;
    }
}
