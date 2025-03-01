package com.example.drinks.data

import com.example.drinks.utils.NetworkError
import com.example.drinks.utils.NetworkError.NetworkUnavailable.toUserMessage
import com.example.drinks.utils.ProductState
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException

class RemoteDataSource(private val retrofitClient: RetroClientApi) {
    suspend fun getDrink(): ProductState {
        return try {
            val result = retrofitClient.getProducts()
            ProductState.Success(result)
        } catch (e: Exception) {
            ProductState.Error(mapExceptionToNetworkError(e).toUserMessage())
        }
    }

    private fun mapExceptionToNetworkError(e: Exception): NetworkError {
        return when (e) {
            is IOException -> NetworkError.NetworkUnavailable
            is HttpException -> NetworkError.ServerError(e.code())
            is SocketTimeoutException -> NetworkError.TimeoutError
            else -> NetworkError.UnknownError(e.message ?: "Unknown error")
        }
    }

}