package com.gchartslibrary;

/**
 * @author Georgel Aron
 */
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.gchartslibrary.chart.ChartBuilder;
import com.gchartslibrary.model.ChartDataset;
import com.gchartslibrary.model.DatasetSelection;
import com.gchartslibrary.renderer.DatasetRenderer;
import com.gchartslibrary.renderer.Renderer;

public class DemoPieActivity extends Activity {

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
            // seriesRenderer.setPopout(true);
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        // defaultRenderer.setShowLabels(false);
        defaultRenderer.setShowLegend(false);

        final ChartView chartP = ChartBuilder.createPieChartView(getBaseContext(), distributionSeries, defaultRenderer);
        final ChartView chartD = ChartBuilder.createDoughnutChartView(getBaseContext(), distributionSeries, defaultRenderer);

        chartP.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                DatasetSelection seriesSelection = chartP.getCurrentSeriesAndPoint();
                PieChart chart = (PieChart) chartP.getChart();
                if (seriesSelection == null) {
                    chart.removeDatasetRendererClicked();
                    chartP.repaint();
                } else {
                    chart.setDatasetRendererClicked(seriesSelection.getPointIndex());
                    chartP.repaint();
                }

            }
        });

        chartD.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                DatasetSelection seriesSelection = chartD.getCurrentSeriesAndPoint();
                DoughnutChart chart = (DoughnutChart) chartD.getChart();
                if (seriesSelection == null) {
                    chart.removeDatasetRendererClicked();
                    chartD.repaint();
                } else {
                    chart.setDatasetRendererClicked(seriesSelection.getPointIndex());
                    chartD.repaint();
                }
            }
        });

        viewPie.addView(chartP);
        viewDoughnut.addView(chartD);
    }

}
