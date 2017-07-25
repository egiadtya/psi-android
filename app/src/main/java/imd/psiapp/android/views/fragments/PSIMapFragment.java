package imd.psiapp.android.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import imd.psiapp.android.R;
import imd.psiapp.android.views.activities.MainActivity;

/**
 * this class for showing psi on map view
 * Created by egiadtya on 7/25/17.
 */

public class PSIMapFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_psi_map, container, false);
        ButterKnife.bind(this, view);
        ((MainActivity) getActivity()).setTitle("PSI Map");
        return view;
    }
}
