package com.ort.dogadoption.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.ort.dogadoption.R

class MainActivity : AppCompatActivity() {

    private lateinit var textTest: TextView
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var drawer: DrawerLayout
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavigationId) as NavHostFragment
        bottomNavView = findViewById(R.id.bottomMenuMain)
        drawer = findViewById(R.id.mainContainer)
        navigationView = findViewById(R.id.navView)

        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(bottomNavView, navController)
        NavigationUI.setupWithNavController(navigationView, navController)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbarTop)
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val email = intent.getStringExtra("user")

        navController.addOnDestinationChangedListener { _, _, _ ->
            supportActionBar?.setHomeAsUpIndicator(R.drawable.hamburger)
        }


    }
}