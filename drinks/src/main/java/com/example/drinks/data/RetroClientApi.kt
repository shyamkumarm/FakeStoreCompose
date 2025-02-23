package com.example.drinks.data

import com.example.drinks.domain.models.LiquorProduct
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetroClientApi {

    @GET("api/json/v1/1/filter.php?c=Ordinary_Drink")
    suspend fun getProducts(): LiquorProduct


    companion object {
        private const val BASE_URL = "https://www.thecocktaildb.com/"

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val retroImpl: RetroClientApi = getRetrofit().create(RetroClientApi::class.java)
    }
}
