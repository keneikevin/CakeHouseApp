package com.kevin.cakehouse.ui.main.fragments
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.cakehouse.R
import com.kevin.cakehouse.adapters.shoppingAdapter
import com.kevin.cakehouse.databinding.FragmentShoppingBinding
import com.kevin.cakehouse.ui.main.viewmodels.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingFragment:Fragment(R.layout.fragment_shopping) {
    private lateinit var binding: FragmentShoppingBinding
    lateinit var viewModel: ShoppingViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)


        binding = FragmentShoppingBinding.bind(view)
        binding.fabAddShoppingItem.setOnClickListener {

            findNavController().navigate(
                ShoppingFragmentDirections.actionShoppingFragmentToDetailFragment()
            )
        }


    }
 private fun setUpRecyclerView() = binding.rvShoppingItems.apply {
     adapter = shoppingAdapter
     layoutManager = LinearLayoutManager(requireContext())
     itemAnimator = null
 }
}