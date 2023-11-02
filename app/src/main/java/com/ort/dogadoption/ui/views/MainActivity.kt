package com.ort.dogadoption.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.ort.dogadoption.PetListAdapter
import com.ort.dogadoption.R
import com.ort.dogadoption.ui.viewmodels.SharedInfoViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    private lateinit var textTest: TextView

    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var drawer: DrawerLayout
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var sharedInfoViewModel: SharedInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sharedInfoViewModel = ViewModelProvider(this)[SharedInfoViewModel::class.java]

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
        sharedInfoViewModel.setUsername(email!!)

        val headerUsernameTextView: TextView = navigationView.getHeaderView(0).findViewById(R.id.topBarUsernameId)
        val headerUsernamePhoto: ImageView = navigationView.getHeaderView(0).findViewById(R.id.topBarUsernamePhoto)

        Glide.with(this)
            .load("https://freesvg.org/img/Male-Avatar.png")
            .circleCrop().into(headerUsernamePhoto)

        headerUsernameTextView.text = email
        navController.addOnDestinationChangedListener { _, _, _ ->
            supportActionBar?.setHomeAsUpIndicator(R.drawable.hamburger)
        }

        sharedInfoViewModel.isDarkMode.observe(this, Observer { isDarkMode ->
            if (isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        })

        sharedInfoViewModel.userNamePhoto.observe(this, Observer { userNamePhoto ->
            Glide.with(this)
                .load(userNamePhoto)
                .circleCrop().into(headerUsernamePhoto)
        })


    }
}