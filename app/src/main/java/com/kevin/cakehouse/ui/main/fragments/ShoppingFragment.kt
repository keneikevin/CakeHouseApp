package com.kevin.cakehouse.ui.main.fragments
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.cakehouse.R
import com.kevin.cakehouse.adapters.ShoppingAdapter
import com.kevin.cakehouse.databinding.FragmentShoppingBinding
import com.kevin.cakehouse.other.Status
import com.kevin.cakehouse.ui.main.viewmodels.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

@AndroidEntryPoint
class ShoppingFragment :Fragment(R.layout.fragment_shopping) {
    private lateinit var binding: FragmentShoppingBinding
    lateinit var viewModel: ShoppingViewModel
    private lateinit var shoppingAdapter: ShoppingAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        binding = FragmentShoppingBinding.bind(view)
        subscribeToObservers()
        setupRecyclerView()

        binding.fabAddShoppingItem.setOnClickListener {
            findNavController().navigate(
                    ShoppingFragmentDirections.actionShoppingFragmentToDetailFragment()
            )
        }}


    private val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0, LEFT or RIGHT
    ) {
        override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
        ) = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val pos = viewHolder.layoutPosition
            val item = shoppingAdapter.shoppingItems[pos]
            viewModel?.deleteShoppingItem(item)
            Snackbar.make(requireView(), "Successfully deleted item", Snackbar.LENGTH_LONG).apply {
                setAction("Undo") {
                    viewModel?.insertShoppingItemIntoDb(item)
                }
                show()
            }
        }
    }
    private fun subscribeToObservers() {
        viewModel.totalPrice.observe(viewLifecycleOwner, Observer {
            val price = it ?: 0f
            val priceText = "Total Price: $price ksh"
            binding.tvShoppingItemPrice.text = priceText
        })
        viewModel.shoppingItems.observe(viewLifecycleOwner, Observer {
            shoppingAdapter.shoppingItems = it
        })

    }
    private fun setupRecyclerView() {
        binding.rvShoppingItems.apply {
            shoppingAdapter = ShoppingAdapter()
            adapter = shoppingAdapter
            layoutManager = LinearLayoutManager(requireContext())
            ItemTouchHelper(itemTouchCallback).attachToRecyclerView(this)
        }
    }
}
