package com.kevin.cakeCity.ui.main.viewmodels

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.google.firebase.firestore.FirebaseFirestore
import com.kevin.cakeCity.CakePagingSource
import com.kevin.cakeCity.data.local.entities.Cake
import com.kevin.cakeCity.data.local.entities.ShoppingItem
import com.kevin.cakeCity.other.Constants.PAGE_SIZE
import com.kevin.cakeCity.other.Event
import com.kevin.cakeCity.other.Resource
import com.kevin.cakeCity.repositories.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ShoppingViewModel @Inject constructor(
        private val repository: ShoppingRepository
) : ViewModel() {
    val shoppingItems = repository.observeAllShoppingItems()
    private val _insertShoppingItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<ShoppingItem>>> = _insertShoppingItemStatus


    val flow = Pager(PagingConfig(20)) {
        CakePagingSource(FirebaseFirestore.getInstance())
    }.flow.cachedIn(viewModelScope)


    val price = repository.observePrice()
    val totalPrice = repository.observeTotalPrice()
    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }
    fun insertShoppingItem(name: String, size: String, price: String) {
        if (name.isEmpty() || size.isEmpty() || price.isEmpty()){
            _insertShoppingItemStatus.postValue(Event(Resource.error("The fields must not be empty", null)))
            return
        }
        val shoppingItem = ShoppingItem(name, size.toInt(), price.toFloat())
        insertShoppingItemIntoDb(shoppingItem)
        _insertShoppingItemStatus.postValue(Event(Resource.success(shoppingItem)))
    }
}
















