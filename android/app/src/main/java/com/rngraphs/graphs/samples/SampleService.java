package com.rngraphs.graphs.samples;


import com.rngraphs.graphs.models.DataEntry;
import com.rngraphs.graphs.models.DataItem;

public class SampleService {

    public DataItem getSamples(){
        int x = 60;
        DataEntry[] dataSet = new DataEntry[x];

        for (int xx=0; xx < x; xx++){
            DataEntry dataEntry = new DataEntry("14:" + xx, xx*2);
            dataSet[xx] = dataEntry;
        }

        DataItem item = new DataItem("ItemA", "#FFFFFF", dataSet);

        return item;
    }

}
