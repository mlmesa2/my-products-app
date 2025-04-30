package com.mlmesa.myproducts.domain.model

data class ProductDetailDomain(
    val availabilityStatus: String,
    val brand: String,
    val category: String,
    val description: String,
    val dimensions: DimensionsDomain,
    val discountPercentage: Double,
    val id: Int,
    val images: String,
    val meta: MetaDomain,
    val minimumOrderQuantity: Int,
    val price: Double,
    val rating: Double,
    val returnPolicy: String,
    val shippingInformation: String,
    val sku: String,
    val stock: Int,
    val tags: List<String>,
    val thumbnail: String,
    val title: String,
    val warrantyInformation: String,
    val weight: Int
)

data class MetaDomain(
    val barcode: String,
    val createdAt: String,
    val qrCode: String,
    val updatedAt: String
)

data class DimensionsDomain(
    val depth: Double,
    val height: Double,
    val width: Double
)