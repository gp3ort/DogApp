package com.ort.dogadoption.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.ort.dogadoption.R

class MainActivity : AppCompatActivity() {

    lateinit var textTest: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val email = intent.getStringExtra("user")

        textTest = findViewById(R.id.textTestId)
        textTest.text = email

    }
}