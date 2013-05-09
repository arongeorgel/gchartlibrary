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
 * 
 * @author Georgel Aron
 * 
 */
public class LabelsSelection {
    private int mLabelIndex;

    public LabelsSelection(int labelIndex) {
        mLabelIndex = labelIndex;
    }

    public int getLabelIndex() {
        return mLabelIndex;
    }

    public void setLabelIndex(int labelIndex) {
        mLabelIndex = labelIndex;
    }
}
