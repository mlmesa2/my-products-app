package com.mlmesa.myproducts.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)