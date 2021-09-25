package com.gcode.widget

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

/**
 *作者:created by HP on 2021/3/7 23:34
 *邮箱:sakurajimamai2020@qq.com
 */
/**
 * ShapeButton属性
 * @property buttonShape Int
 * @property gradientDirection Int
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

    //按钮的种类
    private var buttonShape: Int = ShapeButtonShapeType.RECT_SHAPE.typeValue

    //按钮颜色渐变种类
    private var gradientDirection: Int = ShapeButtonGradientType.LINEAR_GRADIENT.typeValue

    /**
     * 矩形类按钮的长宽,设定方法参考
     * @see ShapeButton.setRectButtonWidth
     * @see ShapeButton.setRectButtonHeight
     * 该值不能在外部获取,如需获取请参考以下变量 [buttonWidth] [buttonHeight]
     */
    private var rectButtonWidth:Float = 0f
    private var rectButtonHeight:Float = 0f

    /**
     * 圆形按钮的半径长度,设定方法参考
     * @see ShapeButton.setOvalButtonRadius
     * 该值不能在外部获取,如需获取请参考以下变量 [buttonWidth] [buttonHeight]
     */
    private var ovalButtonRadius: Float = 0f

    //按钮的宽度 用于外部调用
    val buttonWidth:Float
        get() = (if(buttonShape == ShapeButtonShapeType.OVAL_SHAPE.typeValue){ 2*ovalButtonRadius }else{rectButtonWidth})/density

    //按钮的高度 用于外部调用
    val buttonHeight:Float
        get() = (if(buttonShape == ShapeButtonShapeType.OVAL_SHAPE.typeValue){ 2*ovalButtonRadius }else{rectButtonHeight})/density

    /**
     * 圆角按钮的圆角半径,设定方法
     * @see ShapeButton.setRoundedRectCornerRadius
     * 获取方法
     * @see ShapeButton.getRoundedRectCornerRadius
     */
    private var roundedRectCornerRadius: Float = 0f

    /**
     * 任意圆角按钮的四个圆角半径,设定方法
     * @see ShapeButton.setAnyRoundedRectCornerRadius
     * 获取方法例如
     * @see ShapeButton.getLeftTopCornerRadius
     * 单独设定圆角半径参考下列方法等
     * @see ShapeButton.setLeftTopCornerRadius
     */
    private var leftTopCornerRadius: Float = 0f
    private var leftBottomCornerRadius: Float = 0f
    private var rightTopCornerRadius: Float = 0f
    private var rightBottomCornerRadius: Float = 0f

    //按钮是否填充
    var isSolid: Boolean = false
        private set

    //按钮的边框宽度
    private var strokeWidth: Float = 0f

    //是否采用渐变填充色
    var isSolidColorGradient: Boolean = false
        private set

    /**
     * 渐变填充色
     * 注意:这些颜色均为0xAARRGGBB形式的单一颜色值。
     * 设定方法参考
     * @see ShapeButton.setSolidColorGradient
     */
    var startSolidColor: Int = 0
        private set
    var centerSolidColor: Int = 0
        private set
    var endSolidColor: Int = 0
        private set

    /**
     * 按钮不同状态背景色
     * 注意:这些颜色均为0xAARRGGBB形式的单一颜色值。
     * 设定方法
     * @see ShapeButton.setBgColorStateList
     * 单独设定[ShapeButton.normalBgColor]时参考下列方法
     * @see ShapeButton.setNormalBgColor
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
     * 按钮不同状态边框色
     * 注意:这些颜色均为0xAARRGGBB形式的单一颜色值。
     * 设定方法
     * @see ShapeButton.setStrokeColorStateList
     * 单独设定[ShapeButton.normalStrokeColor]时参考下列方法
     * @see ShapeButton.setNormalStrokeColor
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
     * 背景在不同点击状态下的颜色
     * 设定方法
     * @see ShapeButton.setBgColorStateList
     */
    private val statesBgColor = IntArray(6) { it.inc() }

    /**
     * 边框在不同点击状态下的颜色
     * 设定方法
     * @see ShapeButton.setStrokeColorStateList
     */
    private val statesStrokeColor = IntArray(6) { it.inc() }

    //用于设定尺寸
    private val density = context.resources.displayMetrics.density

    /**
     * 其支持的按钮类型请参考 [ShapeButtonShapeType]
     * @param shapeButtonShapeType ShapeButtonShapeType
     */
    fun setButtonShapeType(shapeButtonShapeType: ShapeButtonShapeType) {
        buttonShape = shapeButtonShapeType.typeValue
    }

    /**
     * 获取按钮的形状
     * @return ShapeButtonShapeType
     */
    fun getButtonShapeType() = when(buttonShape){
        1->ShapeButtonShapeType.OVAL_SHAPE
        2->ShapeButtonShapeType.RECT_SHAPE
        3->ShapeButtonShapeType.ROUNDED_RECT_SHAPE
        4->ShapeButtonShapeType.ANY_ROUNDED_RECT_SHAPE
        else->ShapeButtonShapeType.RECT_SHAPE
    }

    /**
     * 设置矩形按钮的宽度
     * @param rectButtonWidth Float
     */
    fun setRectButtonWidth(@FloatRange(from = 0.0)rectButtonWidth:Float){
        this.rectButtonWidth = rectButtonWidth*density
    }

    /**
     * 设置矩形按钮的高度
     * @param rectButtonHeight Float
     */
    fun setRectButtonHeight(@FloatRange(from = 0.0)rectButtonHeight:Float){
        this.rectButtonHeight = rectButtonHeight*density
    }

    /**
     * 设置圆形按钮的半径 单位是dp
     * @param ovalButtonRadius Float
     */
    fun setOvalButtonRadius(@FloatRange(from = 0.0)ovalButtonRadius: Float) {
        this.ovalButtonRadius = ovalButtonRadius*density
    }

    /**
     * 设置圆角矩形的圆角半径 单位是dp
     * @param roundedRectCornerRadius Float
     */
    fun setRoundedRectCornerRadius(@FloatRange(from = 0.0)roundedRectCornerRadius: Float) {
        this.roundedRectCornerRadius = roundedRectCornerRadius*density
    }

    /**
     * 获取到圆角矩形的圆角半径 单位是dp
     * @return Float
     */
    fun getRoundedRectCornerRadius() = roundedRectCornerRadius/density

    /**
     * 设定任意圆角按钮的圆角半径 单位是dp
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

    //设定任意圆角矩形的圆角半径 单位dp
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

    //获取任意圆角矩形的圆角半径 单位dp
    fun getLeftTopCornerRadius() = leftTopCornerRadius/density
    fun getLeftBottomCornerRadius() = leftBottomCornerRadius/density
    fun getRightTopCornerRadius() = rightTopCornerRadius/density
    fun getRightBottomCornerRadius() = rightBottomCornerRadius/density

    /**
     * 设定按钮是否填充
     * @param isSolid Boolean
     */
    fun setIsSolid(isSolid: Boolean){
        this.isSolid = isSolid
    }

    /**
     * 边框宽度 单位dp
     * @param strokeWidth Float
     */
    fun setStrokeWidth(@FloatRange(from = 0.0)strokeWidth: Float) {
        this.strokeWidth = strokeWidth*density
    }

    /**
     * 获取边框宽度
     * @return Float
     */
    fun getStrokeWidth()=strokeWidth/density

    /**
     * 设置是否采用渐变填充色
     * @param isSolidColorGradient Boolean
     */
    fun setIsSolidColorGradient(isSolidColorGradient: Boolean) {
        this.isSolidColorGradient = isSolidColorGradient
    }

    /**
     * 设置渐变填充色
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
     * 设定默认背景色
     * @param normalBgColor Int
     * 注意:调用此方法会默认将所有状态下按钮的颜色均为[ShapeButton.normalBgColor]
     * 如果想要设定不同状态下的颜色,建议你使用下面的方法:
     * @see ShapeButton.setBgColorStateList
     */
    fun setNormalBgColor(@ColorInt normalBgColor: Int) {
        this.normalBgColor = normalBgColor
        for ((index, _) in statesBgColor.withIndex()){
            statesBgColor[index] = normalBgColor
        }
    }

    /**
     * 设定默认边框色
     * @param normalStrokeColor Int
     * 注意:调用此方法会默认将所有状态下按钮的颜色均为[ShapeButton.normalStrokeColor]
     * 如果想要设定不同状态下的颜色,建议你使用下面的方法:
     * @see ShapeButton.setStrokeColorStateList
     */
    fun setNormalStrokeColor(@ColorInt normalStrokeColor: Int) {
        this.normalStrokeColor = normalStrokeColor
        for ((index, _) in statesStrokeColor.withIndex()){
            statesStrokeColor[index] = normalStrokeColor
        }
    }

    /**
     * 设置背景色
     * @param normal Int 默认状态背景色
     * @param pressed Int 指当用户点击或者触摸该控件的背景色
     * @param focused Int 指当前控件获得焦点时的背景色
     * @param unable Int 指当前窗口获得焦点时的背景色
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
     * 设置背景色
     * @param normal Int 默认状态边框色
     * @param pressed Int 指当用户点击或者触摸该控件的边框色
     * @param focused Int 指当前控件获得焦点时的边框色
     * @param unable Int 指当前窗口获得焦点时的边框色
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
     * 其支持的按钮类型请参考 [ShapeButtonGradientType]
     * @param shapeButtonGradientType ShapeButtonGradientType
     */
    fun setGradientDirectionType(shapeButtonGradientType: ShapeButtonGradientType) {
        gradientDirection = shapeButtonGradientType.typeValue
    }

    /**
     * 按钮的渐变类型
     * @return ShapeButtonGradientType
     */
    fun getGradientDirectionTyp() = when(gradientDirection){
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

            gradientType = gradientDirection

            /**
             * 基于这种设计模式,会使得按钮存在一个缺陷,就是无法设定渐变色为状态颜色
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

        gradientDirection = ta.getInteger(R.styleable.ShapeButton_button_gradient_type, GradientDrawable.LINEAR_GRADIENT)

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

        ta.recycle() //回收TypedArray
    }
}