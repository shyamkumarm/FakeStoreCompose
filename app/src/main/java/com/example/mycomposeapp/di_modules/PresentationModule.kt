package com.example.mycomposeapp.di_modules

import androidx.lifecycle.SavedStateHandle
import com.example.mycomposeapp.presentation.viewmodel.FakeStoreApiModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val requestViewModel = module {
    factory { SavedStateHandle() }
    viewModel { FakeStoreApiModel(get(),get()) }
}

private val appModule = module {
    single { } // app utils if any

}

val presentationModules = listOf(requestViewModel)