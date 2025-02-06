package com.example.mycomposeapp.di_modules

import com.example.mycomposeapp.domain.ProductUseCase
import org.koin.dsl.module


private val domainModule = module {
    single { ProductUseCase(get()) }

}

val domainModules = listOf(domainModule)