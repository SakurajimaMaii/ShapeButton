package com.gcode.shapebutton

import android.graphics.drawable.GradientDrawable

/**
 *作者:created by HP on 2021/8/7 00:06
 *邮箱:sakurajimamai2020@qq.com
 */
enum class ShapeButtonGradientType(val typeValue:Int) {
    LINEAR_GRADIENT(GradientDrawable.LINEAR_GRADIENT), RADIAL_GRADIENT(GradientDrawable.RADIAL_GRADIENT), SWEEP_GRADIENT(GradientDrawable.SWEEP_GRADIENT);
}