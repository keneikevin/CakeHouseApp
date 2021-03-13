package com.kevin.cakehouse.repositories

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.kevin.cakehouse.data.local.ShoppingDao
import com.kevin.cakehouse.data.local.entities.ShoppingItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
        private val shoppingDao: ShoppingDao
) : ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override fun shoppingItems():Flow<PagingData<ShoppingItem>> {
      return Pager(
          config = PagingConfig(
              pageSize = NETWORK_PAGE_SIZE,
              enablePlaceholders = false
          ),
          pagingSourceFactory = { shoppingDao.ShoppingItems() }
      ).flow
   }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}

