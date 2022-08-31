package com.gcode.shapebutton

import android.graphics.drawable.GradientDrawable
import androidx.annotation.IntDef

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2021/11/12
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