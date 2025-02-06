package com.example.mycomposeapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Rating(
    val count: Int,
    val rate: Double
) : Parcelable