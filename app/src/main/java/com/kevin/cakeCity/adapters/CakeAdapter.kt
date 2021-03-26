package com.kevin.cakeCity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideContext
import com.kevin.cakeCity.R
import com.kevin.cakeCity.data.local.entities.Cake
import com.kevin.cakeCity.databinding.*
import javax.inject.Inject

class CakeAdapter @Inject constructor(
):   PagingDataAdapter<Cake, CakeAdapter.CakeViewHolder>(Companion) {
     class CakeViewHolder(val binding: CardBinding, var cake: Cake? = null) : RecyclerView.ViewHolder(binding.root){
         val tvTitle= binding.textName
         val tvPrice= binding.textPrice
         val tvImage = binding.img
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
       val cake = getItem(position) ?:return
        holder.apply {
            tvTitle.text = cake.title
            tvPrice.text = cake.price
            itemView.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detailFragment)
                    )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeViewHolder {
        return CakeViewHolder(
            CardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}







