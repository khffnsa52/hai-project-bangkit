package com.capstone.hadirai.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.capstone.hadirai.R
import com.capstone.hadirai.databinding.ActivityMainBinding
import com.capstone.hadirai.ui.ViewModelFactory
import com.capstone.hadirai.ui.home.HomeViewModel
import com.capstone.hadirai.ui.welcome.WelcomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getSession().observe(this) { user ->
            if (user.token.isEmpty() && user.token.isBlank()) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }


//            val toolbar: Toolbar = findViewById(R.id.toolbar)
//            setSupportActionBar(toolbar)


            val navView: BottomNavigationView = binding.navView

            val navController = findNavController(R.id.nav_host_fragment)
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_home, R.id.navigation_history, R.id.navigation_setting
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }
    }
}
