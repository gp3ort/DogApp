package com.ort.dogadoption.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ort.dogadoption.R

class MainActivity : AppCompatActivity() {

    private lateinit var textTest: TextView
    private lateinit var bottomNavView: BottomNavigationView

    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavigationId) as NavHostFragment
        bottomNavView = findViewById(R.id.bottomMenuMain)

        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(bottomNavView, navController)


        val email = intent.getStringExtra("user")

//        textTest = findViewById(R.id.textTestId)
//        textTest.text = email

    }
}