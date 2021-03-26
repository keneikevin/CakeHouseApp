package com.kevin.cakeCity.repositories

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.kevin.cakeCity.data.local.ShoppingDao
import com.kevin.cakeCity.data.local.entities.Cake
import com.kevin.cakeCity.data.local.entities.ShoppingItem
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

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

}






























