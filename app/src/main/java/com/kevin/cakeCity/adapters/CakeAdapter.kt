package com.kevin.cakeCity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kevin.cakeCity.data.local.entities.Cake
import com.kevin.cakeCity.databinding.ItemCakeBinding
import com.kevin.cakeCity.databinding.ItemShoppingBinding

class CakeAdapter(): PagingDataAdapter<Cake, CakeAdapter.CakeViewHolder>(Companion) {
    class CakeViewHolder(binding: ItemShoppingBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvName= binding.tvName
        val tvPrice= binding.tvPrice
    }
    companion object : DiffUtil.ItemCallback<Cake>() {
        override fun areItemsTheSame(oldItem: Cake, newItem: Cake): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cake, newItem: Cake): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: CakeViewHolder, position: Int) {
        val cake = getItem(position) ?: return
    holder.apply {
        tvName.text = cake.title
        tvPrice.text = cake.price
    }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeViewHolder {
        return CakeAdapter.CakeViewHolder(
            ItemShoppingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

