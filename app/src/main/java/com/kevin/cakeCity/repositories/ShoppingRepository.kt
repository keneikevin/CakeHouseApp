package com.kevin.cakeCity.repositories

import androidx.lifecycle.LiveData
import com.kevin.cakeCity.data.local.entities.Cake
import com.kevin.cakeCity.data.local.entities.ShoppingItem

interface ShoppingRepository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>
    suspend fun getAllCakes(): List<Cake>
    fun observePrice(): LiveData<Float>
}