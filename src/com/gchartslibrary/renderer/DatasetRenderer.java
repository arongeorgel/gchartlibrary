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
package com.gchartslibrary.renderer;

import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Point;

/**
 * Class used for renderer a simple series of chart
 * 
 * @author Georgel Aron
 * 
 */
public class DatasetRenderer {
    private int mDataIndex;
    private int mColor;
    private boolean mDisplayChartValues;
    private int mDisplayChartValuesDistance;
    private float mChartValuesTextSize;
    private Align mChartValuesTextAlign;
    private float mChartValuesSpacing;
    private BasicPen mStroke;
    private int mChartValuesColor;
    private boolean mInTopBar;
    private boolean mInCenterBar;
    private boolean mClicked;
    private Point mCenterPoint;
    private float mTextWidth;
    private float mTextHeight;
    private boolean mPopout;

    public DatasetRenderer() {
        mColor = Color.GREEN;
        mDisplayChartValuesDistance = 100;
        mChartValuesTextSize = 10;
        mChartValuesTextAlign = Align.CENTER;
        mChartValuesSpacing = 5f;
        mChartValuesColor = 0;
        mInTopBar = false;
        mInCenterBar = false;
        mClicked = false;
        mCenterPoint = new Point(0, 0);
        mPopout = false;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public boolean isDisplayChartValues() {
        return mDisplayChartValues;
    }

    public void setDisplayChartValues(boolean displayChartValues) {
        mDisplayChartValues = displayChartValues;
    }

    public int getDisplayChartValuesDistance() {
        return mDisplayChartValuesDistance;
    }

    public void setDisplayChartValuesDistance(int displayChartValuesDistance) {
        mDisplayChartValuesDistance = displayChartValuesDistance;
    }

    public float getChartValuesTextSize() {
        return mChartValuesTextSize;
    }

    public void setChartValuesTextSize(float chartValuesTextSize) {
        mChartValuesTextSize = chartValuesTextSize;
    }

    public Align getChartValuesTextAlign() {
        return mChartValuesTextAlign;
    }

    public void setChartValuesTextAlign(Align chartValuesTextAlign) {
        mChartValuesTextAlign = chartValuesTextAlign;
    }

    public float getChartValuesSpacing() {
        return mChartValuesSpacing;
    }

    public void setChartValuesSpacing(float chartValuesSpacing) {
        mChartValuesSpacing = chartValuesSpacing;
    }

    public BasicPen getStroke() {
        return mStroke;
    }

    public void setStroke(BasicPen stroke) {
        mStroke = stroke;
    }

    public int getChartValuesColor() {
        return mChartValuesColor;
    }

    public void setChartValuesColor(int chartValuesColor) {
        mChartValuesColor = chartValuesColor;
    }

    public boolean isInTopBar() {
        return mInTopBar;
    }

    public void setInTopBar(boolean inTopBar) {
        mInTopBar = inTopBar;
    }

    public boolean isInCenterBar() {
        return mInCenterBar;
    }

    public void setInCenterBar(boolean inCenterBar) {
        mInCenterBar = inCenterBar;
    }

    public boolean isClicked() {
        return mClicked;
    }

    public void setClicked(boolean clicked) {
        mClicked = clicked;
    }

    public Point getCenterPoint() {
        return mCenterPoint;
    }

    public void setCenterPoint(Point centerPoint) {
        mCenterPoint = centerPoint;
    }

    public float getTextWidth() {
        return mTextWidth;
    }

    public void setTextWidth(float textWidth) {
        mTextWidth = textWidth;
    }

    public float getTextHeight() {
        return mTextHeight;
    }

    public void setTextHeight(float textHeight) {
        mTextHeight = textHeight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mCenterPoint == null) ? 0 : mCenterPoint.hashCode());
        result = prime * result + mChartValuesColor;
        result = prime * result + Float.floatToIntBits(mChartValuesSpacing);
        result = prime * result + ((mChartValuesTextAlign == null) ? 0 : mChartValuesTextAlign.hashCode());
        result = prime * result + Float.floatToIntBits(mChartValuesTextSize);
        result = prime * result + (mClicked ? 1231 : 1237);
        result = prime * result + mColor;
        result = prime * result + (mDisplayChartValues ? 1231 : 1237);
        result = prime * result + mDisplayChartValuesDistance;
        result = prime * result + (mInCenterBar ? 1231 : 1237);
        result = prime * result + (mInTopBar ? 1231 : 1237);
        result = prime * result + ((mStroke == null) ? 0 : mStroke.hashCode());
        result = prime * result + Float.floatToIntBits(mTextHeight);
        result = prime * result + Float.floatToIntBits(mTextWidth);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DatasetRenderer other = (DatasetRenderer) obj;
        if (mCenterPoint == null) {
            if (other.mCenterPoint != null)
                return false;
        } else if (!mCenterPoint.equals(other.mCenterPoint))
            return false;
        if (mChartValuesColor != other.mChartValuesColor)
            return false;
        if (Float.floatToIntBits(mChartValuesSpacing) != Float.floatToIntBits(other.mChartValuesSpacing))
            return false;
        if (mChartValuesTextAlign != other.mChartValuesTextAlign)
            return false;
        if (Float.floatToIntBits(mChartValuesTextSize) != Float.floatToIntBits(other.mChartValuesTextSize))
            return false;
        if (mClicked != other.mClicked)
            return false;
        if (mColor != other.mColor)
            return false;
        if (mDisplayChartValues != other.mDisplayChartValues)
            return false;
        if (mDisplayChartValuesDistance != other.mDisplayChartValuesDistance)
            return false;
        if (mInCenterBar != other.mInCenterBar)
            return false;
        if (mInTopBar != other.mInTopBar)
            return false;
        if (mStroke == null) {
            if (other.mStroke != null)
                return false;
        } else if (!mStroke.equals(other.mStroke))
            return false;
        if (Float.floatToIntBits(mTextHeight) != Float.floatToIntBits(other.mTextHeight))
            return false;
        if (Float.floatToIntBits(mTextWidth) != Float.floatToIntBits(other.mTextWidth))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SeriesRenderer [mColor=" + mColor + ", mDisplayChartValues=" + mDisplayChartValues + ", mDisplayChartValuesDistance="
                + mDisplayChartValuesDistance + ", mChartValuesTextSize=" + mChartValuesTextSize + ", mChartValuesTextAlign=" + mChartValuesTextAlign
                + ", mChartValuesSpacing=" + mChartValuesSpacing + ", mStroke=" + mStroke + ", mChartValuesColor=" + mChartValuesColor
                + ", mInTopBar=" + mInTopBar + ", mInCenterBar=" + mInCenterBar + ", mClicked=" + mClicked + ", mCenterPoint=" + mCenterPoint
                + ", mTextWidth=" + mTextWidth + ", mTextHeight=" + mTextHeight + "]";
    }

    public int getDataIndex() {
        return mDataIndex;
    }

    public void setDataIndex(int i) {
        mDataIndex = i;
    }

    public boolean isPopout() {
        return mPopout;
    }

    public void setPopout(boolean popout) {
        mPopout = popout;
    }
}
