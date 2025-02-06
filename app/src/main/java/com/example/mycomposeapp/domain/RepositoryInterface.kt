package com.example.mycomposeapp.domain

import com.example.mycomposeapp.domain.model.FakeProductsItem

interface RepositoryInterface {
   suspend fun getFakeProducts(): Result<List<FakeProductsItem>>
   //suspend fun getDrinks(): Result<List<Drinks>>
}