package com.kevin.cakeCity

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.kevin.cakeCity.data.local.entities.Cake
import com.kevin.cakeCity.other.Constants.CAKE_COLLECTION
import kotlinx.coroutines.tasks.await

class CakePagingSource(
    private val db: FirebaseFirestore,
  //  private val uid: String
) :PagingSource<QuerySnapshot, Cake>() {
    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, Cake> {
        return try {
            val currentPage = params.key ?: db.collection(CAKE_COLLECTION)
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .await()

            val lastDocumentSnapShot = currentPage.documents[currentPage.size() -1]
            val nextPage = db.collection(CAKE_COLLECTION).limit(10).startAfter(lastDocumentSnapShot)
                    .orderBy("date", Query.Direction.DESCENDING)
                    .startAfter(lastDocumentSnapShot)
                .get()
                .await()

            LoadResult.Page(
              currentPage.toObjects(Cake::class.java).onEach { cake ->
                  val cakes = db.collection(CAKE_COLLECTION).document().get().await().toObject(Cake::class.java)!!
                  cake.title = cakes.title
              },
                        null,
                    nextPage
            )

        } catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<QuerySnapshot, Cake>): QuerySnapshot? {
        TODO("Not yet implemented")
    }

}
















