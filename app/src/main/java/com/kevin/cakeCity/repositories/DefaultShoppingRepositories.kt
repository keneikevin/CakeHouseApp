package com.kevin.cakeCity.repositories

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.kevin.cakeCity.data.local.ShoppingDao
import com.kevin.cakeCity.data.local.entities.Cake
import com.kevin.cakeCity.data.local.entities.ShoppingItem
import com.kevin.cakeCity.other.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao
) : ShoppingRepository {
    private val fireStore = FirebaseFirestore.getInstance()
    private val cakeCollection = fireStore.collection(Constants.CAKE_COLLECTION)

    override suspend fun getAllCakes(): List<Cake>{
        return try {
            cakeCollection.get().await().toObjects(Cake::class.java)
        } catch (e: Exception){
            emptyList()}
    }

    override fun observePrice(): LiveData<Float> {
        return shoppingDao.observePrice()
    }

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






























