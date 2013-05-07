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

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * An abstract class to be extend by all rendered charts
 * 
 * @author Georgel Aron
 * 
 */
public abstract class Chart {
    /**
     * The graphical representation of the chart.
     * 
     * @param canvas
     *            the canvas to paint to
     * @param x
     *            the top left x value of the view to draw to
     * @param y
     *            the top left y value of the view to draw to
     * @param width
     *            the width of the view to draw to
     * @param height
     *            the height of the view to draw to
     * @param paint
     *            the paint
     */
    public abstract void draw(Canvas canvas, int x, int y, int width, int height, Paint paint);

}
