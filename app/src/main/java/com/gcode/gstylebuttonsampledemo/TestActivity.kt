package com.gcode.gstylebuttonsampledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

//        val styleButton  = findViewById<GStyleButton>(R.id.styleButton)
//
//        styleButton.setStyleButtonShapeType(GStyleButtonShapeType.ANY_ROUNDED_RECT_SHAPE)
//                .setAnyRoundedRectCornerRadius(10f,0f,0f,10f)
//                .setIsSolid(true)
//                .setSolidColorGradient(
//                        ContextCompat.getColor(this, R.color.design_default_color_primary),
//                        ContextCompat.getColor(this, R.color.design_default_color_primary_dark),
//                        ContextCompat.getColor(this, R.color.design_default_color_primary_variant)
//                )
//                .setIsStroke(true)
//                .setStrokeColor(ContextCompat.getColor(this,R.color.teal_200))
//                .setStrokeWidth(10f)
//                .setText(resources.getString(R.string.app_name))
//                .create()

        Log.i("TestActivity",styleButton.text.toString())
    }
}