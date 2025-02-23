package com.example.drinks.domain

import com.example.drinks.domain.models.LiquorProduct
import kotlinx.coroutines.flow.flow

class DrinksUseCase(private val repoImpl: RepoInterface) {

    suspend fun getLiquorProduct() = flow<LiquorProduct> {
        emit(repoImpl.getDrink())
    }
}