package com.kevin.cakehouse.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.kevin.cakehouse.data.local.ShoppingDao
import com.kevin.cakehouse.data.local.entities.ShoppingItem
import com.kevin.cakehouse.other.Event
import com.kevin.cakehouse.other.Resource
import com.kevin.cakehouse.repositories.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.sql.DataSource

@HiltViewModel
class ShoppingViewModel @Inject constructor(
        private val repository: ShoppingRepository,
        private val shoppingDao: ShoppingDao
) : ViewModel() {

       private var shoppingItems : Flow<PagingData<ShoppingItem>> ? = null

    fun shoppingItem() :  Flow<PagingData<ShoppingItem>> {
       val result : Flow<PagingData<ShoppingItem>> = repository.shoppingItems()
        return result
    }

    private val _insertShoppingItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<ShoppingItem>>> = _insertShoppingItemStatus

    val totalPrice = repository.observeTotalPrice()
    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }
    fun insertShoppingItem(name: String, size: String, price: String) {
        if (name.isEmpty()){
            _insertShoppingItemStatus.postValue(Event(Resource.error("The fields must not be empty", null)))
            return
        }
        val shoppingItem = ShoppingItem(name, size.toInt(), price.toFloat())
        insertShoppingItemIntoDb(shoppingItem)
        _insertShoppingItemStatus.postValue(Event(Resource.success(shoppingItem)))
    }
}
















