package com.kevin.cakeCity.ui.main.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kevin.cakeCity.R
import com.kevin.cakeCity.databinding.FragmentProfileBinding
import com.kevin.cakeCity.ui.main.viewmodels.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment :Fragment(R.layout.fragment_profile){
    private lateinit var binding: FragmentProfileBinding
    lateinit var viewModel : ShoppingViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        binding = FragmentProfileBinding.bind(view)
    }
}