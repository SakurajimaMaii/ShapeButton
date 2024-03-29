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

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2021/8/7 20:03
// Description:
// Documentation:

class ShapeButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, style: Int = 0
) : AppCompatButton(context, attrs, style) {

    private val tag:String = this.javaClass.simpleName

    /**
     * Button shape
     * @see [ShapeButtonConstant.OVAL_SHAPE]
     * @see [ShapeButtonConstant.RECT_SHAPE]
     * @see [ShapeButtonConstant.ROUNDED_RECT_SHAPE]
     * @see [ShapeButtonConstant.ANY_ROUNDED_RECT_SHAPE]
     */
    var buttonShape: Int = ShapeButtonConstant.RECT_SHAPE
        private set

    /**
     * Button solid color gradient type.
     */
    var buttonGradientType: Int = GradientDrawable.LINEAR_GRADIENT
        private set

    /**
     * Button gradient orientation.
     *
     * When [buttonGradientType] is set to [GradientDrawable.LINEAR_GRADIENT],you should set
     * [buttonGradientOrientation].Otherwise, [GradientDrawable.Orientation.TOP_BOTTOM] will
     * be used as the default value
     */
    var buttonGradientOrientation: GradientDrawable.Orientation

    /**
     * Button gradient radius
     *
     * When [buttonGradientType] is set to [GradientDrawable.RADIAL_GRADIENT],you should set
     * [buttonGradientRadius].Otherwise, **0** will be used as the default value
     */
    var buttonGradientRadius: Int = 0

    /**
     * Rect button size,if you want to set
     * the value,please check
     * [ShapeButton.setRectButtonWidth]<br/>
     * [ShapeButton.setRectButtonHeight]
     *
     * The value cannot be got externally,
     * please refer to [buttonWidth]<br/>
     * [buttonHeight] if you need to get it.
     */
    private var rectButtonWidth: Float = 0f
    private var rectButtonHeight: Float = 0f

    /**
     * Oval button radius.if you want to set
     * the value,please check
     * [ShapeButton.setOvalButtonRadius]
     *
     * The value cannot be got externally,
     * please refer to [buttonWidth]<br/>
     * [buttonHeight] if you need to get it.
     */
    private var ovalButtonRadius: Float = 0f

    /**
     * Button width(in pixels).
     */
    val buttonWidth: Float
        get() = (if (buttonShape == ShapeButtonConstant.OVAL_SHAPE) {
            2 * ovalButtonRadius
        } else {
            rectButtonWidth
        })

    /**
     * Button height(in pixels).
     */
    val buttonHeight: Float
        get() = (if (buttonShape == ShapeButtonConstant.OVAL_SHAPE) {
            2 * ovalButtonRadius
        } else {
            rectButtonHeight
        })

    /**
     * Rounded rect corner radius(in pixels).
     */
    var roundedRectCornerRadius: Float = 0f
        private set

    /**
     * Left top corner radius(in pixels).
     */
    var leftTopCornerRadius: Float = 0f
        private set

    /**
     * Left bottom corner radius(in pixels).
     */
    var leftBottomCornerRadius: Float = 0f
        private set

    /**
     * Right top corner radius(in pixels).
     */
    var rightTopCornerRadius: Float = 0f
        private set

    /**
     * Right bottom corner radius(in pixels).
     */
    var rightBottomCornerRadius: Float = 0f
        private set

    /**
     * `true` if the button is solid,`false` otherwise.
     * @see setIsSolid
     */
    var isSolid: Boolean = false
        private set

    /**
     * Stroke width(in pixels).
     */
    var strokeWidth: Float = 0f
        private set

    /**
     * `true` if the solid color is gradient,`false` otherwise.
     */
    var isSolidColorGradient: Boolean = false
        private set

    /**
     * Gradient color(in 0xAARRGGBB).

     * If you want to set the value,please check
     * [setSolidColorGradient]
     */
    var startSolidColor: Int = 0
        private set
    var centerSolidColor: Int = 0
        private set
    var endSolidColor: Int = 0
        private set

    /**
     * Button background color value in different states(in 0xAARRGGBB).
     *
     * **You should not use it if you want to set the gradient color**
     *
     * @see setBgColorStateList
     * @see setNormalBgColor
     */
    var normalBgColor: Int = 0
        private set
    var pressedBgColor: Int = 0
        private set
    var focusedBgColor: Int = 0
        private set
    var unableBgColor: Int = 0
        private set

    /**
     * Button stroke color value in different states(in 0xAARRGGBB).
     *
     * @see setStrokeColorStateList
     * @see setNormalStrokeColor
     */
    var normalStrokeColor: Int = 0
        private set
    var pressedStrokeColor: Int = 0
        private set
    var focusedStrokeColor: Int = 0
        private set
    var unableStrokeColor: Int = 0
        private set

    private val states = arrayOfNulls<IntArray>(6).apply {
        set(0, intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled))
        set(1, intArrayOf(android.R.attr.state_focused, android.R.attr.state_enabled))
        set(2, intArrayOf(-android.R.attr.state_focused, android.R.attr.state_enabled))
        set(3, intArrayOf(android.R.attr.state_focused))
        set(4, intArrayOf(android.R.attr.state_window_focused))
        set(5, intArrayOf())
    }

    /**
     * Button background color in different states(in 0xAARRGGBB).
     *
     * @see setBgColorStateList
     */
    private val statesBgColor = IntArray(6) { it.inc() }

    /**
     * Button stroke color in different states(in 0xAARRGGBB).
     *
     * @see setStrokeColorStateList
     */
    private val statesStrokeColor = IntArray(6) { it.inc() }

    private val density = context.resources.displayMetrics.density

    /**
     * Set buttonShape
     * @param buttonShape you can set the value from one of the
     * @see [ShapeButtonConstant.OVAL_SHAPE]
     * @see [ShapeButtonConstant.RECT_SHAPE]
     * @see [ShapeButtonConstant.ROUNDED_RECT_SHAPE]
     * @see [ShapeButtonConstant.ANY_ROUNDED_RECT_SHAPE]
     */
    fun setButtonShapeType(@ShapeButtonConstant.ShapeType buttonShape: Int) {
        this.buttonShape = buttonShape
    }

    /**
     * Set rect button width(in dp).
     */
    fun setRectButtonWidth(@FloatRange(from = 0.0) rectButtonWidth: Float) {
        this.rectButtonWidth = rectButtonWidth * density
    }

    /**
     * Set rect button height(in dp).
     */
    fun setRectButtonHeight(@FloatRange(from = 0.0) rectButtonHeight: Float) {
        this.rectButtonHeight = rectButtonHeight * density
    }

    /**
     * Set oval button radius(in dp).
     */
    fun setOvalButtonRadius(@FloatRange(from = 0.0) ovalButtonRadius: Float) {
        this.ovalButtonRadius = ovalButtonRadius * density
    }

    /**
     * Set corner radius(in pixels).
     */
    fun setRoundedRectCornerRadius(@FloatRange(from = 0.0) roundedRectCornerRadius: Float) {
        this.roundedRectCornerRadius = roundedRectCornerRadius * density
    }

    /**
     * Set corner radius for any rounded corner rect(in dp).
     * @param leftTopCornerRadius Float
     * @param leftBottomCornerRadius Float
     * @param rightTopCornerRadius Float
     * @param rightBottomCornerRadius Float
     */
    fun setAnyRoundedRectCornerRadius(
        @FloatRange(from = 0.0) leftTopCornerRadius: Float = 0f,
        @FloatRange(from = 0.0) leftBottomCornerRadius: Float = 0f,
        @FloatRange(from = 0.0) rightTopCornerRadius: Float = 0f,
        @FloatRange(from = 0.0) rightBottomCornerRadius: Float = 0f

    ) {
        this.leftTopCornerRadius = leftTopCornerRadius * density
        this.leftBottomCornerRadius = leftBottomCornerRadius * density
        this.rightTopCornerRadius = rightTopCornerRadius * density
        this.rightBottomCornerRadius = rightBottomCornerRadius * density
    }

    fun setLeftTopCornerRadius(@FloatRange(from = 0.0) leftTopCornerRadius: Float = 0f) {
        this.leftTopCornerRadius = leftTopCornerRadius * density
    }

    fun setLeftBottomCornerRadius(@FloatRange(from = 0.0) leftBottomCornerRadius: Float = 0f) {
        this.leftBottomCornerRadius = leftBottomCornerRadius * density
    }

    fun setRightTopCornerRadius(@FloatRange(from = 0.0) rightTopCornerRadius: Float = 0f) {
        this.rightTopCornerRadius = rightTopCornerRadius * density
    }

    fun setRightBottomCornerRadius(@FloatRange(from = 0.0) rightBottomCornerRadius: Float = 0f) {
        this.rightBottomCornerRadius = rightBottomCornerRadius * density
    }

    /**
     * `true` is solid color gradient,`false` otherwise.
     */
    fun setIsSolid(isSolid: Boolean) {
        this.isSolid = isSolid
    }

    /**
     * Set button stroke width(in pixels).
     */
    fun setStrokeWidth(@FloatRange(from = 0.0) strokeWidth: Float) {
        this.strokeWidth = strokeWidth * density
    }

    /**
     * `true` is solid color gradient,`false` otherwise.
     * @param isSolidColorGradient Boolean
     */
    fun setIsSolidColorGradient(isSolidColorGradient: Boolean) {
        this.isSolidColorGradient = isSolidColorGradient
    }

    /**
     * Set solid gradient color.
     * @param startSolidColor Int
     * @param centerSolidColor Int
     * @param endSolidColor Int
     */
    fun setSolidColorGradient(
        @ColorInt startSolidColor: Int,
        @ColorInt centerSolidColor: Int,
        @ColorInt endSolidColor: Int
    ) {
        this.startSolidColor = startSolidColor
        this.centerSolidColor = centerSolidColor
        this.endSolidColor = endSolidColor
    }

    /**
     * Set Normal Background Color
     *
     * Calling this method will default the background
     * color of the button in all states to [normalBgColor]
     *
     * If you want to set the background color in different
     * states,it is recommended that you use [setBgColorStateList]
     *
     * @param normalBgColor Int
     */
    fun setNormalBgColor(@ColorInt normalBgColor: Int) {
        this.normalBgColor = normalBgColor
        for ((index, _) in statesBgColor.withIndex()) {
            statesBgColor[index] = normalBgColor
        }
    }

    /**
     * Set Normal Stroke Color
     *
     * Calling this method will default the stroke
     * color of the button in all states to [normalStrokeColor]
     *
     * If you want to set the stroke color in different
     * states,it is recommended that you use [setStrokeColorStateList]
     * @param normalStrokeColor Int
     */
    fun setNormalStrokeColor(@ColorInt normalStrokeColor: Int) {
        this.normalStrokeColor = normalStrokeColor
        for ((index, _) in statesStrokeColor.withIndex()) {
            statesStrokeColor[index] = normalStrokeColor
        }
    }

    /**
     * Set background color of the button.
     * @param normal Int Default button background color
     * @param pressed Int Button background color when button is pressed.
     * @param focused Int Button background color when button is focused.
     * @param unable Int Button background color when button is unable.
     */
    fun setBgColorStateList(normal: Int, pressed: Int, focused: Int, unable: Int) {
        statesBgColor.apply {
            set(0, pressed)
            set(1, focused)
            set(2, normal)
            set(3, focused)
            set(4, unable)
            set(5, normal)
        }
    }

    /**
     * Set stroke color of the button.
     * @param normal Int Default button stroke color.
     * @param pressed Int Button stroke color when button is pressed.
     * @param focused Int Button stroke color when button is focused.
     * @param unable Int Button stroke color when button is unable.
     */
    fun setStrokeColorStateList(normal: Int, pressed: Int, focused: Int, unable: Int) {
        statesStrokeColor.apply {
            set(0, pressed)
            set(1, focused)
            set(2, normal)
            set(3, focused)
            set(4, unable)
            set(5, normal)
        }
    }

    /**
     * Set Gradient Orientation
     *
     * @param gradientType you can set the value from one of the
     * @see [GradientDrawable.LINEAR_GRADIENT]
     * @see [GradientDrawable.SWEEP_GRADIENT]
     * @see [GradientDrawable.RADIAL_GRADIENT]
     */
    fun setGradientType(@ShapeButtonConstant.GradientType gradientType: Int) {
        this.buttonGradientType = gradientType
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec))
    }

    private fun measureWidth(measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        Log.d(tag,"measureWidth,specMode is ${specMode.toString(2)} and specSize is $specSize")
        return when (specMode) {
            MeasureSpec.EXACTLY ->
                if (specSize < paddingLeft + paddingRight + buttonWidth.toInt()) {
                    specSize
                } else {
                    paddingLeft + paddingRight + buttonWidth.toInt()
                }
            MeasureSpec.AT_MOST -> {
                (paddingLeft + paddingRight + buttonWidth.toInt()).coerceAtMost(specSize)
            }
            else -> paddingLeft + paddingRight + buttonWidth.toInt()
        }
    }

    private fun measureHeight(measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        return when (specMode) {
            MeasureSpec.EXACTLY ->
                if (specSize < paddingTop + paddingBottom + buttonHeight.toInt()) {
                    specSize
                } else {
                    paddingTop + paddingBottom + buttonHeight.toInt()
                }
            MeasureSpec.AT_MOST -> {
                (paddingTop + paddingBottom + buttonHeight.toInt()).coerceAtMost(specSize)
            }
            else -> paddingTop + paddingBottom + buttonHeight.toInt()
        }
    }

    fun create() {
        val drawable = GradientDrawable()
        drawable.apply {

            gradientType = buttonGradientType

            /**
             * Based on this design pattern, there will be a defect in the button,
             * that is, the gradient color cannot be set as the state color.
             */
            if (isSolid) {
                if (isSolidColorGradient) {
                    orientation = buttonGradientOrientation

                    gradientRadius = buttonWidth.coerceAtLeast(buttonHeight) / 2

                    colors = intArrayOf(startSolidColor, centerSolidColor, endSolidColor)
                } else {
                    color = ColorStateList(states, statesBgColor)
                }
            }

            setStroke(strokeWidth.toInt(), ColorStateList(states, statesStrokeColor))

            when (buttonShape) {
                ShapeButtonConstant.OVAL_SHAPE -> {
                    shape = GradientDrawable.OVAL
                }
                ShapeButtonConstant.RECT_SHAPE -> {
                    shape = GradientDrawable.RECTANGLE
                }
                ShapeButtonConstant.ROUNDED_RECT_SHAPE -> {
                    cornerRadius = roundedRectCornerRadius
                }
                ShapeButtonConstant.ANY_ROUNDED_RECT_SHAPE -> {
                    cornerRadii = floatArrayOf(
                        leftTopCornerRadius,
                        leftTopCornerRadius,
                        rightTopCornerRadius,
                        rightTopCornerRadius,
                        rightBottomCornerRadius,
                        rightBottomCornerRadius,
                        leftBottomCornerRadius,
                        leftBottomCornerRadius
                    )
                }
            }
        }
        background = drawable
    }

    init {
        val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeButton)

        buttonShape = ta.getInteger(
            R.styleable.ShapeButton_button_shape,
            ShapeButtonConstant.RECT_SHAPE
        )

        ovalButtonRadius = ta.getDimension(R.styleable.ShapeButton_button_oval_radius, 0f)

        rectButtonWidth = ta.getDimension(R.styleable.ShapeButton_button_rect_width, 0f)
        rectButtonHeight = ta.getDimension(R.styleable.ShapeButton_button_rect_height, 0f)

        roundedRectCornerRadius =
            ta.getDimension(R.styleable.ShapeButton_button_rounded_rect_corner_radius, 0f)
        leftTopCornerRadius =
            ta.getDimension(R.styleable.ShapeButton_button_left_top_corner_radius, 0f)
        leftBottomCornerRadius =
            ta.getDimension(R.styleable.ShapeButton_button_left_bottom_corner_radius, 0f)
        rightTopCornerRadius =
            ta.getDimension(R.styleable.ShapeButton_button_right_top_corner_radius, 0f)
        rightBottomCornerRadius =
            ta.getDimension(R.styleable.ShapeButton_button_right_bottom_corner_radius, 0f)

        isSolid = ta.getBoolean(R.styleable.ShapeButton_button_is_solid, true)

        strokeWidth = ta.getDimension(R.styleable.ShapeButton_button_stroke_width, 0f)

        isSolidColorGradient =
            ta.getBoolean(R.styleable.ShapeButton_button_is_solid_color_gradient, false)
        startSolidColor = ta.getColor(
            R.styleable.ShapeButton_button_start_solid_color,
            ContextCompat.getColor(context, R.color.white)
        )
        centerSolidColor = ta.getColor(
            R.styleable.ShapeButton_button_center_solid_color,
            ContextCompat.getColor(context, R.color.white)
        )
        endSolidColor = ta.getColor(
            R.styleable.ShapeButton_button_end_solid_color,
            ContextCompat.getColor(context, R.color.white)
        )

        normalBgColor = ta.getColor(
            R.styleable.ShapeButton_button_normal_bg_color,
            ContextCompat.getColor(context, R.color.default_color)
        )
        pressedBgColor = ta.getColor(R.styleable.ShapeButton_button_pressed_bg_color, normalBgColor)
        focusedBgColor = ta.getColor(R.styleable.ShapeButton_button_focused_bg_color, normalBgColor)
        unableBgColor = ta.getColor(R.styleable.ShapeButton_button_unable_bg_color, normalBgColor)

        normalStrokeColor = ta.getColor(
            R.styleable.ShapeButton_button_normal_stroke_color,
            ContextCompat.getColor(context, R.color.default_color)
        )
        pressedStrokeColor =
            ta.getColor(R.styleable.ShapeButton_button_pressed_stroke_color, normalStrokeColor)
        focusedStrokeColor =
            ta.getColor(R.styleable.ShapeButton_button_focused_stroke_color, normalStrokeColor)
        unableStrokeColor =
            ta.getColor(R.styleable.ShapeButton_button_unable_stroke_color, normalStrokeColor)

        buttonGradientType = ta.getInteger(
            R.styleable.ShapeButton_button_gradient_type,
            GradientDrawable.LINEAR_GRADIENT
        )

        buttonGradientOrientation = GradientDrawable.Orientation.values()[ta.getInteger(
            R.styleable.ShapeButton_button_gradient_orientation,
            0
        )]

        buttonGradientRadius = ta.getInteger(
            R.styleable.ShapeButton_button_gradient_radius,
            0
        )

        statesBgColor.apply {
            set(0, pressedBgColor)
            set(1, focusedBgColor)
            set(2, normalBgColor)
            set(3, focusedBgColor)
            set(4, unableBgColor)
            set(5, normalBgColor)
        }

        statesStrokeColor.apply {
            set(0, pressedStrokeColor)
            set(1, focusedStrokeColor)
            set(2, normalStrokeColor)
            set(3, focusedStrokeColor)
            set(4, unableStrokeColor)
            set(5, normalStrokeColor)
        }

        create()

        ta.recycle()
    }
}