package com.gchartslibrary.chart;

import android.content.Context;

import com.gchartslibrary.ChartView;
import com.gchartslibrary.DoughnutChart;
import com.gchartslibrary.PieChart;
import com.gchartslibrary.model.ChartDataset;
import com.gchartslibrary.renderer.Renderer;

public class ChartBuilder {

    public static ChartView createPieChartView(Context context, ChartDataset dataset, Renderer renderer) {
        PieChart chart = new PieChart(dataset, renderer);
        return new ChartView(context, chart);
    }

    public static ChartView createDoughnutChartView(Context context, ChartDataset dataset, Renderer renderer) {
        DoughnutChart chart = new DoughnutChart(dataset, renderer);
        return new ChartView(context, chart);
    }

}
