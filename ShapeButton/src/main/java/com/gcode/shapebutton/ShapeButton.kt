package com.gcode.shapebutton

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.gcode.widget.R

/**
 *作者:created by HP on 2021/3/7 23:34
 *邮箱:sakurajimamai2020@qq.com
 */
/**
 * ShapeButton
 * @property buttonShape Int
 * @property gradientOrientation Int
 * @property rectButtonWidth Float
 * @property rectButtonHeight Float
 * @property ovalButtonRadius Float
 * @property buttonWidth Float
 * @property buttonHeight Float
 * @property roundedRectCornerRadius Float
 * @property leftTopCornerRadius Float
 * @property leftBottomCornerRadius Float
 * @property rightTopCornerRadius Float
 * @property rightBottomCornerRadius Float
 * @property isSolid Boolean
 * @property strokeWidth Float
 * @property isSolidColorGradient Boolean
 * @property startSolidColor Int
 * @property centerSolidColor Int
 * @property endSolidColor Int
 * @property normalBgColor Int
 * @property pressedBgColor Int
 * @property focusedBgColor Int
 * @property unableBgColor Int
 * @property normalStrokeColor Int
 * @property pressedStrokeColor Int
 * @property focusedStrokeColor Int
 * @property unableStrokeColor Int
 * @property states Array<IntArray?>
 * @property statesBgColor IntArray
 * @property statesStrokeColor IntArray
 * @property density Float
 * @constructor
 */
class ShapeButton constructor(context: Context, attrs: AttributeSet) : AppCompatButton(context, attrs) {

    companion object{
        const val viewTag = "ShapeButton"
    }

    /**
     * Button shape
     * [ShapeButtonShapeType.OVAL_SHAPE]
     * [ShapeButtonShapeType.RECT_SHAPE]
     * [ShapeButtonShapeType.ROUNDED_RECT_SHAPE]
     * [ShapeButtonShapeType.ANY_ROUNDED_RECT_SHAPE]
     */
    private var buttonShape: Int = ShapeButtonShapeType.RECT_SHAPE.typeValue

    /**
     * Button solid color gradient orientation.
     */
    private var gradientOrientation: Int = ShapeButtonGradientType.LINEAR_GRADIENT.typeValue

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
    private var rectButtonWidth:Float = 0f
    private var rectButtonHeight:Float = 0f

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
    val buttonWidth:Float
        get() = (if(buttonShape == ShapeButtonShapeType.OVAL_SHAPE.typeValue){ 2*ovalButtonRadius }else{rectButtonWidth})/density

    /**
     * Button height(in pixels).
     */
    val buttonHeight:Float
        get() = (if(buttonShape == ShapeButtonShapeType.OVAL_SHAPE.typeValue){ 2*ovalButtonRadius }else{rectButtonHeight})/density

    /**
     * Rounded rect corner radius.
     * @see setRoundedRectCornerRadius
     * @see getRoundedRectCornerRadius
     */
    private var roundedRectCornerRadius: Float = 0f

    /**
     * Corner radius for any rounded corner rect.
     * @see setAnyRoundedRectCornerRadius
     * @see getLeftTopCornerRadius
     * @see setLeftTopCornerRadius
     */
    private var leftTopCornerRadius: Float = 0f
    private var leftBottomCornerRadius: Float = 0f
    private var rightTopCornerRadius: Float = 0f
    private var rightBottomCornerRadius: Float = 0f

    /**
     * `true` if the button is solid,`false` otherwise.
     * @see setIsSolid
     */
    var isSolid: Boolean = false
        private set

    /**
     * Stroke width
     */
    private var strokeWidth: Float = 0f

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
        set(2, intArrayOf(-android.R.attr.state_focused,android.R.attr.state_enabled))
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

    fun setButtonShapeType(shapeButtonShapeType: ShapeButtonShapeType) {
        buttonShape = shapeButtonShapeType.typeValue
    }

    fun getButtonShapeType() = when(buttonShape){
        1->ShapeButtonShapeType.OVAL_SHAPE
        2->ShapeButtonShapeType.RECT_SHAPE
        3->ShapeButtonShapeType.ROUNDED_RECT_SHAPE
        4->ShapeButtonShapeType.ANY_ROUNDED_RECT_SHAPE
        else->ShapeButtonShapeType.RECT_SHAPE
    }

    /**
     * Set rect button width(in pixels).
     */
    fun setRectButtonWidth(@FloatRange(from = 0.0)rectButtonWidth:Float){
        this.rectButtonWidth = rectButtonWidth*density
    }

    /**
     * Set rect button height(in pixels).
     */
    fun setRectButtonHeight(@FloatRange(from = 0.0)rectButtonHeight:Float){
        this.rectButtonHeight = rectButtonHeight*density
    }

    /**
     * Set oval button radius(in pixels).
     */
    fun setOvalButtonRadius(@FloatRange(from = 0.0)ovalButtonRadius: Float) {
        this.ovalButtonRadius = ovalButtonRadius*density
    }

    /**
     * Set corner radius(in pixels).
     */
    fun setRoundedRectCornerRadius(@FloatRange(from = 0.0)roundedRectCornerRadius: Float) {
        this.roundedRectCornerRadius = roundedRectCornerRadius*density
    }

    /**
     * Get corner radius(in pixels).
     */
    fun getRoundedRectCornerRadius() = roundedRectCornerRadius/density

    /**
     * Set corner radius for any rounded corner rect(in pixels).
     * @param leftTopCornerRadius Float
     * @param leftBottomCornerRadius Float
     * @param rightTopCornerRadius Float
     * @param rightBottomCornerRadius Float
     */
    fun setAnyRoundedRectCornerRadius(@FloatRange(from = 0.0)leftTopCornerRadius: Float = 0f,
                                      @FloatRange(from = 0.0)leftBottomCornerRadius: Float = 0f,
                                      @FloatRange(from = 0.0)rightTopCornerRadius: Float = 0f,
                                      @FloatRange(from = 0.0)rightBottomCornerRadius: Float = 0f

    ){
        this.leftTopCornerRadius = leftTopCornerRadius*density
        this.leftBottomCornerRadius = leftBottomCornerRadius*density
        this.rightTopCornerRadius = rightTopCornerRadius*density
        this.rightBottomCornerRadius = rightBottomCornerRadius*density
    }

    fun setLeftTopCornerRadius(@FloatRange(from = 0.0)leftTopCornerRadius: Float = 0f) {
        this.leftTopCornerRadius = leftTopCornerRadius*density
    }
    fun getLeftBottomCornerRadius(@FloatRange(from = 0.0)leftBottomCornerRadius: Float = 0f){
        this.leftBottomCornerRadius = leftBottomCornerRadius*density
    }
    fun getRightTopCornerRadius(@FloatRange(from = 0.0)rightTopCornerRadius: Float = 0f){
        this.rightTopCornerRadius = rightTopCornerRadius*density
    }
    fun getRightBottomCornerRadius(@FloatRange(from = 0.0)rightBottomCornerRadius: Float = 0f) {
        this.rightBottomCornerRadius = rightBottomCornerRadius*density
    }

    // Get corner radius for any rounded corner rect(in pixels).
    fun getLeftTopCornerRadius() = leftTopCornerRadius/density
    fun getLeftBottomCornerRadius() = leftBottomCornerRadius/density
    fun getRightTopCornerRadius() = rightTopCornerRadius/density
    fun getRightBottomCornerRadius() = rightBottomCornerRadius/density

    /**
     * `true` is solid color gradient,`false` otherwise.
     */
    fun setIsSolid(isSolid: Boolean){
        this.isSolid = isSolid
    }

    /**
     * Set button stroke width(in pixels).
     */
    fun setStrokeWidth(@FloatRange(from = 0.0)strokeWidth: Float) {
        this.strokeWidth = strokeWidth*density
    }

    /**
     * Get button stroke width(in pixels).
     */
    fun getStrokeWidth()=strokeWidth/density

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
    fun setSolidColorGradient(@ColorInt startSolidColor: Int,@ColorInt centerSolidColor: Int,@ColorInt endSolidColor: Int) {
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
        for ((index, _) in statesBgColor.withIndex()){
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
        for ((index, _) in statesStrokeColor.withIndex()){
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
     * @see ShapeButtonGradientType
     */
    fun setGradientOrientationType(shapeButtonGradientType: ShapeButtonGradientType) {
        gradientOrientation = shapeButtonGradientType.typeValue
    }

    fun getGradientDirectionTyp() = when(gradientOrientation){
        GradientDrawable.LINEAR_GRADIENT->ShapeButtonGradientType.LINEAR_GRADIENT
        GradientDrawable.RADIAL_GRADIENT->ShapeButtonGradientType.RADIAL_GRADIENT
        GradientDrawable.SWEEP_GRADIENT->ShapeButtonGradientType.SWEEP_GRADIENT
        else->ShapeButtonGradientType.LINEAR_GRADIENT
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec))
    }

    private fun measureWidth(measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize: Int = MeasureSpec.getSize(measureSpec)
        return when (specMode) {
            MeasureSpec.EXACTLY ->
                if(specSize<paddingLeft + paddingRight + buttonWidth.toInt()){specSize}else{
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
                if(specSize<paddingTop + paddingBottom + buttonHeight.toInt()){specSize}else{
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

            gradientType = gradientOrientation

            /**
             * Based on this design pattern, there will be a defect in the button,
             * that is, the gradient color cannot be set as the state color.
             */
            if (isSolid) {
                if (isSolidColorGradient) {
                    colors = intArrayOf(startSolidColor, centerSolidColor, endSolidColor)
                } else {
                    color = ColorStateList(states, statesBgColor)
                }
            }

            setStroke(strokeWidth.toInt(), ColorStateList(states, statesStrokeColor))

            when (buttonShape) {
                ShapeButtonShapeType.OVAL_SHAPE.typeValue -> {
                    shape = GradientDrawable.OVAL
                }
                ShapeButtonShapeType.RECT_SHAPE.typeValue -> {
                    shape = GradientDrawable.RECTANGLE
                }
                ShapeButtonShapeType.ROUNDED_RECT_SHAPE.typeValue -> {
                    cornerRadius = roundedRectCornerRadius
                }
                ShapeButtonShapeType.ANY_ROUNDED_RECT_SHAPE.typeValue -> {
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

        buttonShape = ta.getInteger(R.styleable.ShapeButton_button_shape, ShapeButtonShapeType.RECT_SHAPE.typeValue)

        ovalButtonRadius = ta.getDimension(R.styleable.ShapeButton_button_oval_radius, 0f)

        rectButtonWidth = ta.getDimension(R.styleable.ShapeButton_button_rect_width,0f)
        rectButtonHeight = ta.getDimension(R.styleable.ShapeButton_button_rect_height,0f)

        roundedRectCornerRadius = ta.getDimension(R.styleable.ShapeButton_button_rounded_rect_corner_radius, 0f)
        leftTopCornerRadius = ta.getDimension(R.styleable.ShapeButton_button_left_top_corner_radius, 0f)
        leftBottomCornerRadius = ta.getDimension(R.styleable.ShapeButton_button_left_bottom_corner_radius, 0f)
        rightTopCornerRadius = ta.getDimension(R.styleable.ShapeButton_button_right_top_corner_radius, 0f)
        rightBottomCornerRadius = ta.getDimension(R.styleable.ShapeButton_button_right_bottom_corner_radius, 0f)

        isSolid = ta.getBoolean(R.styleable.ShapeButton_button_is_solid, true)

        strokeWidth = ta.getDimension(R.styleable.ShapeButton_button_stroke_width, 0f)

        isSolidColorGradient = ta.getBoolean(R.styleable.ShapeButton_button_is_solid_color_gradient, false)
        startSolidColor = ta.getColor(R.styleable.ShapeButton_button_start_solid_color, ContextCompat.getColor(context, R.color.white))
        centerSolidColor = ta.getColor(R.styleable.ShapeButton_button_center_solid_color, ContextCompat.getColor(context, R.color.white))
        endSolidColor = ta.getColor(R.styleable.ShapeButton_button_end_solid_color, ContextCompat.getColor(context, R.color.white))

        normalBgColor = ta.getColor(R.styleable.ShapeButton_button_normal_bg_color, ContextCompat.getColor(context, R.color.default_color))
        pressedBgColor = ta.getColor(R.styleable.ShapeButton_button_pressed_bg_color, normalBgColor)
        focusedBgColor = ta.getColor(R.styleable.ShapeButton_button_focused_bg_color, normalBgColor)
        unableBgColor = ta.getColor(R.styleable.ShapeButton_button_unable_bg_color, normalBgColor)

        normalStrokeColor = ta.getColor(R.styleable.ShapeButton_button_normal_stroke_color, ContextCompat.getColor(context, R.color.default_color))
        pressedStrokeColor = ta.getColor(R.styleable.ShapeButton_button_pressed_stroke_color, normalStrokeColor)
        focusedStrokeColor = ta.getColor(R.styleable.ShapeButton_button_focused_stroke_color, normalStrokeColor)
        unableStrokeColor = ta.getColor(R.styleable.ShapeButton_button_unable_stroke_color, normalStrokeColor)

        gradientOrientation = ta.getInteger(R.styleable.ShapeButton_button_gradient_type, GradientDrawable.LINEAR_GRADIENT)

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