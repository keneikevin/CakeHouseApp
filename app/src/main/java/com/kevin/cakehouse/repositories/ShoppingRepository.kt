package com.kevin.cakehouse.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kevin.cakehouse.data.local.entities.ShoppingItem
import kotlinx.coroutines.flow.Flow

interface ShoppingRepository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun shoppingItems():Flow<PagingData<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>
}