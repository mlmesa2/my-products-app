package com.mlmesa.myproducts.data.local.database

import com.mlmesa.myproducts.domain.model.MetaDomain
import kotlinx.serialization.Serializable

@Serializable
data class MetaLocal(
    val barcode: String,
    val createdAt: String,
    val qrCode: String,
    val updatedAt: String
) {
    fun toMetaDomain(): MetaDomain = MetaDomain(
        barcode = barcode,
        createdAt = createdAt,
        qrCode = qrCode,
        updatedAt = updatedAt
    )
}