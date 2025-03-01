package com.example.drinks.domain

import kotlinx.coroutines.flow.flow

class DrinksUseCase(private val repoImpl: RepoInterface) {

    suspend fun getLiquorProduct() = flow {
        emit(repoImpl.getDrink())
    }
}