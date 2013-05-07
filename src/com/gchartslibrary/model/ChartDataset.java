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

/**
 * Class for dataset of a chart
 * 
 * @author Georgel Aron
 * 
 */
public class ChartDataset {
    /** The dataset title. */
    private String mTitle;
    /** The dataset categories. */
    private List<String> mCategories = new ArrayList<String>();
    /** The dataset values. */
    private List<Double> mValues = new ArrayList<Double>();

    /**
     * Builds a new category series.
     * 
     * @param title
     *            the series title
     */
    public ChartDataset(String title) {
        mTitle = title;
    }

    /**
     * Adds a new value to the dataset
     * 
     * @param category
     *            the category
     * @param value
     *            the new value
     */
    public synchronized void add(String category, double value) {
        mCategories.add(category);
        mValues.add(value);
    }

    /**
     * Replaces the value at the specific index in the dataset.
     * 
     * @param index
     *            the index in the dataset
     * @param category
     *            the category
     * @param value
     *            the new value
     */
    public synchronized void set(int index, String category, double value) {
        mCategories.set(index, category);
        mValues.set(index, value);
    }

    /**
     * Removes an existing value from the dataset.
     * 
     * @param index
     *            the index in the dataset of the value to remove
     */
    public synchronized void remove(int index) {
        mCategories.remove(index);
        mValues.remove(index);
    }

    /**
     * Returns the value at the specified index.
     * 
     * @param index
     *            the index
     * @return the value at the index
     */
    public synchronized double getValue(int index) {
        return mValues.get(index);
    }

    /**
     * Returns the dataset item count.
     * 
     * @return the dataset item count
     */
    public synchronized int getItemCount() {
        return mCategories.size();
    }

    /**
     * Returns the category name at the specified index.
     * 
     * @param index
     *            the index
     * @return the category name at the index
     */
    public synchronized String getCategory(int index) {
        return mCategories.get(index);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
