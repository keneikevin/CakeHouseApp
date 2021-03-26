package com.kevin.cakeCity.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.kevin.cakeCity.BuildConfig
import com.kevin.cakeCity.R
import com.kevin.cakeCity.databinding.ActivityMainBinding
import com.kevin.cakeCity.ui.main.fragments.HomeFragment
import com.kevin.cakeCity.ui.main.fragments.ShoppingFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)
                as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
        navHostFragment.findNavController()
            .addOnDestinationChangedListener{_, destination, _ ->
                when(destination.id){
                    R.id.homeFragment, R.id.shoppingFragment, R.id.profileFragment ->
                        binding.bottomNavigationView.visibility = View.VISIBLE
                    else -> binding.bottomNavigationView.visibility = View.GONE
                }

            }


    }
}