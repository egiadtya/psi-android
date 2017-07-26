package imd.psiapp.android.views.fragments;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.HashMap;

import imd.psiapp.android.models.PSI;

/**
 * this fragment class for showing 24-Hour PSI page
 * Created by egiadtya on 7/26/17.
 */

public class TwentyHourPSIFragment extends ChartFragment {
    @Override
    protected void setChartData(LineChart chart, PSI data) {
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int yAxis = 0; yAxis < data.getRegionMetadata().size(); yAxis++) {
            String region = data.getRegionMetadata().get(yAxis).getName();
            ArrayList<Entry> yValues = new ArrayList<>();
            for (int xAxis = 0; xAxis < data.getPsiItems().size(); xAxis++) {
                HashMap<String, Double> psiBaseOnRegion = data.getPsiItems().get(xAxis).getPsiReadings().getPSIBaseOnRegion(region);
                float xVal = xAxis + 1;
                Entry entry = new Entry(xVal, psiBaseOnRegion.get("psiTwentyFourHourly").floatValue());
                yValues.add(entry);
            }
            LineDataSet d = new LineDataSet(yValues, region);
            d.setLineWidth(2.5f);
            d.setCircleRadius(4f);

            int color = colorsTemplate[yAxis % colorsTemplate.length];
            d.setColor(color);
            d.setCircleColor(color);
            dataSets.add(d);
        }
        LineData lineData = new LineData(dataSets);
        chart.setData(lineData);
        Legend legend = chart.getLegend();
        legend.setEnabled(true);
        chart.invalidate();
    }
}
