package com.example.drinks.data

import com.example.drinks.presentation.ProductState

class RemoteDataSource(private val retrofitClient: RetroClientApi) {
    suspend fun getDrink(): ProductState {
        return this.fetchResource()
    }


    private suspend inline fun fetchResource(): ProductState {
        return try {
            val response = retrofitClient.getProducts()
            ProductState.Success(response)
        } catch (e: Exception) {
            /*  val errorMsg = when (e) {
                 is RedirectResponseException,
                 is ClientRequestException,
                 is ServerResponseException -> e.message ?: "Server error"
                 else -> e.message ?: "Something went wrong"
            }*/
            ProductState.Error(e)
        }
    }
}