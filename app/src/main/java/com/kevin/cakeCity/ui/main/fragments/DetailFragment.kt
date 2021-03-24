package com.kevin.cakeCity.ui.main.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kevin.cakeCity.R
import com.kevin.cakeCity.databinding.FragmentDetailBinding
import com.kevin.cakeCity.other.Status
import com.kevin.cakeCity.ui.main.viewmodels.ShoppingViewModel
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

       binding.picker.minValue = 1
       binding.picker.maxValue = 10
        binding.picker.setOnValueChangedListener{ picker, oldVal, newVal ->
            val valuePicker1: Int = binding.picker.value
            val text = "Changed from $oldVal to $newVal"


        }
        val vals = arrayOf<String>("egg", "eggless")
        binding.egg.displayedValues = vals
        binding.btnAddShoppingItem.setOnClickListener {
            viewModel.insertShoppingItem(
                binding.etShoppingItemName.text.toString(),
                binding.picker.value.toString(),
                binding.imageView7.text.toString()
            )

        }
    }
    private fun subscribeToObservers(){
        viewModel.insertShoppingItemStatus.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.ERROR -> {
                        Snackbar.make(
                            binding.root,
                            result.message ?: "An unknown error occurred",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    Status.SUCCESS -> {
                        Snackbar.make(
                            binding.root,
                            result.message ?: "Added Shopping Item",
                            Snackbar.LENGTH_LONG
                        ).show()
                        findNavController().popBackStack()
                    }
                    Status.LOADING -> {
                        /*NO OP*/
                    }
                }
            }
        })
    }
}