package com.rngraphs.graphs;

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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphViewManager extends SimpleViewManager<LineChart> {
    public static final String REACT_CLASS = "GraphViewManager";
    private ThemedReactContext reactContext = null;

    private LineChart lineChart = null;
    private LineData lineData = null;

    @ReactProp(name = "assembleData")
    public void assembleData(LineChart view, ReadableMap readableMap) {
        System.out.println(readableMap);

        try {
            HashMap<String, Object> hashMap = readableMap.toHashMap();

            // RETRIEVE VISIBLE-X
            double visibleXDouble = (double) hashMap.get("visibleX");
            int visibleX = ((int) visibleXDouble);

            // RETRIEVE LABELS
            List<String> labelList = (List<String>) hashMap.get("labels");

            // RETRIEVE DATA
            List<HashMap<String, Double>> dataList = (List<HashMap<String, Double>>) hashMap.get("data");

            List<Entry> entryList = new ArrayList<>();
            for (HashMap<String, Double> record : dataList) {
                float x = record.get("x").floatValue();
                float y = record.get("y").floatValue();
                entryList.add(new Entry(x, y));
            }

            //ASSEMBLE DATA
            LineDataSet lineDataSet = new LineDataSet(entryList, "teste");
            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(lineDataSet);
            lineData = new LineData(dataSets);
            lineData.setDrawValues(false);

            //ASSEMBLE LABELS
            //List<String> xAxisValues = new ArrayList<>(labelList);
            lineChart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(labelList));
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setLabelRotationAngle(-90);
            xAxis.setLabelCount(labelList.size());
            xAxis.setGranularityEnabled(true);

            lineChart.setData(lineData);
            lineChart.invalidate();
            lineChart.setVisibleXRangeMaximum(visibleX);
        }catch (Exception e){
            System.out.println(e.toString());
            lineChart.setNoDataText(e.toString());
            lineChart.setNoDataTextColor(ColorTemplate.rgb("#FF0000"));
            lineChart.setData(null);
            lineChart.invalidate();
        }
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
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected LineChart createViewInstance(ThemedReactContext reactContext) {
        this.reactContext = reactContext;

        setupGraphSettings();

        return lineChart;
    }
}
