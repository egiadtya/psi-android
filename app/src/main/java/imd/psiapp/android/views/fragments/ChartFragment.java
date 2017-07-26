package imd.psiapp.android.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import imd.psiapp.android.R;
import imd.psiapp.android.models.PSI;
import imd.psiapp.android.services.api.ApiCallback;
import imd.psiapp.android.services.api.ApiService;

/**
 * this is base class for showing chart page
 * Created by egiadtya on 7/26/17.
 */

public abstract class ChartFragment extends Fragment {
    @BindView(R.id.line_chart)
    LineChart lineChart;
    @BindView(R.id.animator)
    ViewAnimator animator;

    protected int[] colorsTemplate = new int[]{
            ColorTemplate.COLORFUL_COLORS[0],
            ColorTemplate.COLORFUL_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2],
            ColorTemplate.VORDIPLOM_COLORS[3],
            ColorTemplate.VORDIPLOM_COLORS[4],
            ColorTemplate.MATERIAL_COLORS[0],
            ColorTemplate.MATERIAL_COLORS[1]
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initChart();
        populateData();
    }

    /**
     * for initialize chart view
     */
    private void initChart() {
        lineChart.setDrawGridBackground(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setPinchZoom(true);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(true);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setDrawAxisLine(false);
        lineChart.invalidate();
    }

    /**
     * for insert data into chart view
     */
    protected abstract void setChartData(LineChart chart, PSI data);

    /**
     * for getting psi data from server
     */
    private void populateData() {
        ApiService.getInstance().getPsiByDate(getDate(), new ApiCallback<PSI>() {
            @Override
            public void onSuccess(final PSI data) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setChartData(lineChart, data);
                    }
                });
                animator.setDisplayedChild(1);
            }

            @Override
            public void onFailed(String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                animator.setDisplayedChild(1);
            }
        });
    }

    /**
     * @return String of current date
     */
    public String getDate() {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return df.format(new Date());
    }
}
