package nb.cblink.zing.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;

import de.hdodenhof.circleimageview.CircleImageView;
import nb.cblink.zing.R;
import nb.cblink.zing.databinding.ZingMP3AlbumBinding;
import nb.cblink.zing.viewmodel.ZingMP3AlbumSongModelView;
import nb.cblink.zing.viewmodel.ZingMP3SongModelView;

/**
 * Created by nguyenbinh on 12/09/2016.
 */

public class ZingMP3AlbumFragment extends Fragment {
    ZingMP3AlbumSongModelView modelView;
    private View layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ZingMP3AlbumBinding binding = DataBindingUtil.inflate(inflater, R.layout.zingmp3_album_fragment, container, false);
        layout = binding.getRoot();
        modelView = new ZingMP3AlbumSongModelView(getActivity(), (RecyclerView)layout.findViewById(R.id.recyclerViewListSong), (SeekBar)layout.findViewById(R.id.seekBar), (Spinner)layout.findViewById(R.id.choose_quanlity_song), (CircleImageView) layout.findViewById(R.id.icon_song));
        binding.setZ3albummv(modelView);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();

        if(bundle != null && bundle.containsKey(ZingMP3AlbumSongModelView.ID)) {
            String id = bundle.getString(ZingMP3AlbumSongModelView.ID);
            modelView.getAlbumDescrip(id);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        modelView.stopStreamMusic();
    }
}
