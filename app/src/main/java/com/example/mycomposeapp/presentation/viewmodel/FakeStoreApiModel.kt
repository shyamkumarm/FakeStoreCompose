package com.example.mycomposeapp.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposeapp.domain.ProductUseCase
import com.example.mycomposeapp.presentation.ResultCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FakeStoreApiModel(
    private val productUseCase: ProductUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    /*    @SuppressLint("MutableCollectionMutableState")
        val fakeProducts =
            mutableStateOf<List<FakeProductsItem>>(listOf<FakeProductsItem>())*/

    init {
        getFakeProducts()
    }

    private val _items: MutableStateFlow<ResultCallback> = MutableStateFlow(ResultCallback.Loading)
    val fakeProducts: StateFlow<ResultCallback> = _items.asStateFlow()
    val savedStateHandleProducts: StateFlow<ResultCallback> =
        savedStateHandle.getStateFlow("products", ResultCallback.Loading)


    val flowItems = flow<ResultCallback> {
        //delay(4_000)
        emit(productUseCase.getFakeProductsList())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3_000), ResultCallback.Loading)


    private fun getFakeProducts() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                productUseCase.getFakeProductsList()
            }
            //_items.value = ResultCallback.Success(products)
            savedStateHandle["products"] = result

        }

    }

}