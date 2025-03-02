package com.example.drinks.utils

sealed class NetworkError {
    data object NetworkUnavailable : NetworkError() // No internet connection
    data class ServerError(val code: Int) : NetworkError() // HTTP error (e.g., 404, 500)
    data class UnknownError(val message: String) : NetworkError() // Other errors
    data object TimeoutError : NetworkError() // Request timeout

    fun NetworkError.toUserMessage(): String {
        return when (this) {
            is NetworkUnavailable -> "No internet connection. Please check your network settings."
            is ServerError -> "Server error: ${this.code}. Please try again later."
            is TimeoutError -> "Request timed out. Please check your connection."
            is UnknownError -> "An unexpected error occurred: ${this.message}"
        }
    }
}