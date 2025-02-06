package com.example.mycomposeapp.data.repo

import com.example.mycomposeapp.domain.model.FakeProductsItem
import com.example.mycomposeapp.data.network.RemoteDataSource
import com.example.mycomposeapp.domain.RepositoryInterface
import io.ktor.client.call.body

class RepositoryImpl(private val apiService: RemoteDataSource) : RepositoryInterface {


    override suspend fun getFakeProducts(): Result<List<FakeProductsItem>> =
        runCatching {
            apiService.getFakeProducts().body<List<FakeProductsItem>>()
        }

   /* override suspend fun getDrinks(): Result<List<Drinks>> =
        runCatching {
            apiService.getDrinks().body<Drink>().drinks
        }*/
}