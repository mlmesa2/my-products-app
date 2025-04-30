package com.mlmesa.myproducts.domain.model

data class ProductDomain(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val brand: String,
    val thumbnail: String,
    val images: String
)
