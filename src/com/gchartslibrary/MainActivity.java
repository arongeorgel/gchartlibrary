package com.gchartslibrary;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.gchartslibrary.chart.ChartBuilder;
import com.gchartslibrary.model.ChartDataset;
import com.gchartslibrary.renderer.DatasetRenderer;
import com.gchartslibrary.renderer.Renderer;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout viewPie = (RelativeLayout) findViewById(R.id.pie);
        RelativeLayout viewDoughnut = (RelativeLayout) findViewById(R.id.doughnut);

        // Pie Chart Section Names
        String[] code = new String[] { "44.56%", "24.73%", "15.21%", "8.4%", "7.1%" };

        // Pie Chart Section Value
        final double[] distribution = { 44.56, 24.73, 15.21, 8.4, 7.1 };

        // Color of each Pie Chart Sections

        // Instantiating CategorySeries to plot Pie Chart
        ChartDataset distributionSeries = new ChartDataset("");
        for (int i = 0; i < distribution.length; i++) {
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(code[i], distribution[i]);
        }

        int[] colors = { Color.BLACK, Color.BLUE, Color.GRAY, Color.GREEN, Color.RED };
        DatasetRenderer seriesRenderer;
        Renderer defaultRenderer = new Renderer();
        for (int i = 0; i < distribution.length; i++) {
            seriesRenderer = new DatasetRenderer();
            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(true);
            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        final ChartView chartP = ChartBuilder.createPieChartView(getBaseContext(), distributionSeries, defaultRenderer);
        final ChartView chartD = ChartBuilder.createDoughnutChartView(getBaseContext(), distributionSeries, defaultRenderer);

        viewPie.addView(chartP);
        viewDoughnut.addView(chartD);
    }

}
