package com.kevin.cakehouse.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kevin.cakehouse.data.local.entities.ShoppingItem
import com.kevin.cakehouse.databinding.ItemShoppingBinding
import javax.inject.Inject

class shoppingAdapter @Inject constructor()
    :PagingDataAdapter<ShoppingItem, shoppingAdapter.ShoppingViewHolder>(Companion) {
    class ShoppingViewHolder( val binding: ItemShoppingBinding): RecyclerView.ViewHolder(binding.root){
        val tvName: TextView = binding.tvName
        val tvSize: TextView = binding.tvShoppingItemSize
        val tvPrice: TextView = binding.tvShoppingItemPrice

    }
    companion object : DiffUtil.ItemCallback<ShoppingItem>(){
        override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val shoppingItem = getItem(position)?:return
        holder.apply {
        tvName.text = shoppingItem.name
        tvSize.text = shoppingItem.size.toString()
        tvPrice.text = shoppingItem.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
            return ShoppingViewHolder(
                binding = ItemShoppingBinding.inflate(LayoutInflater.from(parent.context),
                parent,false
                    )
            )
    }

}


























