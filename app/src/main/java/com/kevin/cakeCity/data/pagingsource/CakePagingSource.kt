package com.kevin.cakeCity.data.pagingsource

import androidx.paging.PagingSource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.kevin.cakeCity.data.local.entities.Cake
import com.kevin.cakeCity.other.Constants.CAKE_COLLECTION
import kotlinx.coroutines.tasks.await

class CakePagingSource(
    private val db: FirebaseFirestore,
    //  private val uid: String
) : PagingSource<QuerySnapshot, Cake>() {
    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, Cake> {
        return try {
            val currentPage = params.key ?: db.collection(CAKE_COLLECTION)
                .limit(10)
                .get()
                .await()

            val lastDocumentSnapShot = currentPage.documents[currentPage.size() - 1]
            val nextPage = db.collection(CAKE_COLLECTION).limit(10).startAfter(lastDocumentSnapShot)
                .get()
                .await()

            LoadResult.Page(
                data = currentPage.toObjects(Cake::class.java),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}