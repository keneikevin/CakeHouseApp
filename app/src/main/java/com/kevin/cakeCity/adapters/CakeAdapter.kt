package com.kevin.cakeCity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kevin.cakeCity.data.local.entities.Cake
import com.kevin.cakeCity.databinding.ItemCakeBinding

class CakeAdapter : PagingDataAdapter<Cake, CakeAdapter.CakeViewHolder>(Companion) {
    class CakeViewHolder(val binding: ItemCakeBinding): RecyclerView.ViewHolder(binding.root){
        val tvName:TextView  = binding.titleView


    }
    companion object : DiffUtil.ItemCallback<Cake>() {
        override fun areItemsTheSame(oldItem: Cake, newItem: Cake): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }

        override fun areContentsTheSame(oldItem: Cake, newItem: Cake): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: CakeViewHolder, position: Int) {
        val cake = getItem(position)?:return
        holder.apply {
            tvName.text = cake.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeViewHolder {
        return CakeViewHolder(
            binding = ItemCakeBinding.inflate(LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }
}




