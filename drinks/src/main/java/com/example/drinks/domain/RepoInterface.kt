package com.example.drinks.domain

import com.example.drinks.utils.ProductState

interface RepoInterface {
    suspend fun getDrink(): ProductState
}