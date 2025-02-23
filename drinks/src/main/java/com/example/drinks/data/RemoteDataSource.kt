package com.example.drinks.data

import com.example.drinks.domain.models.LiquorProduct

class RemoteDataSource(private val retrofitClient: RetroClientApi) {
     suspend fun getDrink():LiquorProduct {
          return retrofitClient.getProducts()
     }
}