package com.example.drinks.domain.models


import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Stable
data class LiquorProduct(
    @SerialName("drinks")
    val drinks: List<Drink>
)