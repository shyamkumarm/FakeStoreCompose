package com.example.drinks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinks.data.RemoteDataSource
import com.example.drinks.data.RepoImpl
import com.example.drinks.data.RetroClientApi
import com.example.drinks.domain.DrinksUseCase
import com.example.drinks.utils.ProductState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MyDrinkViewmodel() : ViewModel() {

    private val repoInterface by lazy {
        DrinksUseCase(RepoImpl(RemoteDataSource(RetroClientApi.retroImpl)))
    }

    private val _productMutableState = MutableStateFlow<ProductState>(ProductState.Loading)
    val productState = _productMutableState.asStateFlow()

    init {
        getLiquorProducts()
    }

    public fun getLiquorProducts() {
        _productMutableState.tryEmit(ProductState.Loading)
        viewModelScope.launch {
            repoInterface.getLiquorProduct().flowOn(Dispatchers.IO).collect {
                _productMutableState.emit(it)
            }
        }
    }
}