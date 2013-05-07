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

import java.util.Arrays;

import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;

/**
 * A descriptor for the pen style.
 */
public class BasicPen {
    public static final BasicPen SOLID = new BasicPen(Cap.BUTT, Join.MITER, 4, null, 0);
    public static final BasicPen DASHED = new BasicPen(Cap.ROUND, Join.BEVEL, 10, new float[] { 10, 10 }, 1);
    public static final BasicPen DOTTED = new BasicPen(Cap.ROUND, Join.BEVEL, 5, new float[] { 2, 10 }, 1);
    private Cap mCap;
    private Join mJoin;
    private float mMiter;
    private float[] mIntervals;
    private float mPhase;

    /**
     * Build a new basic pen style.
     * 
     * @param cap
     *            the pen cap
     * @param join
     *            the pen join
     * @param miter
     *            the pen miter
     * @param intervals
     *            the path effect intervals
     * @param phase
     *            the path effect phase
     */
    public BasicPen(Cap cap, Join join, float miter, float[] intervals, float phase) {
        mCap = cap;
        mJoin = join;
        mMiter = miter;
        mIntervals = intervals;
    }

    /**
     * Returns the pen cap.
     * 
     * @return the pen cap
     */
    public Cap getCap() {
        return mCap;
    }

    /**
     * Returns the pen join.
     * 
     * @return the pen join
     */
    public Join getJoin() {
        return mJoin;
    }

    /**
     * Returns the pen miter.
     * 
     * @return the pen miter
     */
    public float getMiter() {
        return mMiter;
    }

    /**
     * Returns the path effect intervals.
     * 
     * @return the path effect intervals
     */
    public float[] getIntervals() {
        return mIntervals;
    }

    /**
     * Returns the path effect phase.
     * 
     * @return the path effect phase
     */
    public float getPhase() {
        return mPhase;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mCap == null) ? 0 : mCap.hashCode());
        result = prime * result + Arrays.hashCode(mIntervals);
        result = prime * result + ((mJoin == null) ? 0 : mJoin.hashCode());
        result = prime * result + Float.floatToIntBits(mMiter);
        result = prime * result + Float.floatToIntBits(mPhase);
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
        BasicPen other = (BasicPen) obj;
        if (mCap != other.mCap)
            return false;
        if (!Arrays.equals(mIntervals, other.mIntervals))
            return false;
        if (mJoin != other.mJoin)
            return false;
        if (Float.floatToIntBits(mMiter) != Float.floatToIntBits(other.mMiter))
            return false;
        if (Float.floatToIntBits(mPhase) != Float.floatToIntBits(other.mPhase))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BasicPen [mCap=" + mCap + ", mJoin=" + mJoin + ", mMiter=" + mMiter + ", mIntervals=" + Arrays.toString(mIntervals) + ", mPhase="
                + mPhase + "]";
    }

}
