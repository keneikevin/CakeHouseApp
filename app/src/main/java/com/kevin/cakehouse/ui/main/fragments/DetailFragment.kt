package com.kevin.cakehouse.ui.main.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kevin.cakehouse.R
import com.kevin.cakehouse.databinding.FragmentDetailBinding
import com.kevin.cakehouse.other.Status
import com.kevin.cakehouse.ui.main.viewmodels.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment:Fragment(R.layout.fragment_detail) {
    private lateinit var binding: FragmentDetailBinding
    lateinit var viewModel: ShoppingViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        binding = FragmentDetailBinding.bind(view)
        subscribeToObservers()
        binding.btnAddShoppingItem.setOnClickListener {
            viewModel.insertShoppingItem(
                    binding.etShoppingItemName.text.toString(),
                    binding.etShoppingItemAmount.text.toString(),
                    binding.etShoppingItemPrice.text.toString()
            )

        }
    }
    private fun subscribeToObservers(){
        viewModel.insertShoppingItemStatus.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { result->
                when(result.status){
                    Status.ERROR ->{
                        Snackbar.make(
                            binding.root,
                            result.message ?: "An unknown error occurred",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    Status.SUCCESS ->{
                        Snackbar.make(
                            binding.root,
                            result.message?:"Added Shopping Item",
                                Snackbar.LENGTH_LONG
                        ).show()
                        findNavController().navigate(
                                DetailFragmentDirections.actionDetailFragmentToShoppingFragment()
                        )
                    }
                    Status.LOADING ->{
                        /*NO OP*/
                    }
                }
            }
        })
    }
}