package com.ugurinci.appcentnewsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ugurinci.appcentnewsapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
}