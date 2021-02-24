package com.kevin.cakehouse.repositories

import androidx.lifecycle.LiveData
import com.kevin.cakehouse.data.local.entities.ShoppingItem

interface ShoppingRepository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

}