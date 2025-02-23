package com.example.drinks.presentation

import com.example.drinks.domain.models.LiquorProduct

sealed class DataCallback {
    data class Success(val liquorProduct: LiquorProduct) : DataCallback()
    data object Loading : DataCallback()
    data class Error(val exception: Throwable) : DataCallback()
}