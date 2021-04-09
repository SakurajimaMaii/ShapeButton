package com.gcode.gstylebutton

import android.graphics.drawable.GradientDrawable

/**
 *作者:created by HP on 2021/3/9 12:03
 *邮箱:sakurajimamai2020@qq.com
 */
enum class GStyleButtonGradientType(private val typeValue:Int) {
    LINEAR_GRADIENT(GradientDrawable.LINEAR_GRADIENT), RADIAL_GRADIENT(GradientDrawable.RADIAL_GRADIENT), SWEEP_GRADIENT(GradientDrawable.SWEEP_GRADIENT);

    fun getTypeValue() = typeValue
}