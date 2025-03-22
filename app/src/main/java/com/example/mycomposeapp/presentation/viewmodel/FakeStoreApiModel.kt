package com.example.mycomposeapp.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposeapp.domain.ProductUseCase
import com.example.mycomposeapp.domain.model.FakeProductsItem
import com.example.mycomposeapp.presentation.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
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

    private val _items = MutableStateFlow(ResultState.Loading)
    val fakeProducts = _items.asStateFlow()
    val savedStateHandleProducts =
    savedStateHandle.getStateFlow<ResultState<List<FakeProductsItem>>>("products", ResultState.Loading)


    val flowItems = flow {
        //delay(4_000)
        emit(productUseCase.getFakeProductsList())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3_000), ResultState.Loading)


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