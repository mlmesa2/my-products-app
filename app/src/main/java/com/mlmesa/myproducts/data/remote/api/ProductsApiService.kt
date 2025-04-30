package com.mlmesa.myproducts.data.remote.api

import com.mlmesa.myproducts.data.remote.model.LoginRequest
import com.mlmesa.myproducts.data.remote.model.LoginResponse
import com.mlmesa.myproducts.data.remote.model.ProductsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsApiService {

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ) : Response<LoginResponse>

    @GET("products")
    suspend fun getProducts(): Response<ProductsResponse>
}

