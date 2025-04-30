package com.mlmesa.myproducts.data.remote.api

import com.mlmesa.myproducts.data.remote.model.LoginRequest
import com.mlmesa.myproducts.data.remote.model.LoginResponse
import com.mlmesa.myproducts.common.Result
import com.mlmesa.myproducts.data.remote.model.ProductsResponse

interface ProductsApiDataSource {
    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse>
    suspend fun getProducts(): Result<ProductsResponse>
}