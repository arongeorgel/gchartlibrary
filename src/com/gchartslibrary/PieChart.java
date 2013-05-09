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
package com.gchartslibrary;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.RectF;

import com.gchartslibrary.chart.Chart;
import com.gchartslibrary.model.ChartDataset;
import com.gchartslibrary.model.DatasetSelection;
import com.gchartslibrary.model.PieChartMap;
import com.gchartslibrary.renderer.DatasetRenderer;
import com.gchartslibrary.renderer.Renderer;

/**
 * Pie chart class
 * 
 * @author Georgel Aron
 * 
 */
public class PieChart extends Chart {
    private PieChartMap mPieMap;
    private int mCenterX;
    private int mCenterY;

    public PieChart(ChartDataset dataset, Renderer renderer) {
        super(dataset, renderer);
        mCenterX = 0;
        mCenterY = 0;

        mPieMap = new PieChartMap();
    }

    @Override
    public void draw(Canvas canvas, int x, int y, int width, int height, Paint paint) {
        paint.setAntiAlias(true);
        paint.setStyle(Style.FILL);

        int legendSize = getLegendSize(mRenderer, height / 5, 0);
        int left = x;
        int top = y;
        int right = x + width;
        int sLength = mDataset.getItemCount();
        double total = 0;
        String[] titles = new String[sLength];
        for (int i = 0; i < sLength; i++) {
            total += mDataset.getValue(i);
            titles[i] = mDataset.getCategory(i);
        }
        if (mRenderer.isFitLegend()) {
            legendSize = drawLegend(canvas, mRenderer, titles, left, right, y, width, height, legendSize, paint, true);
        }
        int bottom = y + height - legendSize;

        drawBackground(mRenderer, canvas, x, y, width, height, paint, false, Renderer.NO_COLOR);
        float currentAngle = mRenderer.getStartAngle();
        float labelCurrentAngle = mRenderer.getStartAngle();
        int mRadius = Math.min(Math.abs(right - left), Math.abs(bottom - top));
        int radius = (int) (mRadius * 0.35) + 50;

        if (mCenterX == 0) {
            mCenterX = (left + right) / 2;
        }
        if (mCenterY == 0) {
            mCenterY = (bottom + top) / 2;
        }

        // Hook in clip detection after center has been calculated
        mPieMap.setDimensions(radius, mCenterX, mCenterY);
        boolean loadPieCfg = !mPieMap.areAllSegmentPresent(sLength);
        if (loadPieCfg) {
            mPieMap.clearPieSegments();
        }

        float shortRadius = radius * 0.9f;
        float longRadius = radius * 1.1f;

        RectF oval = new RectF(mCenterX - radius, mCenterY - radius, mCenterX + radius, mCenterY + radius);
        for (int i = 0; i < sLength; i++) {
            float value = (float) mDataset.getValue(i);
            float angle = (float) (value / total * 360);
            int color = mRenderer.getRenderers().get(i).getColor();

            if (mRenderer.getRenderers().get(i).isClicked()) {
                ClickedArc.CANVAS = canvas;
                ClickedArc.CURRENT_ANGLE = currentAngle;
                ClickedArc.ANGLE = angle;
                ClickedArc.COLOR = color;
                ClickedArc.INDEX = mRenderer.getRenderers().get(i).getDataIndex();
            } else {
                Paint paint3 = new Paint();
                paint3.setStrokeWidth(1);
                paint3.setStrokeCap(Paint.Cap.ROUND);
                paint3.setStyle(Paint.Style.FILL);

                int[] colors = { color, colorToDarker(color, 0.95f) };
                RadialGradient gradient3 = new RadialGradient(mCenterX, mCenterY, radius, colors, null, android.graphics.Shader.TileMode.CLAMP);
                paint3.setShader(gradient3);
                paint3.setAntiAlias(true);

                canvas.drawArc(oval, currentAngle, angle, true, paint3);
            }

            if (i + 1 == sLength && mRenderer.getRenderers().get(ClickedArc.INDEX).isClicked()) {
                int[] colorClicked = { ClickedArc.COLOR, colorToDarker(ClickedArc.COLOR, 0.95f) };
                Paint paint3 = new Paint();
                paint3.setStrokeWidth(1);
                paint3.setStrokeCap(Paint.Cap.ROUND);
                paint3.setStyle(Paint.Style.FILL);
                if (!mRenderer.getRenderers().get(ClickedArc.INDEX).isPopout()) {
                    Paint shadow = new Paint();
                    shadow.setColor(Color.DKGRAY);
                    shadow.setAntiAlias(true);
                    shadow.setShadowLayer(2.0f, 1.0f, 1.0f, ClickedArc.INDEX);
                    int shadowRadius = radius + 2;
                    RectF shadowOval = new RectF(mCenterX - shadowRadius, mCenterY - shadowRadius, mCenterX + shadowRadius, mCenterY + shadowRadius);
                    canvas.drawArc(shadowOval, ClickedArc.CURRENT_ANGLE - 1, ClickedArc.ANGLE + 2, true, shadow);

                    RadialGradient gradient3 = new RadialGradient(mCenterX, mCenterY, radius, colorClicked, null,
                            android.graphics.Shader.TileMode.CLAMP);
                    paint3.setShader(gradient3);
                    paint3.setAntiAlias(true);
                    canvas.drawArc(oval, ClickedArc.CURRENT_ANGLE, ClickedArc.ANGLE, true, paint3);
                } else {
                    int shadowRadius = radius + 5;
                    oval = new RectF(mCenterX - shadowRadius, mCenterY - shadowRadius, mCenterX + shadowRadius, mCenterY + shadowRadius);
                    RadialGradient gradient3 = new RadialGradient(mCenterX + shadowRadius, mCenterY + shadowRadius, radius, colorClicked, null,
                            android.graphics.Shader.TileMode.CLAMP);
                    paint3.setShader(gradient3);
                    paint3.setAntiAlias(true);
                    canvas.drawArc(oval, ClickedArc.CURRENT_ANGLE, ClickedArc.ANGLE, true, paint3);
                }
            }

            if (loadPieCfg) {
                mRenderer.getRenderers().get(i).setDataIndex(i);
                mPieMap.addPieSegment(i, value, currentAngle, angle + currentAngle);
            }

            currentAngle += angle;
        }

        if (mRenderer.isShowLabels()) {
            List<RectF> prevLabelsBounds = new ArrayList<RectF>();
            for (int i = 0; i < sLength; i++) {
                float value = (float) mDataset.getValue(i);
                float angle = (float) (value / total * 360);
                drawLabel(canvas, mDataset.getCategory(i), mRenderer, prevLabelsBounds, mCenterX, mCenterY, shortRadius / 2, longRadius / 2,
                        labelCurrentAngle, angle, left, right, mRenderer.getLabelsColor(), paint, true, mRenderer.getRenderers().get(i));
                Point sPoint = mRenderer.getRenderers().get(i).getCenterPoint();
                Point ePoint = new Point((int) mRenderer.getRenderers().get(i).getTextWidth(), (int) mRenderer.getRenderers().get(i).getTextHeight());

                mPieMap.addLabelSegment(i, value, sPoint, ePoint);
                labelCurrentAngle += angle;
            }
            prevLabelsBounds.clear();
        }

        if (mRenderer.isShowLegend()) {
            drawLegend(canvas, mRenderer, titles, left, right, y, width, height, legendSize, paint, false);
        }

    }

    @Override
    public DatasetSelection getDatasetForScreenCoordinate(Point screenPoint, Point offsetPoint) {
        return mPieMap.getSeriesAndPointForScreenCoordinate(screenPoint);
    }

    @Override
    public Renderer getRenderer() {
        return mRenderer;
    }

    public void removeDatasetRendererClicked() {
        for (DatasetRenderer renderer : mRenderer.getRenderers()) {
            renderer.setClicked(false);
        }
    }

    public void setDatasetRendererClicked(int value) {
        for (DatasetRenderer renderer : mRenderer.getRenderers()) {
            if (renderer.getDataIndex() == value) {
                renderer.setClicked(true);
            } else {
                renderer.setClicked(false);
            }
        }
    }

    public static class ClickedArc {
        public static int INDEX;
        public static Canvas CANVAS = null;
        public static float CURRENT_ANGLE = 0.0f;
        public static float ANGLE = 0.0f;
        public static int COLOR = 0x00;
    }

}
