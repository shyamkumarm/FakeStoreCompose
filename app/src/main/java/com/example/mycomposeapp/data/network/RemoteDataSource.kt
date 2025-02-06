package com.example.mycomposeapp.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteDataSource(private val client: HttpClient) {
    companion object {
        private const val BASE_URL = "https://fakestoreapi.com"
        private const val PRODUCTS = "$BASE_URL/products"

    }

    suspend fun getFakeProducts() = client.get(PRODUCTS)
   // suspend fun getDrinks() = client.get("https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink")

}