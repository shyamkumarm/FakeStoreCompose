package com.example.mycomposeapp.di_modules

import android.util.Log
import com.example.mycomposeapp.data.network.RemoteDataSource
import com.example.mycomposeapp.domain.RepositoryInterface
import com.example.mycomposeapp.data.repo.RepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

private const val NETWORK_TIME_OUT = 10_000L

val httpClientAndroid = HttpClient(Android) {

    install(ContentNegotiation) {
        json( Json {
            prettyPrint = true
            isLenient = true
            useAlternativeNames = true
            ignoreUnknownKeys = true
            encodeDefaults = false
        })

    }
    install(HttpTimeout) {
        requestTimeoutMillis = NETWORK_TIME_OUT
        connectTimeoutMillis = NETWORK_TIME_OUT
        socketTimeoutMillis = NETWORK_TIME_OUT
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
        filter { request ->
            request.url.host.contains("ktor.io")
        }
    }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application)
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }


    val networkModule = module {
        single { RemoteDataSource(httpClientAndroid) }
        single { getFakeRepository(get<RemoteDataSource>()) }
    }

    fun getFakeRepository(remoteDataSource: RemoteDataSource): RepositoryInterface
    = RepositoryImpl(remoteDataSource/* Add database source here if any */)

    val dataModules = listOf(networkModule /* Add database modules here if any */)