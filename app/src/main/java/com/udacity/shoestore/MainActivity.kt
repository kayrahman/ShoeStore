package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.viemodels.ShoeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel: ShoeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

       // navController = this.findNavController(R.id.fm_container)

        val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.fm_container) as NavHostFragment
         navController = navHostFragment.navController

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
                    binding.toolbar.setOnMenuItemClickListener {
                        when(it.itemId){
                            R.id.loginFragment ->
                            {
                                viewModel.logout()
                                true
                            }else-> false
                        }
                    }


                }
                else -> binding.toolbar.menu.clear()
            }

        }


    }



}
