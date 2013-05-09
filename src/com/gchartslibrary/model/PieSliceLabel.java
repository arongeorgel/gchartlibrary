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

import android.graphics.Point;

/**
 * Model of a label for a slice
 * 
 * @see {@link PieChartSlice}
 * 
 * @author Georgel Aron
 * 
 */
public class PieSliceLabel {
    private int mLabelIndex;
    private Point mStartPoint;
    private Point mEndPoint;

    public PieSliceLabel(int labelIndex, Point startPoint, Point endPoint) {
        super();
        mLabelIndex = labelIndex;
        mStartPoint = startPoint;
        mEndPoint = endPoint;
    }

    public int getLabelIndex() {
        return mLabelIndex;
    }

    public void setLabelIndex(int labelIndex) {
        mLabelIndex = labelIndex;
    }

    public Point getStartPoint() {
        return mStartPoint;
    }

    public void setStartPoint(Point startPoint) {
        mStartPoint = startPoint;
    }

    public Point getEndPoint() {
        return mEndPoint;
    }

    public void setEndPoint(Point endPoint) {
        mEndPoint = endPoint;
    }

    /**
     * Checks if the point is inside the segment
     * 
     * @param point
     *            point
     * @return true if point is inside segment, false otherwise
     */
    public boolean isInSegment(Point point) {
        if (point.x < mEndPoint.x && point.y > mEndPoint.y && point.x > mStartPoint.x && point.y < mStartPoint.y) {
            return true;
        } else {
            return false;
        }
    }
}
