package com.mlmesa.myproducts.di

import android.content.Context
import androidx.room.Room
import com.mlmesa.myproducts.data.local.database.MyProductsDatabase
import com.mlmesa.myproducts.data.local.database.dao.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMyProductsDatabase(
        @ApplicationContext context: Context,
    ): MyProductsDatabase = Room.databaseBuilder(
        context = context,
        klass = MyProductsDatabase::class.java,
        name = "my_products_db",
    ).build()
}

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesProductsDao(
        database: MyProductsDatabase,
    ): ProductsDao = database.productsDao()
}