package com.gcode.gstylebutton

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat


/**
 *作者:created by HP on 2021/3/7 23:34
 *邮箱:sakurajimamai2020@qq.com
 */
/**
 * GStyleButton属性
 * @property buttonShape Int
 * @property ovalRadius Float 圆形按钮半径
 * @property roundedRectCornerRadius Float 圆角矩形圆角半径
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
 * @property statesBgColor IntArray 背景状态色
 * @property statesStrokeColor IntArray 边框状态色
 * @property gradientDirection Int
 * @property _mWidth Float
 * @property mWidth Float
 * @property _mHeight Float
 * @property mHeight Float
 */
class GStyleButton : AppCompatButton {

    private var buttonShape: Int = GStyleButtonShapeType.RECT_SHAPE.getTypeValue()

    /**
     * 其支持的按钮类型请参考 [GStyleButtonShapeType]
     * @param gStyleButtonShapeType GStyleButtonShapeType
     * @return GStyleButton
     */
    fun setButtonShapeType(gStyleButtonShapeType: GStyleButtonShapeType): GStyleButton {
        buttonShape = gStyleButtonShapeType.getTypeValue()
        return this
    }

    private var ovalRadius: Float = 0f

    fun setOvalRadius(ovalRadius: Float): GStyleButton {
        this.ovalRadius = ovalRadius
        return this
    }

    private var roundedRectCornerRadius: Float = 0f

    fun setRoundedRectCornerRadius(roundedRectCornerRadius: Float): GStyleButton {
        this.roundedRectCornerRadius = roundedRectCornerRadius
        return this
    }

    /**
     * 链式编程设定值请参考下面的方法
     * @see GStyleButton.setAnyRoundedRectCornerRadius
     */
    private var leftTopCornerRadius: Float = 0f

    private var leftBottomCornerRadius: Float = 0f

    private var rightTopCornerRadius: Float = 0f

    private var rightBottomCornerRadius: Float = 0f

    fun setAnyRoundedRectCornerRadius(leftTopCornerRadius: Float = 0f,
                                      leftBottomCornerRadius: Float = 0f,
                                      rightTopCornerRadius: Float = 0f,
                                      rightBottomCornerRadius: Float = 0f

    ): GStyleButton {
        this.leftTopCornerRadius = leftTopCornerRadius
        this.leftBottomCornerRadius = leftBottomCornerRadius
        this.rightTopCornerRadius = rightTopCornerRadius
        this.rightBottomCornerRadius = rightBottomCornerRadius
        return this
    }

    private var isSolid: Boolean = true

    fun setIsSolid(isSolid: Boolean): GStyleButton {
        this.isSolid = isSolid
        return this
    }

    private var strokeWidth: Float = 0f

    fun setStrokeWidth(strokeWidth: Float): GStyleButton {
        this.strokeWidth = strokeWidth
        return this
    }

    private var isSolidColorGradient: Boolean = false

    fun setIsSolidColorGradient(isSolidColorGradient: Boolean): GStyleButton {
        this.isSolidColorGradient = isSolidColorGradient
        return this
    }

    /**
     * 链式编程时请参考下列设定方法
     * @see GStyleButton.setSolidColorGradient
     */
    private var startSolidColor: Int = 0

    private var centerSolidColor: Int = 0

    private var endSolidColor: Int = 0

    /**
     * 设置渐变填充色
     * @param startSolidColor Int
     * @param centerSolidColor Int
     * @param endSolidColor Int
     * @return GStyleButton
     */
    fun setSolidColorGradient(startSolidColor: Int, centerSolidColor: Int, endSolidColor: Int): GStyleButton {
        this.startSolidColor = startSolidColor
        this.centerSolidColor = centerSolidColor
        this.endSolidColor = endSolidColor
        return this
    }

    /**
     * 使用链式编程设定时请参考下列方法:
     * @see GStyleButton.setBgColorStateList
     * 单独设定[GStyleButton.normalBgColor]时参考下列方法
     * @see GStyleButton.setNormalBgColor
     */
    private var normalBgColor: Int = 0

    private var pressedBgColor: Int = 0

    private var focusedBgColor: Int = 0

    private var unableBgColor: Int = 0

    /**
     * 使用链式编程设定时请参考下列方法:
     * @see GStyleButton.setStrokeColorStateList
     * 单独设定[GStyleButton.normalStrokeColor]时参考下列方法
     * @see GStyleButton.setNormalStrokeColor
     */
    private var normalStrokeColor: Int = 0

    private var pressedStrokeColor: Int = 0

    private var focusedStrokeColor: Int = 0

    private var unableStrokeColor: Int = 0

    /**
     * 设定默认背景色
     * @param normalBgColor Int
     * @return GStyleButton
     * 注意:调用此方法会默认将所有状态下按钮的颜色均为[GStyleButton.normalBgColor]
     * 如果想要设定不同状态下的颜色,建议你使用下面的方法:
     * @see GStyleButton.setBgColorStateList
     */
    fun setNormalBgColor(normalBgColor: Int): GStyleButton {
        this.normalBgColor = normalBgColor
        for ((index, _) in statesBgColor.withIndex()){
            statesBgColor[index] = normalBgColor
        }
        return this
    }

    /**
     * 设定默认边框色
     * @param normalStrokeColor Int
     * @return GStyleButton
     * 注意:调用此方法会默认将所有状态下按钮的颜色均为[GStyleButton.normalStrokeColor]
     * 如果想要设定不同状态下的颜色,建议你使用下面的方法:
     * @see GStyleButton.setStrokeColorStateList
     */
    fun setNormalStrokeColor(normalStrokeColor: Int): GStyleButton {
        this.normalStrokeColor = normalStrokeColor
        for ((index, _) in statesStrokeColor.withIndex()){
            statesStrokeColor[index] = normalStrokeColor
        }
        return this
    }

    private val states = arrayOfNulls<IntArray>(6).apply {
        set(0, intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled))
        set(1, intArrayOf(android.R.attr.state_focused, android.R.attr.state_enabled))
        set(2, intArrayOf(android.R.attr.state_enabled))
        set(3, intArrayOf(android.R.attr.state_focused))
        set(4, intArrayOf(android.R.attr.state_window_focused))
        set(5, intArrayOf())
    }

    /**
     * 背景在不同点击状态下的颜色
     * 设定方法
     * @see GStyleButton.setBgColorStateList
     */
    private val statesBgColor = IntArray(6) { it.inc() }

    /**
     * 边框在不同点击状态下的颜色
     * 设定方法
     * @see GStyleButton.setStrokeColorStateList
     */
    private val statesStrokeColor = IntArray(6) { it.inc() }

    /**
     * 设置背景色
     * @param normal Int 默认状态背景色
     * @param pressed Int 指当用户点击或者触摸该控件的背景色
     * @param focused Int 指当前控件获得焦点时的背景色
     * @param unable Int 指当前窗口获得焦点时的背景色
     * @return GStyleButton
     */
    fun setBgColorStateList(normal: Int, pressed: Int, focused: Int, unable: Int): GStyleButton {
        statesBgColor.apply {
            set(0, pressed)
            set(1, focused)
            set(2, normal)
            set(3, focused)
            set(4, unable)
            set(5, normal)
        }
        return this
    }

    /**
     * 设置背景色
     * @param normal Int 默认状态边框色
     * @param pressed Int 指当用户点击或者触摸该控件的边框色
     * @param focused Int 指当前控件获得焦点时的边框色
     * @param unable Int 指当前窗口获得焦点时的边框色
     * @return GStyleButton
     */
    fun setStrokeColorStateList(normal: Int, pressed: Int, focused: Int, unable: Int): GStyleButton {
        statesStrokeColor.apply {
            set(0, pressed)
            set(1, focused)
            set(2, normal)
            set(3, focused)
            set(4, unable)
            set(5, normal)
        }
        return this
    }

    private var gradientDirection: Int = GStyleButtonGradientType.LINEAR_GRADIENT.getTypeValue()

    /**
     * 其支持的按钮类型请参考 [GStyleButtonGradientType]
     * @param gradientDirectionType GStyleButtonGradientType
     * @return GStyleButton
     */
    fun setGradientDirectionType(gradientDirectionType: GStyleButtonGradientType): GStyleButton {
        gradientDirection = gradientDirectionType.getTypeValue()
        return this
    }

    private var _mWidth: Float = 0f

    val mWidth: Float
        get() = _mWidth

    private var _mHeight: Float = 0f

    val mHeight: Float
        get() = _mHeight

    /**
     * @param context
     */
    constructor(context: Context) : super(context)

    /**
     * 在xml布局文件中使用时自动调用
     * @param context
     */
    @RequiresApi(Build.VERSION_CODES.R)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.GStyleButton)

        buttonShape = ta.getInteger(R.styleable.GStyleButton_button_shape, GStyleButtonShapeType.RECT_SHAPE.getTypeValue())

        ovalRadius = ta.getDimension(R.styleable.GStyleButton_oval_radius, 0f)

        roundedRectCornerRadius = ta.getDimension(R.styleable.GStyleButton_rounded_rect_corner_radius, 0f)
        leftTopCornerRadius = ta.getDimension(R.styleable.GStyleButton_left_top_corner_radius, 0f)
        leftBottomCornerRadius = ta.getDimension(R.styleable.GStyleButton_left_bottom_corner_radius, 0f)
        rightTopCornerRadius = ta.getDimension(R.styleable.GStyleButton_right_top_corner_radius, 0f)
        rightBottomCornerRadius = ta.getDimension(R.styleable.GStyleButton_right_bottom_corner_radius, 0f)

        isSolid = ta.getBoolean(R.styleable.GStyleButton_is_solid, true)

        strokeWidth = ta.getDimension(R.styleable.GStyleButton_stroke_width, 0f)

        isSolidColorGradient = ta.getBoolean(R.styleable.GStyleButton_is_solid_color_gradient, false)
        startSolidColor = ta.getColor(R.styleable.GStyleButton_start_solid_color, ContextCompat.getColor(context, R.color.white))
        centerSolidColor = ta.getColor(R.styleable.GStyleButton_center_solid_color, ContextCompat.getColor(context, R.color.white))
        endSolidColor = ta.getColor(R.styleable.GStyleButton_end_solid_color, ContextCompat.getColor(context, R.color.white))

        normalBgColor = ta.getColor(R.styleable.GStyleButton_normal_bg_color, ContextCompat.getColor(context, R.color.default_color))
        pressedBgColor = ta.getColor(R.styleable.GStyleButton_pressed_bg_color, normalBgColor)
        focusedBgColor = ta.getColor(R.styleable.GStyleButton_focused_bg_color, normalBgColor)
        unableBgColor = ta.getColor(R.styleable.GStyleButton_unable_bg_color, normalBgColor)

        normalStrokeColor = ta.getColor(R.styleable.GStyleButton_normal_stroke_color, ContextCompat.getColor(context, R.color.default_color))
        pressedStrokeColor = ta.getColor(R.styleable.GStyleButton_pressed_stroke_color, normalStrokeColor)
        focusedStrokeColor = ta.getColor(R.styleable.GStyleButton_focused_stroke_color, normalStrokeColor)
        unableStrokeColor = ta.getColor(R.styleable.GStyleButton_unable_stroke_color, normalStrokeColor)

        gradientDirection = ta.getInteger(R.styleable.GStyleButton_gradient_type, GradientDrawable.LINEAR_GRADIENT)

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

    /**
     * 不会自动调用，如果有默认style时，在第二个构造函数中调用
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        _mWidth = measuredWidth.toFloat()
        _mHeight = measuredHeight.toFloat()
    }

    /**
     * 初始化Button
     * 在使用链式方法创建时,该方法必须在最后被调用
     */
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
                GStyleButtonShapeType.OVAL_SHAPE.getTypeValue() -> {
                    shape = GradientDrawable.OVAL
                }
                GStyleButtonShapeType.RECT_SHAPE.getTypeValue() -> {
                    shape = GradientDrawable.RECTANGLE
                }
                GStyleButtonShapeType.ROUNDED_RECT_SHAPE.getTypeValue() -> {
                    cornerRadius = roundedRectCornerRadius
                }
                GStyleButtonShapeType.ANY_ROUNDED_RECT_SHAPE.getTypeValue() -> {
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
}