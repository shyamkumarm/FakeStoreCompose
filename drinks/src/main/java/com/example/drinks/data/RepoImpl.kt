package com.example.drinks.data

import com.example.drinks.domain.RepoInterface
import com.example.drinks.utils.ProductState

class RepoImpl(private val remoteDataSource: RemoteDataSource) : RepoInterface {
    override suspend fun getDrink(): ProductState {

        return when (val result = remoteDataSource.getDrink()) {
            is ProductState.Success -> {
                // When success replace the local database and return the result
                // You could also return the local data for a single source of truth pattern
                //  localDataSource.updateUserInfo(remoteResult.data)
                result
            }
            is ProductState.Error -> {
                // If error fallback to local database
                result
            }
            else -> result
        }
    }
}