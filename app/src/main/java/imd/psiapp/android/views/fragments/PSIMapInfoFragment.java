package imd.psiapp.android.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import imd.psiapp.android.R;
import imd.psiapp.android.models.PSIReadings;

/**
 * this is page for showing psi info based on region
 * Created by egiadtya on 7/26/17.
 */

public class PSIMapInfoFragment extends BottomSheetDialogFragment {
    public static final String REGION_NAME = "region_name";
    public static final String PSI_READINGS = "readings_item";
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.info_container)
    LinearLayout infoContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_psi_map_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    /**
     * for initialize view, this method will be generate psi info to the view
     */
    private void initView() {
        final PSIReadings psiReadings = getArguments().getParcelable(PSI_READINGS);
        final String region = getArguments().getString(REGION_NAME);
        if (psiReadings != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    HashMap<String, Double> psiBaseOnRegion = psiReadings.getPSIBaseOnRegion(region);
                    for (Map.Entry<String, Double> entry : psiBaseOnRegion.entrySet()) {
                        generatePSIInfoView(entry.getKey(), entry.getValue());
                    }
                }
            });

        }
        tvRegion.setText(region);
    }

    /**
     * for generate psi info view
     *
     * @param title {@link String} of psi title
     * @param value double on psi value
     */
    @SuppressLint("DefaultLocale")
    private void generatePSIInfoView(String title, double value) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_psi_info_item, infoContainer, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvValue = (TextView) view.findViewById(R.id.tv_value);
        tvTitle.setText(title);
        tvValue.setText(String.format("%.2f", value));
        infoContainer.addView(view);
    }
}
