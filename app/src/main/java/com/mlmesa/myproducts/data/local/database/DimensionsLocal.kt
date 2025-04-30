package com.mlmesa.myproducts.data.local.database

import com.mlmesa.myproducts.domain.model.DimensionsDomain
import kotlinx.serialization.Serializable

@Serializable
data class DimensionsLocal(
    val depth: Double,
    val height: Double,
    val width: Double
) {
    fun toDimensionsDomain(): DimensionsDomain {
        return DimensionsDomain(
            depth = depth,
            height = height,
            width = width
        )
    }
}
