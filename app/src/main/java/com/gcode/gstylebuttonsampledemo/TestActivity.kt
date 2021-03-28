package com.gcode.gstylebuttonsampledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat
import com.gcode.gstylebutton.StyleButton

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val styleButton  = findViewById<StyleButton>(R.id.styleButton)

        val button = findViewById<Button>(R.id.button)

        button.text = "12222222222"

        button.setOnClickListener {
            styleButton.setIsStroke(true)
                    .setStrokeColor(ContextCompat.getColor(this,R.color.purple_500))
                    .setStrokeWidth(5f)
                    .setText(resources.getString(R.string.app_name))
                    .create()
        }

        Log.i("TestActivity",styleButton.text.toString())
    }
}