package com.kevin.cakehouse.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.*
import com.kevin.cakehouse.data.local.entities.ShoppingItem
import kotlinx.coroutines.flow.Flow


@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItemDatabase: ShoppingItem)

    @Delete
    suspend fun deleteShoppingItem(shoppingItem:ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun ShoppingItems():  PagingSource<Int, ShoppingItem>


    @Query("SELECT SUM(price *size)FROM shopping_items")
    fun observeTotalPrice(): LiveData<Float>
}