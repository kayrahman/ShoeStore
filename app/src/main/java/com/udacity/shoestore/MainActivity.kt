package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_main)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = this.findNavController(R.id.fm_container)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.loginFragment,
                R.id.welcomeOnboardingFragment,
                R.id.instructionsOnboardingFragment,
                R.id.shoeListFragment
            )
            .build()

        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.shoeListFragment -> {
                    binding.toolbar.inflateMenu(R.menu.main_menu)

                }
                else -> binding.toolbar.menu.clear()
            }

        }


    }



}
