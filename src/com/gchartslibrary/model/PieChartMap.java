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
package com.gchartslibrary.model;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

/**
 * An imaginary map over the chart, used for touch event
 * 
 * @author Georgel Aron
 * 
 */
public class PieChartMap {
    private List<PieChartSlice> mPieSegmentList = new ArrayList<PieChartSlice>();
    private List<PieSliceLabel> mLabelSegmentList = new ArrayList<PieSliceLabel>();

    private int mPieChartRadius;

    private int mCenterX, mCenterY;

    /**
     * Set PieChart location on screen.
     * 
     * @param pieRadius
     * @param centerX
     * @param centerY
     */
    public void setDimensions(int pieRadius, int centerX, int centerY) {
        mPieChartRadius = pieRadius;
        mCenterX = centerX;
        mCenterY = centerY;
    }

    /**
     * If we have all PieChart Config then there is no point in reloading it
     * 
     * @param datasetSize
     * @return true if cfg for each segment is present
     */
    public boolean areAllSegmentPresent(int datasetSize) {
        return mPieSegmentList.size() == datasetSize;
    }

    /**
     * Add configuration for a PieChart Segment
     * 
     * @param dataIndex
     * @param value
     * @param startAngle
     * @param endAngle
     */
    public void addPieSegment(int dataIndex, float value, float startAngle, float endAngle) {
        mPieSegmentList.add(new PieChartSlice(startAngle, endAngle, dataIndex, value));
    }

    public void addLabelSegment(int dataIndex, float value, Point startPoint, Point endPoint) {
        mLabelSegmentList.add(new PieSliceLabel(dataIndex, startPoint, endPoint));
    }

    /**
     * Clears the pie segments list.
     */
    public void clearPieSegments() {
        mPieSegmentList.clear();
    }

    /**
     * Fetches angle relative to pie chart center point where 3 O'Clock is 0 and
     * 12 O'Clock is 270degrees
     * 
     * @param screenPoint
     * @return angle in degress from 0-360.
     */
    public double getAngle(Point screenPoint) {
        double dx = screenPoint.x - mCenterX;
        // Minus to correct for coord re-mapping
        double dy = -(screenPoint.y - mCenterY);

        double inRads = Math.atan2(dy, dx);

        // We need to map to coord system when 0 degree is at 3 O'clock, 270 at
        // 12
        // O'clock
        if (inRads < 0)
            inRads = Math.abs(inRads);
        else
            inRads = 2 * Math.PI - inRads;

        return Math.toDegrees(inRads);
    }

    /**
     * Checks if Point falls within PieChart
     * 
     * @param screenPoint
     * @return true if in PieChart
     */
    public boolean isOnPieChart(Point screenPoint) {
        double sqValue = (Math.pow(mCenterX - screenPoint.x, 2) + Math.pow(mCenterY - screenPoint.y, 2));

        double radiusSquared = mPieChartRadius * mPieChartRadius;
        boolean isOnPieChart = sqValue <= radiusSquared;
        return isOnPieChart;
    }

    /**
     * Fetches the SeriesSelection for the PieSegment selected.
     * 
     * @param screenPoint
     *            - the user tap location
     * @return null if screen point is not in PieChart or its config if it is
     */
    public DatasetSelection getSeriesAndPointForScreenCoordinate(Point screenPoint) {
        if (isOnPieChart(screenPoint)) {
            double angleFromPieCenter = getAngle(screenPoint);

            for (PieChartSlice pieSeg : mPieSegmentList) {
                if (pieSeg.isInSegment(angleFromPieCenter)) {
                    return new DatasetSelection(0, pieSeg.getDataIndex(), pieSeg.getValue(), pieSeg.getValue());
                }
            }
        }
        return null;
    }

    public LabelsSelection getLabelAndPointScreenCoordinate(Point screenPoint) {
        if (isOnPieChart(screenPoint)) {
            for (PieSliceLabel seg : mLabelSegmentList) {
                if (seg.isInSegment(screenPoint)) {
                    return new LabelsSelection(seg.getLabelIndex());
                }
            }
        }
        return null;
    }
}
