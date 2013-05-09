/**
 * Copyright [2013] [Aron Georgel - {@link aron.georgel(at)gmail.com}]
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.gchartslibrary.chart;

import android.content.Context;

import com.gchartslibrary.ChartView;
import com.gchartslibrary.DoughnutChart;
import com.gchartslibrary.PieChart;
import com.gchartslibrary.model.ChartDataset;
import com.gchartslibrary.renderer.Renderer;

/**
 * Builder class for all types of charts
 * 
 * @author Georgel Aron
 * 
 */
public class ChartBuilder {

    /**
     * Create a new Piechart
     * 
     * @param context
     *            where view will be show
     * @param dataset
     *            for chart
     * @param renderer
     *            for chart
     * @return new {@link PieChart}
     */
    public static ChartView createPieChartView(Context context, ChartDataset dataset, Renderer renderer) {
        PieChart chart = new PieChart(dataset, renderer);
        return new ChartView(context, chart);
    }

    /**
     * Create a new Doughnut chart
     * 
     * @param context
     *            where view will be show
     * @param dataset
     *            for chart
     * @param renderer
     *            for chart
     * @return new {@link DoughnutChart}
     */
    public static ChartView createDoughnutChartView(Context context, ChartDataset dataset, Renderer renderer) {
        DoughnutChart chart = new DoughnutChart(dataset, renderer);
        return new ChartView(context, chart);
    }

}
