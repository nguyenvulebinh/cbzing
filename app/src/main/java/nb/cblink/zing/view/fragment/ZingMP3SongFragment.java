package nb.cblink.zing.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import de.hdodenhof.circleimageview.CircleImageView;
import nb.cblink.zing.R;
import nb.cblink.zing.databinding.ZingMP3SongBinding;
import nb.cblink.zing.viewmodel.ZingMP3SongModelView;

/**
 * Created by nguyenbinh on 12/09/2016.
 */

public class ZingMP3SongFragment extends Fragment{
    ZingMP3SongModelView modelView;
    private View layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ZingMP3SongBinding binding = DataBindingUtil.inflate(inflater, R.layout.zingmp3_song_fragment, container, false);
        layout = binding.getRoot();
        modelView = new ZingMP3SongModelView(getActivity(), (CircleImageView) layout.findViewById(R.id.icon_song), (Spinner) layout.findViewById(R.id.choose_quanlity_song), (SeekBar) layout.findViewById(R.id.seekBar));
        binding.setZ3songmv(modelView);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();

        if(bundle != null && bundle.containsKey(ZingMP3SongModelView.ID)) {
            String id = bundle.getString(ZingMP3SongModelView.ID);
            modelView.getSongInfo(id);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        modelView.stopStreamMusic();
    }
}
