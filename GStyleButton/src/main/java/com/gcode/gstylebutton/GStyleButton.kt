package com.gcode.gstylebutton

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.Log
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
 * @property styleButtonShapeType GStyleButtonShapeType 按钮形状类型
 * @property ovalRadius Float 圆形按钮半径
 * @property roundedRectCornerRadius Float 圆角矩形圆角半径
 * @property leftTopCornerRadius Float
 * @property leftBottomCornerRadius Float
 * @property rightTopCornerRadius Float
 * @property rightBottomCornerRadius Float
 * @property isSolid Boolean 是否有填充
 * @property isStroke Boolean 是否有边框
 * @property strokeWidth Float 边框宽度
 * @property strokeColor Int 边框颜色
 * @property isSolidColorGradient Boolean 是否采用渐变填充色
 * @property startSolidColor Int 渐变开始颜色
 * @property centerSolidColor Int 渐变中间颜色
 * @property endSolidColor Int 渐变结束颜色
 * @property styleBackgroundColor Int button背景色
 * @property styleGradientType Int 渐变类型
 * @property _mWidth Float
 * @property mWidth Float
 * @property _mHeight Float
 * @property mHeight Float
 */
class GStyleButton : AppCompatButton {

    private var buttonShape:Int = GStyleButtonShapeType.RECT_SHAPE.getTypeValue()

    var styleButtonShapeType:GStyleButtonShapeType = GStyleButtonShapeType.RECT_SHAPE
        set(value){
            field = value
            buttonShape = value.getTypeValue()
            create()
        }

    fun setStyleButtonShapeType(styleButtonShapeType:GStyleButtonShapeType):GStyleButton{
        this.styleButtonShapeType = styleButtonShapeType
        buttonShape = styleButtonShapeType.getTypeValue()
        return this
    }

    var ovalRadius:Float = 0f
        set(value) {
            field = value
            create()
        }

    fun setOvalRadius(ovalRadius:Float):GStyleButton{
        this.ovalRadius = ovalRadius
        return this
    }

    var roundedRectCornerRadius:Float = 0f
        set(value){
            field = value
            create()
        }

    fun setRoundedRectCornerRadius(roundedRectCornerRadius:Float):GStyleButton{
        this.roundedRectCornerRadius = roundedRectCornerRadius
        return this
    }

    var leftTopCornerRadius:Float = 0f
        set(value) {
            field = value
            create()
        }

    private var leftBottomCornerRadius:Float = 0f
        set(value) {
            field = value
            create()
        }

    private var rightTopCornerRadius:Float = 0f
        set(value) {
            field = value
            create()
        }

    private var rightBottomCornerRadius:Float = 0f
        set(value) {
            field = value
            create()
        }

    fun setAnyRoundedRectCornerRadius(leftTopCornerRadius:Float = 0f,
                                      leftBottomCornerRadius:Float = 0f,
                                      rightTopCornerRadius:Float = 0f,
                                      rightBottomCornerRadius:Float = 0f

    ):GStyleButton{
        this.leftTopCornerRadius = leftTopCornerRadius
        this.leftBottomCornerRadius = leftBottomCornerRadius
        this.rightTopCornerRadius = rightTopCornerRadius
        this.rightBottomCornerRadius = rightBottomCornerRadius
        return this
    }

    var isSolid:Boolean = true
        set(value){
            field = value
            create()
        }

    fun setIsSolid(isSolid:Boolean):GStyleButton{
        this.isSolid = isSolid
        return this
    }

    var isStroke:Boolean = false
        set(value){
            field = value
            create()
        }

    fun setIsStroke(isStroke:Boolean):GStyleButton{
        this.isStroke = isStroke
        return this
    }

    var strokeWidth:Float = 0f
        set(value) {
            field = value
            create()
        }

    fun setStrokeWidth(strokeWidth:Float):GStyleButton{
        this.strokeWidth = strokeWidth
        return this
    }

    var strokeColor:Int = 0
        set(value) {
            field = value
            create()
        }

    fun setStrokeColor(strokeColor:Int):GStyleButton{
        this.strokeColor = strokeColor
        return this
    }

    var isSolidColorGradient:Boolean = false
        set(value) {
            field = value
            create()
        }

    fun setIsSolidColorGradient(isSolidColorGradient:Boolean):GStyleButton{
        this.isSolidColorGradient = isSolidColorGradient
        return this
    }

    var startSolidColor:Int = 0
        set(value) {
            field = value
            create()
        }

    var centerSolidColor:Int = 0
        set(value) {
            field = value
            create()
        }

    var endSolidColor:Int = 0
        set(value) {
            field = value
            create()
        }

    fun setSolidColorGradient(startSolidColor:Int,centerSolidColor:Int,endSolidColor:Int):GStyleButton{
        this.startSolidColor = startSolidColor
        this.centerSolidColor = centerSolidColor
        this.endSolidColor = endSolidColor
        return this
    }

    var styleBackgroundColor:Int = 0

    fun setStyleBackgroundColor(styleBackgroundColor:Int):GStyleButton{
        this.styleBackgroundColor = styleBackgroundColor
        return this
    }

    var styleGradientType:Int = 0
        set(value) {
            field = value
            create()
        }

    fun setStyleGradientType(styleGradientType:Int):GStyleButton{
        this.styleGradientType = styleGradientType
        return this
    }

    private var _mWidth:Float = 0f

    val mWidth:Float
        get() = _mWidth

    private var _mHeight:Float = 0f

    val mHeight:Float
        get() = _mHeight

    fun setText(text:String):GStyleButton{
        this.text = text
        return this
    }

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

        buttonShape = ta.getInteger(R.styleable.StyleButton_button_shape, GStyleButtonShapeType.RECT_SHAPE.getTypeValue())

        ovalRadius = ta.getDimension(R.styleable.StyleButton_oval_radius, 0f)

        roundedRectCornerRadius = ta.getDimension(R.styleable.StyleButton_rounded_rect_corner_radius, 0f)
        leftTopCornerRadius = ta.getDimension(R.styleable.StyleButton_left_top_corner_radius, 0f)
        leftBottomCornerRadius = ta.getDimension(R.styleable.StyleButton_left_bottom_corner_radius, 0f)
        rightTopCornerRadius = ta.getDimension(R.styleable.StyleButton_right_top_corner_radius, 0f)
        rightBottomCornerRadius = ta.getDimension(R.styleable.StyleButton_right_bottom_corner_radius, 0f)

        isSolid = ta.getBoolean(R.styleable.StyleButton_is_solid, true)

        isStroke = ta.getBoolean(R.styleable.StyleButton_is_stroke, false)
        strokeWidth = ta.getDimension(R.styleable.StyleButton_stroke_width, 0f)
        strokeColor = ta.getColor(R.styleable.StyleButton_stroke_color, ContextCompat.getColor(context, R.color.white))

        isSolidColorGradient = ta.getBoolean(R.styleable.StyleButton_is_solid_color_gradient, false)
        startSolidColor = ta.getColor(R.styleable.StyleButton_start_solid_color, ContextCompat.getColor(context, R.color.white))
        centerSolidColor = ta.getColor(R.styleable.StyleButton_center_solid_color, ContextCompat.getColor(context, R.color.white))
        endSolidColor = ta.getColor(R.styleable.StyleButton_end_solid_color, ContextCompat.getColor(context, R.color.white))

        styleBackgroundColor = ta.getColor(R.styleable.StyleButton_style_background_color, ContextCompat.getColor(context, R.color.default_color))

        styleGradientType = ta.getInteger(R.styleable.StyleButton_gradient_type, GradientDrawable.LINEAR_GRADIENT)

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
     */
    fun create(){

        val drawable: GradientDrawable = GradientDrawable()
        drawable.apply {

            gradientType = styleGradientType

            if(isSolid){
                if(isSolidColorGradient){
                    colors = intArrayOf(startSolidColor, centerSolidColor, endSolidColor)
                }else{
                    setColor(styleBackgroundColor)
                }
            }else{
                setStroke(5, ContextCompat.getColor(context, R.color.default_color))
            }

            if(isStroke){
                setStroke(strokeWidth.toInt(), strokeColor)
            }

            when(buttonShape){
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
                else -> {
                    Log.e("GStyleButton","GStyleButton Shape Type Error:The button shape type value must be one of the set values in GStyleButtonShapeType ")
                }
            }
        }
        background = drawable
    }


}