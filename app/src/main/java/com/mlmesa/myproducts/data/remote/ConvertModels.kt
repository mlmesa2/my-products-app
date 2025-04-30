package com.mlmesa.myproducts.data.remote

import com.mlmesa.myproducts.data.local.database.DimensionsLocal
import com.mlmesa.myproducts.data.local.database.MetaLocal
import com.mlmesa.myproducts.data.local.database.entities.ProductEntity
import com.mlmesa.myproducts.data.remote.model.LoginResponse
import com.mlmesa.myproducts.data.remote.model.Product
import com.mlmesa.myproducts.domain.model.UserData


fun LoginResponse.toUserData(): UserData = UserData(
    email = email,
    firstName = firstName,
    gender = gender,
    id = id,
    image = image,
    lastName = lastName,
    username = username
)

fun Product.toProductEntity(): ProductEntity = ProductEntity(
    availabilityStatus = availabilityStatus,
    brand = brand,
    category = category,
    description = description,
    dimensions = DimensionsLocal(
        depth = dimensions.depth,
        height = dimensions.height,
        width = dimensions.width
    ),
    discountPercentage = discountPercentage,
    id = id,
    images = images.first(),
    meta = MetaLocal(
        barcode = meta.barcode,
        createdAt = meta.createdAt,
        qrCode = meta.qrCode,
        updatedAt = meta.updatedAt
    ),
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
    weight = weight
)
