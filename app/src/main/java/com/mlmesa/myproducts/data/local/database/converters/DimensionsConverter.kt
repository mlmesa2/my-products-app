package com.mlmesa.myproducts.data.local.database.converters

import androidx.room.TypeConverter
import com.mlmesa.myproducts.data.local.database.DimensionsLocal
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DimensionsConverter {

    @TypeConverter
    fun fromDimensions(value: DimensionsLocal): String = Json.encodeToString(value)

    @TypeConverter
    fun toDimensions(value: String): DimensionsLocal = Json.decodeFromString(value)
}