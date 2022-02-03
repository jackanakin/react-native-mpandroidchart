package com.rngraphs.graphs;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
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
import java.util.HashMap;
import java.util.List;

public class GraphViewManager extends SimpleViewManager<LineChart> {
    public static final String REACT_CLASS = "GraphViewManager";
    private ThemedReactContext reactContext = null;

    private LineChart lineChart = null;
    private LineData lineData;
    private List<Entry> entryList = new ArrayList<>();

    @ReactProp(name = "assembleData")
    public void assembleData(LineChart view, ReadableMap data) {
        System.out.println("GraphViewManager:assembleData");

        HashMap<String, Object> hashMap = data.toHashMap();
        System.out.println(hashMap);

        String label = (String) hashMap.get("label");
        System.out.println(label);

        List<HashMap<String, String>> dataList = (List<HashMap<String, String>>) hashMap.get("data");
        System.out.println(dataList);
        for (HashMap<String, String> record : dataList) {
            System.out.println("key="+record.get("key") + ", value="+record.get("value"));
        }

        lineChart.setData(lineData);
        lineChart.setVisibleXRangeMaximum(10);
        lineChart.invalidate();
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    private void setupGraphSettings(){
        lineChart = new LineChart(reactContext);
        lineChart.getLegend().setEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDescription(null);
        lineChart.getAxisRight().setDrawLabels(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.setDrawBorders(false);
    }

    @Override
    protected LineChart createViewInstance(ThemedReactContext reactContext) {
        System.out.println("GraphViewManager:createViewInstance=START");
        this.reactContext = reactContext;
        setupGraphSettings();

        SampleService sampleService = new SampleService();
        DataItem item = sampleService.getSamples();
        List<String> xLabels = new ArrayList<String>();

        int x = 0;
        for (DataEntry entry: item.getDataset() ){
            entryList.add(new Entry(x, entry.getFormattedValue()));
            xLabels.add("14:"+x);
            x++;
        }

        LineDataSet lineDataSet = new LineDataSet(entryList, "teste");
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(lineDataSet);

        lineData = new LineData(dataSets);
        lineData.setDrawValues(false);

        List<String> xAxisValues = new ArrayList<>(xLabels);
        lineChart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setLabelRotationAngle(-90);
        xAxis.setLabelCount(xAxisValues.size());
        xAxis.setGranularityEnabled(true);



        System.out.println("GraphViewManager:createViewInstance=END");
        return lineChart;
    }
}
