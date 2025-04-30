package com.mlmesa.myproducts.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mlmesa.myproducts.data.local.database.converters.DimensionsConverter
import com.mlmesa.myproducts.data.local.database.converters.ListStringsConverter
import com.mlmesa.myproducts.data.local.database.converters.MetaConverter
import com.mlmesa.myproducts.data.local.database.dao.ProductsDao
import com.mlmesa.myproducts.data.local.database.entities.ProductEntity

@Database(
    entities = [ProductEntity::class], version = 1, exportSchema = false
)
@TypeConverters(ListStringsConverter::class, DimensionsConverter::class, MetaConverter::class)
abstract class MyProductsDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
}