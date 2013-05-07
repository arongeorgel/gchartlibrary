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

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Typeface;

/**
 * An abstract class to be extend by all renderers
 * 
 * @author Georgel Aron
 * 
 */
public abstract class Renderer {
    public static final int NO_COLOR = 0;
    public static final int BACKGROUND_COLOR = Color.BLACK;
    public static final int TEXT_COLOR = Color.LTGRAY;
    private static final Typeface REGULAR_TEXT_FONT = Typeface.create(Typeface.SERIF, Typeface.NORMAL);

    private String mChartTitle;
    private float mChartTitleTextSize;
    private int mChartTitleTextColor;
    private int mChartTitleTextMaxWidth;
    private String mTextTypefaceName;
    private int mTextTypefaceStyle;
    private Typeface typeface;
    private int mBackgroundColor;
    private boolean mApplyBackgroundColor;
    private boolean mShowLabels;
    private int mLabelsColor;
    private float mLabelsTextSize;
    private boolean mShowLegend;
    private float mLegendTextSize;
    private boolean mFitLegend;
    private List<DatasetRenderer> mRenderers;
    private int mLegendHeight;
    private int[] mMargins;
    private boolean mClickEnabled;
    private int mSelectableBuffer;
    private boolean mDisplayValues;
    private boolean mPassValues;
    private float mStartAngle;
    private int mLegendsColor;
    private int mBarMinimalWith;
    private int mXPaddingLegend;
    private int mYPaddingLegend;
    private int mLegendSpacing;
    private float scaleRateChartValuesText;
    private float scaleRateXYAxisLablesText;
    private float scaleRateLegendText;

    public Renderer() {
        mChartTitle = "";
        mChartTitleTextSize = 15;
        mChartTitleTextColor = Color.WHITE;
        mChartTitleTextMaxWidth = -1;
        mTextTypefaceName = REGULAR_TEXT_FONT.toString();
        mTextTypefaceStyle = Typeface.NORMAL;
        mShowLabels = true;
        mLabelsColor = TEXT_COLOR;
        mLabelsTextSize = 10;
        mShowLegend = true;
        mLegendTextSize = 12;
        mFitLegend = false;
        mRenderers = new ArrayList<DatasetRenderer>();
        mLegendHeight = 0;
        mMargins = new int[] { 5, 5, 5, 5 };
        mClickEnabled = false;
        mSelectableBuffer = 15;
        mStartAngle = 0;
        mLegendsColor = NO_COLOR;
        mBarMinimalWith = 30;
        mXPaddingLegend = 0;
        mYPaddingLegend = 0;
        mLegendSpacing = 0;
    }

    public void removeClickListener() {
        for (DatasetRenderer renderer : mRenderers) {
            renderer.setClicked(false);
        }
    }

    public void addSeriesRenderer(DatasetRenderer renderer) {
        mRenderers.add(renderer);
    }

    public void removeSeriesRenderer(DatasetRenderer renderer) {
        mRenderers.remove(renderer);
    }

    public String getChartTitle() {
        return mChartTitle;
    }

    public void setChartTitle(String chartTitle) {
        mChartTitle = chartTitle;
    }

    public float getChartTitleTextSize() {
        return mChartTitleTextSize;
    }

    public void setChartTitleTextSize(float chartTitleTextSize) {
        mChartTitleTextSize = chartTitleTextSize;
    }

    public int getChartTitleTextColor() {
        return mChartTitleTextColor;
    }

    public void setChartTitleTextColor(int chartTitleTextColor) {
        mChartTitleTextColor = chartTitleTextColor;
    }

    public int getChartTitleTextMaxWidth() {
        return mChartTitleTextMaxWidth;
    }

    public void setChartTitleTextMaxWidth(int chartTitleTextMaxWidth) {
        mChartTitleTextMaxWidth = chartTitleTextMaxWidth;
    }

    public String getTextTypefaceName() {
        return mTextTypefaceName;
    }

    public void setTextTypefaceName(String textTypefaceName) {
        mTextTypefaceName = textTypefaceName;
    }

    public int getTextTypefaceStyle() {
        return mTextTypefaceStyle;
    }

    public void setTextTypefaceStyle(int textTypefaceStyle) {
        mTextTypefaceStyle = textTypefaceStyle;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
    }

    public boolean isApplyBackgroundColor() {
        return mApplyBackgroundColor;
    }

    public void setApplyBackgroundColor(boolean applyBackgroundColor) {
        mApplyBackgroundColor = applyBackgroundColor;
    }

    public boolean isShowLabels() {
        return mShowLabels;
    }

    public void setShowLabels(boolean showLabels) {
        mShowLabels = showLabels;
    }

    public int getLabelsColor() {
        return mLabelsColor;
    }

    public void setLabelsColor(int labelsColor) {
        mLabelsColor = labelsColor;
    }

    public float getLabelsTextSize() {
        return mLabelsTextSize;
    }

    public void setLabelsTextSize(float labelsTextSize) {
        mLabelsTextSize = labelsTextSize;
    }

    public boolean isShowLegend() {
        return mShowLegend;
    }

    public void setShowLegend(boolean showLegend) {
        mShowLegend = showLegend;
    }

    public float getLegendTextSize() {
        return mLegendTextSize;
    }

    public void setLegendTextSize(float legendTextSize) {
        mLegendTextSize = legendTextSize;
    }

    public boolean isFitLegend() {
        return mFitLegend;
    }

    public void setFitLegend(boolean fitLegend) {
        mFitLegend = fitLegend;
    }

    public List<DatasetRenderer> getRenderers() {
        return mRenderers;
    }

    public void setRenderers(List<DatasetRenderer> renderers) {
        mRenderers = renderers;
    }

    public int getLegendHeight() {
        return mLegendHeight;
    }

    public void setLegendHeight(int legendHeight) {
        mLegendHeight = legendHeight;
    }

    public int[] getMargins() {
        return mMargins;
    }

    public void setMargins(int[] margins) {
        mMargins = margins;
    }

    public boolean isClickEnabled() {
        return mClickEnabled;
    }

    public void setClickEnabled(boolean clickEnabled) {
        mClickEnabled = clickEnabled;
    }

    public int getSelectableBuffer() {
        return mSelectableBuffer;
    }

    public void setSelectableBuffer(int selectableBuffer) {
        this.mSelectableBuffer = selectableBuffer;
    }

    public boolean isDisplayValues() {
        return mDisplayValues;
    }

    public void setDisplayValues(boolean displayValues) {
        mDisplayValues = displayValues;
    }

    public boolean isPassValues() {
        return mPassValues;
    }

    public void setPassValues(boolean passValues) {
        mPassValues = passValues;
    }

    public float getStartAngle() {
        return mStartAngle;
    }

    public void setStartAngle(float startAngle) {
        mStartAngle = startAngle;
    }

    public int getLegendsColor() {
        return mLegendsColor;
    }

    public void setLegendsColor(int legendsColor) {
        mLegendsColor = legendsColor;
    }

    public int getBarMinimalWith() {
        return mBarMinimalWith;
    }

    public void setBarMinimalWith(int barMinimalWith) {
        mBarMinimalWith = barMinimalWith;
    }

    public int getXPaddingLegent() {
        return mXPaddingLegend;
    }

    public void setXPaddingLegent(int xPaddingLegent) {
        mXPaddingLegend = xPaddingLegent;
    }

    public int getYPaddingLegent() {
        return mYPaddingLegend;
    }

    public void setYPaddingLegent(int yPaddingLegent) {
        mYPaddingLegend = yPaddingLegent;
    }

    public int getLegentSpacing() {
        return mLegendSpacing;
    }

    public void setLegentSpacing(int legentSpacing) {
        mLegendSpacing = legentSpacing;
    }

    public float getScaleRateChartValuesText() {
        return scaleRateChartValuesText;
    }

    public void setScaleRateChartValuesText(float scaleRateChartValuesText) {
        this.scaleRateChartValuesText = scaleRateChartValuesText;
    }

    public float getScaleRateXYAxisLablesText() {
        return scaleRateXYAxisLablesText;
    }

    public void setScaleRateXYAxisLablesText(float scaleRateXYAxisLablesText) {
        this.scaleRateXYAxisLablesText = scaleRateXYAxisLablesText;
    }

    public float getScaleRateLegendText() {
        return scaleRateLegendText;
    }

    public void setScaleRateLegendText(float scaleRateLegendText) {
        this.scaleRateLegendText = scaleRateLegendText;
    }

}
