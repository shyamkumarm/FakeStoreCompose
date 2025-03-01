package com.example.drinks.domain

import com.example.drinks.presentation.ProductState

interface RepoInterface {
    suspend fun getDrink(): ProductState
}