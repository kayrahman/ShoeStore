package com.udacity.shoestore.fragments

import android.graphics.drawable.GradientDrawable
import android.icu.lang.UCharacter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeListBindingImpl
import com.udacity.shoestore.viemodels.ShoeViewModel
import timber.log.Timber


class ShoeListFragment : Fragment() {

    private lateinit var binding : FragmentShoeListBinding
    private lateinit var viewModel : ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_list,container,false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)
        binding.shoeViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            if(!it){
                findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
            }
        })


        viewModel.shoeList.observe(viewLifecycleOwner, Observer {shoe_list->
            shoe_list.forEach {
                val shoeBinding : ItemShoeListBinding = DataBindingUtil.inflate(layoutInflater,R.layout.item_shoe_list,binding.llShoeList,false)
                shoeBinding.model = it
                binding.llShoeList.addView(shoeBinding.root)
                Timber.i("shoeName${it}")
            }

        })

        binding.btnAdd.setOnClickListener {
           findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)

        }

    }


}