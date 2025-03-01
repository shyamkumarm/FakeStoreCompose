package com.example.drinks.domain

import com.example.drinks.presentation.ProductState
import kotlinx.coroutines.flow.flow

class DrinksUseCase(private val repoImpl: RepoInterface) {

    suspend fun getLiquorProduct() = flow {
        //emit(ProductState.Loading)
        emit(repoImpl.getDrink())
    }
}