package com.kevin.cakehouse.ui.main.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kevin.cakehouse.R
import com.kevin.cakehouse.databinding.FragmentDetailBinding
import com.kevin.cakehouse.ui.main.viewmodels.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment:Fragment(R.layout.fragment_detail) {
    private lateinit var binding:FragmentDetailBinding
    lateinit var viewModel: ShoppingViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        binding.btnAddShoppingItem.setOnClickListener{
            findNavController().navigate(
           DetailFragmentDirections.actionDetailFragmentToShoppingFragment()
            )
        }
    }
}