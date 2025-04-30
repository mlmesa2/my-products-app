package com.mlmesa.myproducts.di

import com.mlmesa.myproducts.BuildConfig
import com.mlmesa.myproducts.data.local.datastore.UserDataDataSource
import com.mlmesa.myproducts.data.remote.AuthInterceptor
import com.mlmesa.myproducts.data.remote.api.ProductsApiDataSource
import com.mlmesa.myproducts.data.remote.api.ProductsRetrofit
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun productsCallFactory(
        authInterceptor: AuthInterceptor
    ): Call.Factory =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .build()


    @Provides
    @Singleton
    fun provideAuthInterceptor(
        userDataDataSource: UserDataDataSource,
    ): AuthInterceptor {
        return AuthInterceptor(userDataDataSource)
    }

}

@Module
@InstallIn(SingletonComponent::class)
interface RetrofitModule {

    @Binds
    fun bindProductsApiDataSource(productsRetrofit: ProductsRetrofit): ProductsApiDataSource

}