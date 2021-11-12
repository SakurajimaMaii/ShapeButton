package com.gcode.shapebuttonsampledemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.gcode.gstylebuttonsampledemo.R
import com.gcode.shapebutton.ShapeButton
import com.gcode.shapebutton.ShapeButtonConstant
import com.gcode.vasttools.utils.DensityUtils
import com.gcode.vasttools.utils.DensityUtils.dp

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        //val button2 = findViewById<GStyleButton>(R.id.button2)

        val button3 = findViewById<ShapeButton>(R.id.button3)
        val button4 = findViewById<ShapeButton>(R.id.button4)

        button4.isEnabled = false
        button4.apply {
            setButtonShapeType(ShapeButtonConstant.ANY_ROUNDED_RECT_SHAPE)
            setAnyRoundedRectCornerRadius(10f,0f,20f,0f)
            setIsSolid(true)
            setIsSolidColorGradient(true)
            setGradientOrientation(ShapeButtonConstant.LINEAR_GRADIENT)
            setSolidColorGradient(
                ContextCompat.getColor(context, R.color.red),
                ContextCompat.getColor(context, R.color.design_default_color_primary_dark),
                ContextCompat.getColor(context, R.color.design_default_color_primary_variant)
            )
            setStrokeWidth(10f)
            setRectButtonWidth(500f)
            setRectButtonHeight(300f)
            setBgColorStateList(
                ContextCompat.getColor(context, R.color.palevioletred),
                ContextCompat.getColor(context, R.color.blue),
                ContextCompat.getColor(context, R.color.mediumvioletred),
                ContextCompat.getColor(context, R.color.red),
            )
            setStrokeColorStateList(
                ContextCompat.getColor(context, R.color.yellow),
                ContextCompat.getColor(context, R.color.greenyellow),
                ContextCompat.getColor(context, R.color.orange),
                ContextCompat.getColor(context, R.color.darkgrey),
            )
            create()
        }

        button3.setOnClickListener {
            Log.d("MyTest","${button4.leftTopCornerRadius} ${10f.dp}")
        }
    }
}