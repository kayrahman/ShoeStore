package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeOnboardingBinding

class WelcomeOnboardingFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_welcome_onboarding,container,false)
       return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener (
            //go to Instruction onboarding fragment
            Navigation.createNavigateOnClickListener(R.id.action_welcomeOnboardingFragment_to_instructionsOnboardingFragment)
        )
    }


}