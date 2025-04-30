package com.mlmesa.myproducts.data.remote.api

import com.mlmesa.myproducts.data.remote.model.LoginRequest
import com.mlmesa.myproducts.data.remote.model.LoginResponse
import com.mlmesa.myproducts.data.remote.model.ProductsResponse
import com.mlmesa.myproducts.data.remote.utils.MAIN_URL
import com.mlmesa.myproducts.data.remote.utils.safeApiCall
import com.mlmesa.myproducts.common.Result
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRetrofit @Inject constructor(
    networkJson: Json,
    productsCallFactory: dagger.Lazy<Call.Factory>
) : ProductsApiDataSource {

    private val newNetworkApi = Retrofit.Builder()
        .baseUrl(MAIN_URL)
        .callFactory { productsCallFactory.get().newCall(it) }
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(ProductsApiService::class.java)

    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> =
        safeApiCall {
           newNetworkApi.login(loginRequest)
        }

    override suspend fun getProducts(): Result<ProductsResponse> =
        safeApiCall {
            newNetworkApi.getProducts()
        }

}