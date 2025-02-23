package com.example.drinks.domain

import com.example.drinks.domain.models.LiquorProduct

interface RepoInterface {
    suspend fun getDrink(): LiquorProduct
}