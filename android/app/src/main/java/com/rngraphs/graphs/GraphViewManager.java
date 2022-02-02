package com.rngraphs.graphs;

import android.widget.TextView;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.rngraphs.graphs.models.DataEntry;
import com.rngraphs.graphs.models.DataItem;
import com.rngraphs.graphs.samples.SampleService;

import java.util.ArrayList;
import java.util.List;

public class GraphViewManager extends SimpleViewManager<LineChart> {
    LineChart lineChart = null;
    LineData lineData;
    List<Entry> entryList = new ArrayList<>();

    public static final String REACT_CLASS = "GraphViewManager";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected LineChart createViewInstance(ThemedReactContext reactContext) {
        lineChart = new LineChart(reactContext);
        SampleService sampleService = new SampleService();
        DataItem item = sampleService.getSamples();
        List<String> xLabels = new ArrayList<String>();

        int x = 0;
        for (DataEntry entry: item.getDataset() ){
            System.out.println("ENTRYSET="+entry.getFormattedValue());

            entryList.add(new Entry(x, entry.getFormattedValue()));
            xLabels.add("14:"+x);
            x++;
        }

        LineDataSet lineDataSet = new LineDataSet(entryList, "teste");
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(lineDataSet);

        lineData = new LineData(dataSets);
        lineData.setDrawValues(false);


        lineChart.getLegend().setEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDescription(null);
        lineChart.getAxisRight().setDrawLabels(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.setDrawBorders(false);

        List<String> xAxisValues = new ArrayList<>(xLabels);
        lineChart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setLabelRotationAngle(-90);
        xAxis.setLabelCount(xAxisValues.size());
        xAxis.setGranularityEnabled(true);

        lineChart.setData(lineData);
        lineChart.setVisibleXRangeMaximum(10);

        lineChart.invalidate();

        return lineChart;
    }
}
