package com.gchartslibrary.model;

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
