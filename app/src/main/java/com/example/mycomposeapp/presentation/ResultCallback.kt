package com.example.mycomposeapp.presentation

import android.os.Parcelable
import com.example.mycomposeapp.domain.model.FakeProductsItem
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class ResultCallback : Parcelable {
    data class Success(val data: List<FakeProductsItem>) : ResultCallback()
    data object Loading : ResultCallback()
    data class Error(val exception: Throwable) : ResultCallback()
}