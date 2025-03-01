package com.example.drinks.utils

sealed class NetworkError {
    data object NetworkUnavailable : NetworkError() // No internet connection
    data class ServerError(val code: Int) : NetworkError() // HTTP error (e.g., 404, 500)
    data class UnknownError(val message: String) : NetworkError() // Other errors
    data object TimeoutError : NetworkError() // Request timeout

    fun NetworkError.toUserMessage(): String {
        return when (this) {
            is NetworkError.NetworkUnavailable -> "No internet connection. Please check your network settings."
            is NetworkError.ServerError -> "Server error: ${this.code}. Please try again later."
            is NetworkError.TimeoutError -> "Request timed out. Please check your connection."
            is NetworkError.UnknownError -> "An unexpected error occurred: ${this.message}"
        }
    }
}