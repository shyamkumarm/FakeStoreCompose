package com.example.mycomposeapp

import android.app.Application
import com.example.mycomposeapp.di_modules.dataModules
import com.example.mycomposeapp.di_modules.domainModules
import com.example.mycomposeapp.di_modules.presentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MainApplication)
            modules(presentationModules+ domainModules + dataModules)
        }
    }
}