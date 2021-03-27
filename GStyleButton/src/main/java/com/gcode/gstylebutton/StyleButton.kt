package com.gcode.gstylebutton

import android.content.Context
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
class StyleButton : AppCompatButton {

    //按钮形状
    var buttonShape = 0
    //圆形按钮半径
    var circleRadius:Float = 0f
    //圆角属性
    var allCornerRadius:Float = 0f
    var leftTopCornerRadius:Float = 0f
    var leftBottomCornerRadius:Float = 0f
    var rightTopCornerRadius:Float = 0f
    var rightBottomCornerRadius:Float = 0f
    //是否填充 默认为true
    var isFilling:Boolean = true
    //边框属性
    var isStroke:Boolean = false
    var strokeWidth:Float = 0f
    var strokeColor:Int = 0
    //边框是否采用渐变 默认为false
    var isSolidColorGradient:Boolean = false
    var startSolidColor:Int = 0
    var centerSolidColor:Int = 0
    var endSolidColor:Int = 0
    //默认背景色
    var defaultBackgroundColor:Int = 0
    //渐变类型
    var defaultGradientType:Int = 0

    private var _mWidth:Float = 0f

    val mWidth:Float
        get() = _mWidth

    private var _mHeight:Float = 0f

    val mHeight:Float
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
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){

        val ta:TypedArray = context.obtainStyledAttributes(attrs, R.styleable.StyleButton)

        buttonShape = ta.getInteger(R.styleable.StyleButton_button_shape, StyleButtonType.rect_shape)

        circleRadius = ta.getDimension(R.styleable.StyleButton_circle_radius, 0f)

        allCornerRadius = ta.getDimension(R.styleable.StyleButton_corner_radius, 0f)
        leftTopCornerRadius = ta.getDimension(R.styleable.StyleButton_left_top_corner_radius, 0f)
        leftBottomCornerRadius = ta.getDimension(R.styleable.StyleButton_left_bottom_corner_radius, 0f)
        rightTopCornerRadius = ta.getDimension(R.styleable.StyleButton_right_top_corner_radius, 0f)
        rightBottomCornerRadius = ta.getDimension(R.styleable.StyleButton_right_bottom_corner_radius, 0f)

        isFilling = ta.getBoolean(R.styleable.StyleButton_is_filling, true)

        isStroke = ta.getBoolean(R.styleable.StyleButton_is_stroke, false)
        strokeWidth = ta.getDimension(R.styleable.StyleButton_stroke_width, 0f)
        strokeColor = ta.getColor(R.styleable.StyleButton_stroke_color, ContextCompat.getColor(context, R.color.white))

        isSolidColorGradient = ta.getBoolean(R.styleable.StyleButton_is_solid_color_gradient, false)
        startSolidColor = ta.getColor(R.styleable.StyleButton_start_solid_color, ContextCompat.getColor(context, R.color.white))
        centerSolidColor = ta.getColor(R.styleable.StyleButton_center_solid_color, ContextCompat.getColor(context, R.color.white))
        endSolidColor = ta.getColor(R.styleable.StyleButton_end_solid_color, ContextCompat.getColor(context, R.color.white))

        defaultBackgroundColor = ta.getColor(R.styleable.StyleButton_background_color, ContextCompat.getColor(context, R.color.default_color))

        defaultGradientType = ta.getInteger(R.styleable.StyleButton_gradient_type, GradientDrawable.LINEAR_GRADIENT)

        ta.recycle() //回收TypedArray

        init(context)
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
     */
    @RequiresApi(Build.VERSION_CODES.R)
    private fun init(context: Context){

        val drawable: GradientDrawable = GradientDrawable()
        drawable.apply {

            gradientType = defaultGradientType

            if(isFilling){
                if(isSolidColorGradient){
                    colors = intArrayOf(
                            startSolidColor, centerSolidColor, endSolidColor
                    )
                }else{
                    setColor(defaultBackgroundColor)
                }
            }else{
                setStroke(5, ContextCompat.getColor(context, R.color.default_color))
            }

            if(isStroke){
                setStroke(strokeWidth.toInt(), strokeColor)
            }

            when(buttonShape){
                StyleButtonType.oval_shape -> {
                    shape = GradientDrawable.OVAL
                }
                StyleButtonType.rect_shape -> {
                    shape = GradientDrawable.RECTANGLE
                }
                StyleButtonType.corner_rect_shape -> {
                    cornerRadius = allCornerRadius
                }
                StyleButtonType.any_corner_rect_shape -> {
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