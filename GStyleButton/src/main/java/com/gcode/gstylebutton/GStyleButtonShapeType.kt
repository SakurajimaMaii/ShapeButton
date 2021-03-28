package com.gcode.gstylebutton

/**
 *作者:created by HP on 2021/3/9 12:03
 *邮箱:sakurajimamai2020@qq.com
 */
enum class GStyleButtonShapeType(private val typeValue:Int) {

    OVAL_SHAPE(1),RECT_SHAPE(2),ROUNDED_RECT_SHAPE(3),ANY_ROUNDED_RECT_SHAPE(4);

    fun getTypeValue() = typeValue
}