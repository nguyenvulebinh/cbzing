package nb.cblink.zing.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nb.cblink.zing.R;
import nb.cblink.zing.databinding.TrainFragmentBinding;
import nb.cblink.zing.viewmodel.TrainModelView;

/**
 * Created by nguyenbinh on 17/09/2016.
 */

public class TrainFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TrainFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.train_fragment, container, false);
        View layout = binding.getRoot();
        TrainModelView modelView = new TrainModelView(layout.getContext(), inflater);
        binding.setTmv(modelView);
        return layout;
    }
}
