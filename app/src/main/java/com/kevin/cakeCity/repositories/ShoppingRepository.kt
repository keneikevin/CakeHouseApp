package com.kevin.cakeCity.repositories

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.DocumentId
import com.kevin.cakeCity.data.local.entities.Cake
import com.kevin.cakeCity.data.local.entities.ShoppingItem

interface ShoppingRepository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

}