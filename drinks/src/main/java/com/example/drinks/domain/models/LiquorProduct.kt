package com.example.drinks.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LiquorProduct(
    @SerialName("drinks")
    val drinks: List<Drink>
)