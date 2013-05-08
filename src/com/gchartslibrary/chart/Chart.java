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
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;

import com.gchartslibrary.model.ChartDataset;
import com.gchartslibrary.model.DatasetSelection;
import com.gchartslibrary.renderer.DatasetRenderer;
import com.gchartslibrary.renderer.Renderer;

/**
 * An abstract class to be extend by all rendered charts
 * 
 * @author Georgel Aron
 * 
 */
public abstract class Chart {
    public static final int WIDTH = 10;

    protected ChartDataset mDataset;
    protected Renderer mRenderer;

    protected int colorToDarker(int color, float value) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= value;
        return Color.HSVToColor(hsv);
    }

    /**
     * 
     * @param dataset
     *            the series dataset
     * @param renderer
     *            the series renderer
     */
    public Chart(ChartDataset dataset, Renderer renderer) {
        mDataset = dataset;
        mRenderer = renderer;
    }

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

    /**
     * Get the renderer of the chart
     * 
     * @return {@link Renderer}
     */
    public abstract Renderer getRenderer();

    /**
     * Given screen coordinates, returns the series and point indexes of a chart
     * element. If there is no chart element (line, point, bar, etc) at those
     * coordinates, null is returned.
     * 
     * @param screenPoint
     * @return the series and point indexes
     */
    public DatasetSelection getDatasetForScreenCoordinate(Point screenPoint, Point offsetPoint) {
        return null;
    }

    /**
     * Draws the chart background.
     * 
     * @param renderer
     *            the chart renderer
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
     *            the paint used for drawing
     * @param newColor
     *            if a new color is to be used
     * @param color
     *            the color to be used
     */
    protected void drawBackground(Renderer renderer, Canvas canvas, int x, int y, int width, int height, Paint paint, boolean newColor, int color) {
        if (renderer.isApplyBackgroundColor() || newColor) {
            if (newColor) {
                paint.setColor(color);
            } else {
                paint.setColor(renderer.getBackgroundColor());
            }
            paint.setStyle(Style.FILL);
            canvas.drawRect(x, y, x + width, y + height, paint);
        }
    }

    /**
     * Draws the chart legend.
     * 
     * @param canvas
     *            the canvas to paint to
     * @param renderer
     *            the series renderer
     * @param titles
     *            the titles to go to the legend
     * @param left
     *            the left X value of the area to draw to
     * @param right
     *            the right X value of the area to draw to
     * @param y
     *            the y value of the area to draw to
     * @param width
     *            the width of the area to draw to
     * @param height
     *            the height of the area to draw to
     * @param legendSize
     *            the legend size
     * @param paint
     *            the paint to be used for drawing
     * @param calculate
     *            if only calculating the legend size
     * 
     * @return the legend height
     */
    protected int drawLegend(Canvas canvas, Renderer renderer, String[] titles, int left, int right, int y, int width, int height, int legendSize,
            Paint paint, boolean calculate) {
        float size = 32;
        if (renderer.isShowLegend()) {
            float currentX = left;
            float currentY = y + height - legendSize + size;
            paint.setTextAlign(Align.LEFT);
            paint.setTextSize(renderer.getLegendTextSize());
            int sLength = Math.min(titles.length, renderer.getRenderers().size());
            for (int i = 0; i < sLength; i++) {
                final float lineSize = WIDTH;
                String text = titles[i];
                if (titles.length == renderer.getRenderers().size()) {
                    paint.setColor(renderer.getRenderers().get(i).getColor());
                } else {
                    paint.setColor(Color.LTGRAY);
                }
                float[] widths = new float[text.length()];
                paint.getTextWidths(text, widths);
                float sum = 0;
                for (float value : widths) {
                    sum += value;
                }
                float extraSize = lineSize + 10 + sum;
                float currentWidth = currentX + extraSize;

                int xPaddingLegent = renderer.getXPaddingLegent();
                int yPaddingLegent = renderer.getYPaddingLegent();

                if (i > 0 && getExceed(currentWidth, renderer, right, width)) {
                    currentX = left;
                    currentY += renderer.getLegendTextSize();
                    size += renderer.getLegendTextSize();
                    currentWidth = currentX + extraSize;
                }
                if (getExceed(currentWidth, renderer, right, width)) {
                    float maxWidth = right - currentX - lineSize - 10;
                    int nr = paint.breakText(text, true, maxWidth, widths);
                    text = text.substring(0, nr) + "...";
                }
                if (!calculate) {
                    drawLegendShape(canvas, renderer.getRenderers().get(i), currentX + xPaddingLegent, currentY + yPaddingLegent, i, paint);
                    if (renderer.getLegendsColor() != Renderer.NO_COLOR) {
                        paint.setColor(renderer.getLegendsColor());
                    }

                    if (renderer.getScaleRateLegendText() > 0) {
                        drawString(canvas, text, currentX + lineSize + xPaddingLegent + 5, currentY + yPaddingLegent + 5, paint,
                                renderer.getScaleRateLegendText());
                    } else {
                        drawString(canvas, text, currentX + lineSize + xPaddingLegent + 5, currentY + yPaddingLegent + 5, paint);
                    }
                }
                currentX += extraSize + renderer.getLegentSpacing();
            }
        }
        return Math.round(size + renderer.getLegendTextSize());
    }

    /**
     * Calculates if the current width exceeds the total width.
     * 
     * @param currentWidth
     *            the current width
     * @param renderer
     *            the renderer
     * @param right
     *            the right side pixel value
     * @param width
     *            the total width
     * @return if the current width exceeds the total width
     */
    protected boolean getExceed(float currentWidth, Renderer renderer, int right, int width) {
        return currentWidth > right;
    }

    /**
     * Draw a multiple lines string.
     * 
     * @param canvas
     *            the canvas to paint to
     * @param text
     *            the text to be painted
     * @param x
     *            the x value of the area to draw to
     * @param y
     *            the y value of the area to draw to
     * @param paint
     *            the paint to be used for drawing
     * @param scaleRate
     *            text scale rate
     */
    protected void drawString(Canvas canvas, String text, float x, float y, Paint paint, float scaleRate) {
        String[] lines = text.split("\n");
        Rect rect = new Rect();
        int yOff = 0;

        paint.setTextScaleX(scaleRate);

        for (int i = 0; i < lines.length; ++i) {
            canvas.drawText(lines[i], x, y + yOff, paint);
            paint.getTextBounds(lines[i], 0, lines[i].length(), rect);
            yOff = yOff + rect.height() + 5; // space between lines is 5
        }
    }

    /**
     * Draw a multiple lines string.
     * 
     * @param canvas
     *            the canvas to paint to
     * @param text
     *            the text to be painted
     * @param x
     *            the x value of the area to draw to
     * @param y
     *            the y value of the area to draw to
     * @param paint
     *            the paint to be used for drawing
     */
    protected void drawString(Canvas canvas, String text, float x, float y, Paint paint) {
        String[] lines = text.split("\n");
        Rect rect = new Rect();
        int yOff = 0;
        for (int i = 0; i < lines.length; ++i) {
            canvas.drawText(lines[i], x, y + yOff, paint);
            paint.getTextBounds(lines[i], 0, lines[i].length(), rect);
            yOff = yOff + rect.height() + 5;
        }
    }

    /**
     * The graphical representation of the legend shape.
     * 
     * @param canvas
     *            the canvas to paint to
     * @param renderer
     *            the series renderer
     * @param x
     *            the x value of the point the shape should be drawn at
     * @param y
     *            the y value of the point the shape should be drawn at
     * @param seriesIndex
     *            the series index
     * @param paint
     *            the paint to be used for drawing
     */
    public void drawLegendShape(Canvas canvas, DatasetRenderer renderer, float x, float y, int seriesIndex, Paint paint) {
        canvas.drawRect(x, y - WIDTH / 2, x + WIDTH, y + WIDTH / 2, paint);
    }

    /**
     * Calculates the current legend size.
     * 
     * @param renderer
     *            the renderer
     * @param defaultHeight
     *            the default height
     * @param extraHeight
     *            the added extra height
     * @return the legend size
     */
    protected int getLegendSize(Renderer renderer, int defaultHeight, float extraHeight) {
        int legendSize = renderer.getLegendHeight();
        if (renderer.isShowLegend() && legendSize == 0) {
            legendSize = defaultHeight;
        }
        if (!renderer.isShowLegend() && renderer.isShowLabels()) {
            legendSize = (int) (renderer.getLabelsTextSize() * 4 / 3 + extraHeight);
        }
        return legendSize;
    }
}
