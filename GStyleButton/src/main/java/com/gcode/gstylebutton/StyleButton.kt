package com.gcode.gstylebutton

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

/**
 *作者:created by HP on 2021/3/7 23:34
 *邮箱:sakurajimamai2020@qq.com
 */
class StyleButton : AppCompatButton {
    //按钮形状
    private var buttonShape = 0
    //圆角属性
    private var allCornerRadius:Float = 0f
    private var leftTopCornerRadius:Float = 0f
    private var leftBottomCornerRadius:Float = 0f
    private var rightTopCornerRadius:Float = 0f
    private var rightBottomCornerRadius:Float = 0f
    //是否填充 默认为true
    private var isFilling:Boolean = true
    //边框属性
    private var strokeWidth:Float = 0f
    private var strokeColor:Int = 0
    //边框是否采用渐变 默认为false
    private var isSolidColorGradient:Boolean = false
    private var startSolidColor:Int = 0
    private var centerSolidColor:Int = 0
    private var endSolidColor:Int = 0
    //默认背景色
    private var defaultBackgroundColor:Int = 0
    //渐变类型
    private var defaultGradientType:Int = 0

    private var mWidth = 0f
    private var mHeight = 0f

    /**
     * @param context
     */
    constructor(context: Context) : super(context)

    /**
     * 在xml布局文件中使用时自动调用
     * @param context
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        val ta:TypedArray = context.obtainStyledAttributes(attrs, R.styleable.StyleButton)

        buttonShape = ta.getInteger(R.styleable.StyleButton_button_shape,
            ButtonShapeType.rect_shape
        )

        allCornerRadius = ta.getDimension(R.styleable.StyleButton_corner_radius, 0f)
        leftTopCornerRadius = ta.getDimension(R.styleable.StyleButton_left_top_corner_radius, 0f)
        leftBottomCornerRadius = ta.getDimension(R.styleable.StyleButton_left_bottom_corner_radius, 0f)
        rightTopCornerRadius = ta.getDimension(R.styleable.StyleButton_right_top_corner_radius, 0f)
        rightBottomCornerRadius = ta.getDimension(R.styleable.StyleButton_right_bottom_corner_radius, 0f)

        isFilling = ta.getBoolean(R.styleable.StyleButton_is_filling,true)

        strokeWidth = ta.getDimension(R.styleable.StyleButton_stroke_width, 0f)
        strokeColor = ta.getColor(R.styleable.StyleButton_stroke_color, ContextCompat.getColor(context, R.color.white))

        isSolidColorGradient = ta.getBoolean(R.styleable.StyleButton_is_solid_color_gradient,false)
        startSolidColor = ta.getColor(R.styleable.StyleButton_start_solid_color,ContextCompat.getColor(context, R.color.white))
        centerSolidColor = ta.getColor(R.styleable.StyleButton_center_solid_color,ContextCompat.getColor(context, R.color.white))
        endSolidColor = ta.getColor(R.styleable.StyleButton_end_solid_color,ContextCompat.getColor(context, R.color.white))

        defaultBackgroundColor = ta.getColor(R.styleable.StyleButton_background_color, ContextCompat.getColor(context, R.color.default_color))

        defaultGradientType = ta.getInteger(R.styleable.StyleButton_gradient_type,GradientDrawable.LINEAR_GRADIENT)

        ta.recycle() //回收TypedArray

        init()
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
        mWidth = measuredWidth.toFloat() //通过getMeasuredWidth()方法获取
        mHeight = measuredHeight.toFloat()
    }

    /**
     * 初始化Button
     */
    private fun init(){
        val drawable: GradientDrawable = GradientDrawable()
        drawable.apply {

            gradientType = defaultGradientType

            if(isFilling){
                if(isSolidColorGradient){
                    colors = intArrayOf(
                        startSolidColor,centerSolidColor,endSolidColor
                    )
                }else{
                    setColor(defaultBackgroundColor)
                }
            }else{
                setStroke(2,ContextCompat.getColor(context,R.color.default_color))
            }

            when(buttonShape){
                ButtonShapeType.oval_shape -> {
                    shape = GradientDrawable.OVAL
                }
                ButtonShapeType.rect_shape -> {
                    shape = GradientDrawable.RECTANGLE
                }
                ButtonShapeType.corner_rect_shape -> {
                    cornerRadius = allCornerRadius
                }
                ButtonShapeType.any_corner_rect_shape -> {
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