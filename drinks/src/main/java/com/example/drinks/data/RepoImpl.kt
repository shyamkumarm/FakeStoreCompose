package com.example.drinks.data

import com.example.drinks.domain.RepoInterface
import com.example.drinks.domain.models.LiquorProduct

class RepoImpl(private val remoteDataSource: RemoteDataSource) : RepoInterface {
    override suspend fun getDrink(): LiquorProduct {
       return remoteDataSource.getDrink()
    }
}