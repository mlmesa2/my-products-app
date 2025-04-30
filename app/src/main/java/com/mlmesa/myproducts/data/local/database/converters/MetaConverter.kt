package com.mlmesa.myproducts.data.local.database.converters

import androidx.room.TypeConverter
import com.mlmesa.myproducts.data.local.database.MetaLocal
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MetaConverter {

    @TypeConverter
    fun fromMeta(value: MetaLocal): String = Json.encodeToString(value)

    @TypeConverter
    fun toMeta(value: String): MetaLocal = Json.decodeFromString(value)
}