package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.viemodels.ShoeViewModel
import kotlinx.android.synthetic.main.activity_main.*


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

        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        binding.btnDetailScreen.setOnClickListener (
            //go to detail screen
            Navigation.createNavigateOnClickListener(R.id.action_shoeListFragment_to_shoeDetailFragment,null)
        )


    }







}