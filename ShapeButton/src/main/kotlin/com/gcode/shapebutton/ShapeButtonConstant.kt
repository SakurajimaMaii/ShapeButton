/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gcode.shapebutton

import android.graphics.drawable.GradientDrawable
import androidx.annotation.IntDef

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/8/31 13:10
// Description: 
// Documentation:

object ShapeButtonConstant {
    const val OVAL_SHAPE = 1
    const val RECT_SHAPE = 2
    const val ROUNDED_RECT_SHAPE = 3
    const val ANY_ROUNDED_RECT_SHAPE = 4

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(OVAL_SHAPE, RECT_SHAPE, ROUNDED_RECT_SHAPE, ANY_ROUNDED_RECT_SHAPE)
    annotation class ShapeType

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(
        GradientDrawable.LINEAR_GRADIENT,
        GradientDrawable.RADIAL_GRADIENT,
        GradientDrawable.SWEEP_GRADIENT
    )
    annotation class GradientType
}