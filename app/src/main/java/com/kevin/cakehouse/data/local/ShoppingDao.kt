package com.kevin.cakehouse.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kevin.cakehouse.data.local.entities.ShoppingItem


@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItemDatabase: ShoppingItem)

    @Delete
    suspend fun deleteShoppingItem(shoppingItem:ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price *size)FROM shopping_items")
    fun observeTotalPrice(): LiveData<Float>
}