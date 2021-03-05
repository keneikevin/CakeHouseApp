package com.kevin.cakehouse.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevin.cakehouse.data.local.entities.ShoppingItem
import com.kevin.cakehouse.other.Event
import com.kevin.cakehouse.other.Resource
import com.kevin.cakehouse.repositories.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ShoppingViewModel @Inject constructor(
        private val repository: ShoppingRepository
) : ViewModel() {
    val shoppingItems = repository.observeAllShoppingItems()
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
















