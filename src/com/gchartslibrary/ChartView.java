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

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.gchartslibrary.chart.Chart;
import com.gchartslibrary.model.DatasetSelection;
import com.gchartslibrary.model.LabelsSelection;

/**
 * The view holder where the chart will be drown
 * 
 * @author Georgel Aron
 * 
 */
public class ChartView extends View {
    private Chart mChart;
    private Rect mRect;
    private Handler mHandler;
    private Paint mPaint;
    private float mOldX;
    private float mOldY;

    private int mOffsetX = 0;
    private int mOffsetY = 0;

    /**
     * Use constructor <code>ChartView(context, chart)</code>
     */
    @Deprecated
    public ChartView(Context context) {
        super(context);
    }

    public ChartView(Context context, Chart chart) {
        super(context);
        mRect = new Rect();
        mPaint = new Paint();
        mChart = chart;
        mHandler = new Handler();
    }

    public Chart getChart() {
        return mChart;
    }

    /**
     * Get <b>x</b> coordinate of last touch
     * 
     * @return x coordinate value
     */
    public float getOldX() {
        return mOldX;
    }

    /**
     * Get <b>y</b> coordinate of last touch
     * 
     * @return y coordinate value
     */
    public float getOldY() {
        return mOldY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int top = mRect.top;
        int left = mRect.left;

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mChart.draw(canvas, left, top, width, height, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mOldX = event.getX();
            mOldY = event.getY();
        }
        return super.onTouchEvent(event);
    }

    /**
     * Returns the current series selection object.
     * 
     * @return the series selection
     */
    public DatasetSelection getCurrentSeriesAndPoint() {
        return mChart.getDatasetForScreenCoordinate(new Point((int) mOldX, (int) mOldY), new Point(mOffsetX, mOffsetY));
    }

    public LabelsSelection getCurrentLabelAndPoint() {
        return null;
    }

    /**
     * Schedule a view content repaint.
     */
    public void repaint() {
        mHandler.post(new Runnable() {
            public void run() {
                invalidate();
            }
        });
    }

    /**
     * Schedule a view content repaint, in the specified rectangle area.
     * 
     * @param left
     *            the left position of the area to be repainted
     * @param top
     *            the top position of the area to be repainted
     * @param right
     *            the right position of the area to be repainted
     * @param bottom
     *            the bottom position of the area to be repainted
     */
    public void repaint(final int left, final int top, final int right, final int bottom) {
        mHandler.post(new Runnable() {
            public void run() {
                invalidate(left, top, right, bottom);
            }
        });
    }
}
