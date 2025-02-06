package com.example.mycomposeapp.domain

import android.util.Log
import com.example.mycomposeapp.presentation.ResultCallback

class ProductUseCase(private val repository: RepositoryInterface) {

   suspend fun getFakeProductsList(): ResultCallback {
     return repository.getFakeProducts().fold(
         onSuccess = { products ->
            Log.d("shyam", "Product Use case onSuccess called")
            ResultCallback.Success(products)

         }, onFailure = { exception ->
             ResultCallback.Error(exception)
         })
   }
}