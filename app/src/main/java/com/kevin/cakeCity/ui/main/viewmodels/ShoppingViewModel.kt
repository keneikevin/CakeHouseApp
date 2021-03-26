package com.kevin.cakeCity.ui.main.viewmodels

import android.widget.NumberPicker
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.google.firebase.firestore.FirebaseFirestore
import com.kevin.cakeCity.data.local.entities.Cake
import com.kevin.cakeCity.data.local.entities.ShoppingItem
import com.kevin.cakeCity.data.pagingsource.CakePagingSource
import com.kevin.cakeCity.other.Event
import com.kevin.cakeCity.other.Resource
import com.kevin.cakeCity.repositories.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ShoppingViewModel @Inject constructor(
        private val repository: ShoppingRepository
) : ViewModel() {
    private val _mediaItems = MutableLiveData<Resource<List<Cake>>>()
    val mediaItems: LiveData<Resource<List<Cake>>> = _mediaItems

    private val _cake = MutableLiveData<Event<Resource<Cake>>>()
    val cake: LiveData<Event<Resource<Cake>>> = _cake
    val shoppingItems = repository.observeAllShoppingItems()
    private val _insertShoppingItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<ShoppingItem>>> = _insertShoppingItemStatus

    // The current cost
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score
    init{
        _score.value = 1500
    }
    fun calculate(sz: String){
        var rol = sz.toInt()
       var s= rol * 1500
        _score.value = s
    }


        val flow = Pager(PagingConfig(20)){
            CakePagingSource(FirebaseFirestore.getInstance())
        }.flow.cachedIn(viewModelScope)

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
















