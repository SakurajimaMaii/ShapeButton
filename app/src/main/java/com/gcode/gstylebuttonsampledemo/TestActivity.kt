package com.gcode.gstylebuttonsampledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.gcode.gstylebutton.GStyleButton
import com.gcode.gstylebutton.GStyleButtonShapeType

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val button1 = findViewById<GStyleButton>(R.id.button1)
        val button2 = findViewById<GStyleButton>(R.id.button2)
        val button3 = findViewById<GStyleButton>(R.id.button3)
        val button4 = findViewById<GStyleButton>(R.id.button4)

        button1.setStyleButtonShapeType(GStyleButtonShapeType.ANY_ROUNDED_RECT_SHAPE)
                .GStyle_SetText("Hello")
                .GStyle_setTextColor(ContextCompat.getColor(this,R.color.corn_silk))
                .setAnyRoundedRectCornerRadius(40f,0f,0f,40f)
                .setIsSolid(true)
                .setIsSolidColorGradient(false)
                .setStyleBackgroundColor(ContextCompat.getColor(this,R.color.light_sea_blue))
                .create()

        button2.setStyleButtonShapeType(GStyleButtonShapeType.ROUNDED_RECT_SHAPE)
                .GStyle_SetText("OK")
                .GStyle_setTextColor(ContextCompat.getColor(this,R.color.lime))
                .setRoundedRectCornerRadius(40f)
                .setIsSolid(false)
                .setIsStroke(true)
                .setStrokeColor(ContextCompat.getColor(this,R.color.lime))
                .setStrokeWidth(10f)
                .create()

        button3.setStyleButtonShapeType(GStyleButtonShapeType.RECT_SHAPE)
                .GStyle_SetText("CANCEL")
                .GStyle_setTextColor(ContextCompat.getColor(this,R.color.crimson))
                .setRoundedRectCornerRadius(40f)
                .setIsSolid(false)
                .setIsStroke(true)
                .setStrokeColor(ContextCompat.getColor(this,R.color.crimson))
                .setStrokeWidth(10f)
                .create()

        button4.setStyleButtonShapeType(GStyleButtonShapeType.OVAL_SHAPE)
                .GStyle_SetText("LOGIN")
                .GStyle_setTextColor(ContextCompat.getColor(this,R.color.white))
                .setRoundedRectCornerRadius(40f)
                .setIsSolid(true)
                .setIsSolidColorGradient(true)
                .setSolidColorGradient(
                        ContextCompat.getColor(this,R.color.palevioletred),
                        ContextCompat.getColor(this,R.color.orchid),
                        ContextCompat.getColor(this,R.color.mediumvioletred)
                )
                .create()

        button1.setOnClickListener {
            Toast.makeText(this,"我被点击啦!",Toast.LENGTH_SHORT).show()
        }
    }
}