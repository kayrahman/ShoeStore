package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viemodels.ShoeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    private lateinit var binding : FragmentShoeDetailBinding
    private lateinit var viewModel:ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_detail,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this.activity!!).get(ShoeViewModel::class.java)

        binding.shoeViewModel = viewModel
        binding.lifecycleOwner = this

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.isDone.observe(viewLifecycleOwner,
            Observer {isTaskDone ->
            if(isTaskDone){
                Timber.i("ShoeList: Done")
                findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
                viewModel.newTask()

            }
        })



    }
}