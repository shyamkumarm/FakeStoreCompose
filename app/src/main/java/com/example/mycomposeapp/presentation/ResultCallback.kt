package com.example.mycomposeapp.presentation

import android.os.Parcelable
import com.example.mycomposeapp.domain.model.FakeProductsItem
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
sealed class ResultCallback : Parcelable {
    data class Success(val data: List<FakeProductsItem>) : ResultCallback()
    data object Loading : ResultCallback()
    data class Error(val exception: Throwable) : ResultCallback()
}

@Parcelize
sealed class ResultState<out T> : Parcelable {
    data class Success<T>(val data: @RawValue T) : ResultState<T>()
    data class Error(val exception: Throwable) : ResultState<Nothing>()
    data object Loading : ResultState<Nothing>()
}