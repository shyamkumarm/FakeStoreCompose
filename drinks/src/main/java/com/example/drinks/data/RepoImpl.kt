package com.example.drinks.data

import com.example.drinks.domain.RepoInterface
import com.example.drinks.utils.ProductState

class RepoImpl(private val remoteDataSource: RemoteDataSource) : RepoInterface {
    override suspend fun getDrink(): ProductState {
       return remoteDataSource.getDrink()
    }
}