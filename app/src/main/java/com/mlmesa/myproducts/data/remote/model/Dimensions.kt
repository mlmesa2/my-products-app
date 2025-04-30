package com.mlmesa.myproducts.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Dimensions(
    val depth: Double,
    val height: Double,
    val width: Double
)