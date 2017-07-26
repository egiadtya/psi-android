package imd.psiapp.android.views.fragments;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import imd.psiapp.android.models.PSI;
import imd.psiapp.android.models.PSIItem;
import imd.psiapp.android.models.PSIReadingsItem;

/**
 * this fragment class for showing Pollutant sub indices page
 * Created by egiadtya on 7/26/17.
 */

public class PollutantSubIndicesFragment extends ChartFragment {
    @Override
    protected void setChartData(LineChart chart, PSI data) {
        HashMap<String, List<Double>> subIndicesMap = generateChartData(data);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        int yAxis = 0;
        for (Map.Entry<String, List<Double>> mapEntry : subIndicesMap.entrySet()) {
            ArrayList<Entry> yValues = new ArrayList<>();
            for (int xAxis = 0; xAxis < mapEntry.getValue().size(); xAxis++) {
                float xVal = xAxis + 1;
                Entry entry = new Entry(xVal, mapEntry.getValue().get(xAxis).floatValue());
                yValues.add(entry);
            }
            LineDataSet d = new LineDataSet(yValues, mapEntry.getKey().replace("SubIndex", ""));
            d.setLineWidth(2.5f);
            d.setCircleRadius(4f);

            int color = colorsTemplate[yAxis % colorsTemplate.length];
            d.setColor(color);
            d.setCircleColor(color);
            dataSets.add(d);
            yAxis++;
        }

        LineData lineData = new LineData(dataSets);
        chart.setData(lineData);
        Legend legend = chart.getLegend();
        legend.setEnabled(true);
        chart.invalidate();
    }

    /**
     * method for prepare chart data from psi data model
     *
     * @param data {@link PSI}
     */
    private HashMap<String, List<Double>> generateChartData(PSI data) {
        HashMap<String, List<Double>> subIndicesMap = new HashMap<>();
        for (int yAxis = 0; yAxis < data.getPsiItems().size(); yAxis++) {
            PSIItem psiItem = data.getPsiItems().get(yAxis);
            HashMap<String, Double> psiBaseOnRegion = psiItem.getPsiReadings().getPSIBaseOnRegion(PSIReadingsItem.NATIONAL);
            for (Map.Entry<String, Double> psiEntry : psiBaseOnRegion.entrySet()) {
                if (psiEntry.getKey().contains("SubIndex")) {
                    if (subIndicesMap.get(psiEntry.getKey()) == null) {
                        subIndicesMap.put(psiEntry.getKey(), new ArrayList<Double>());
                        subIndicesMap.get(psiEntry.getKey()).add(psiEntry.getValue());
                    } else {
                        subIndicesMap.get(psiEntry.getKey()).add(psiEntry.getValue());
                    }
                }
            }
        }
        return subIndicesMap;
    }
}
