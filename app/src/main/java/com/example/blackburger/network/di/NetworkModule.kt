package com.example.blackburger.network.di

import com.example.blackburger.network.api.NetworkDataSource
import com.example.blackburger.network.api.NetworkHttp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsNetworkDataSource(
        networkHttp: NetworkHttp
    ): NetworkDataSource

    companion object {
        @Singleton
        @Provides
        fun providerJson(): Json = Json {
            ignoreUnknownKeys = true
        }
    }
}