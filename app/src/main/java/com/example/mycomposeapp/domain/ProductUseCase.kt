package com.example.mycomposeapp.domain

import android.util.Log
import com.example.mycomposeapp.domain.model.FakeProductsItem
import com.example.mycomposeapp.presentation.ResultState

class ProductUseCase(private val repository: RepositoryInterface) {

   suspend fun getFakeProductsList(): ResultState<List<FakeProductsItem>> {
     return repository.getFakeProducts().fold(
         onSuccess = { products ->
            Log.d("shyam", "Product Use case onSuccess called")
             ResultState.Success(products)

         }, onFailure = { exception ->
             ResultState.Error(exception)
         })
   }
}