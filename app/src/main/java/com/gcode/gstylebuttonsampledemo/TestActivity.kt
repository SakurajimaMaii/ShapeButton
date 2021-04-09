package com.gcode.gstylebuttonsampledemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.gcode.gstylebutton.GStyleButton
import com.gcode.gstylebutton.GStyleButtonGradientType
import com.gcode.gstylebutton.GStyleButtonShapeType

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val button3 = findViewById<GStyleButton>(R.id.button3)


        button3.setButtonShapeType(GStyleButtonShapeType.ROUNDED_RECT_SHAPE)
                .setRoundedRectCornerRadius(40f)
                .setIsSolid(true)
                .setStrokeWidth(10f)
                .setBgColorStateList(
                        ContextCompat.getColor(this, R.color.palevioletred),
                        ContextCompat.getColor(this, R.color.orchid),
                        ContextCompat.getColor(this, R.color.mediumvioletred),
                        ContextCompat.getColor(this, R.color.red),
                )
                .create()
    }
}