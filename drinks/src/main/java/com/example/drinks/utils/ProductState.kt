package com.example.drinks.utils

import com.example.drinks.domain.models.LiquorProduct

sealed class ProductState {
    data class Success(val liquorProduct: LiquorProduct) : ProductState()
    data object Loading : ProductState()
    data class Error(val exception: String) : ProductState()
}