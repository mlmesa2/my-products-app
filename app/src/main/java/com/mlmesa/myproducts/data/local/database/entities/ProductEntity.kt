package com.mlmesa.myproducts.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mlmesa.myproducts.data.local.database.DimensionsLocal
import com.mlmesa.myproducts.data.local.database.MetaLocal
import com.mlmesa.myproducts.domain.model.ProductDetailDomain
import com.mlmesa.myproducts.domain.model.ProductDomain
import kotlin.String

@Entity(tableName = "products")
data class ProductEntity(
    val availabilityStatus: String,
    val brand: String,
    val category: String,
    val description: String,
    val dimensions: DimensionsLocal,
    val discountPercentage: Double,
    @PrimaryKey
    val id: Int,
    val images: String,
    val meta: MetaLocal,
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
) {
    fun toProductDomain(): ProductDomain = ProductDomain(
        id = id,
        title = title,
        description = description,
        price = price,
        brand = brand,
        thumbnail = thumbnail,
        images = images
    )

    fun toProductDetailDomain(): ProductDetailDomain = ProductDetailDomain(
        availabilityStatus = availabilityStatus,
        brand = brand,
        category = category,
        description = description,
        dimensions = dimensions.toDimensionsDomain(),
        discountPercentage = discountPercentage,
        id = id,
        images = images,
        meta = meta.toMetaDomain(),
        minimumOrderQuantity = minimumOrderQuantity,
        price = price,
        rating = rating,
        returnPolicy = returnPolicy,
        shippingInformation = shippingInformation,
        sku = sku,
        stock = stock,
        tags = tags,
        thumbnail = thumbnail,
        title = title,
        warrantyInformation = warrantyInformation,
        weight = weight,
    )
}
