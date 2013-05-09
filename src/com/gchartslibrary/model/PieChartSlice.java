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

/**
 * Model of slice for a piechart or doughnut
 * 
 * @author Georgel Aron
 * 
 */
public class PieChartSlice {
    private float mStartAngle;
    private float mEndAngle;
    private int mDataIndex;
    private float mValue;

    public PieChartSlice(float startAngle, float endAngle, int dataIndex, float value) {
        super();
        mStartAngle = startAngle;
        mEndAngle = endAngle;
        mDataIndex = dataIndex;
        mValue = value;
    }

    public float getStartAngle() {
        return mStartAngle;
    }

    public void setStartAngle(float startAngle) {
        mStartAngle = startAngle;
    }

    public float getEndAngle() {
        return mEndAngle;
    }

    public void setEndAngle(float endAngle) {
        mEndAngle = endAngle;
    }

    public int getDataIndex() {
        return mDataIndex;
    }

    public void setDataIndex(int dataIndex) {
        mDataIndex = dataIndex;
    }

    public float getValue() {
        return mValue;
    }

    public void setValue(float value) {
        mValue = value;
    }

    /**
     * Checks if angle is inside of segment.
     * 
     * @param angle
     * @return true if in segment, false otherwise.
     */
    public boolean isInSegment(double angle) {
        return angle >= mStartAngle && angle <= mEndAngle;
    }
}
