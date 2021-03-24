package com.kevin.cakeCity.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.kevin.cakeCity.data.local.entities.Cake
import com.kevin.cakeCity.other.Constants.CAKE_COLLECTION
import kotlinx.coroutines.tasks.await

class CakeDatabase {
    private val firestore = FirebaseFirestore.getInstance()
    private val cakeCollection = firestore.collection(CAKE_COLLECTION)

    suspend fun getAllCakes():List<Cake>{
        return try {
            cakeCollection.get().await().toObjects(Cake::class.java)
        } catch (e:Exception){
            emptyList()
        }
    }
}