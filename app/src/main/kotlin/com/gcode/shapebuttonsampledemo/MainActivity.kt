package com.gcode.shapebuttonsampledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gcode.gstylebuttonsampledemo.R
import com.gcode.gstylebuttonsampledemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }
}