package nb.cblink.zing.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;

import nb.cblink.zing.R;
import nb.cblink.zing.databinding.ZingMP3AlbumBinding;
import nb.cblink.zing.databinding.ZingTVVideoBinding;
import nb.cblink.zing.viewmodel.ZingMP3AlbumSongModelView;
import nb.cblink.zing.viewmodel.ZingTVVideoModelView;

/**
 * Created by nguyenbinh on 12/09/2016.
 */

public class ZingTVVideoFragment extends Fragment {
    ZingTVVideoModelView modelView;
    private View layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ZingTVVideoBinding binding = DataBindingUtil.inflate(inflater, R.layout.zingtv_video_fragment, container, false);
        layout = binding.getRoot();
        modelView = new ZingTVVideoModelView(getActivity(), (ImageView) layout.findViewById(R.id.image_view_video), (Spinner) layout.findViewById(R.id.choose_quanlity_song));
        binding.setZtvvideomv(modelView);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();

        if(bundle != null && bundle.containsKey(ZingTVVideoModelView.ID)) {
            String id = bundle.getString(ZingTVVideoModelView.ID);
            modelView.getZingTVVideoInfo(id);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
