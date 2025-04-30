package com.mlmesa.myproducts.di

import com.mlmesa.myproducts.data.local.database.dao.ProductsDao
import com.mlmesa.myproducts.data.local.datastore.UserDataDataSource
import com.mlmesa.myproducts.data.remote.api.ProductsApiDataSource
import com.mlmesa.myproducts.data.repository.LoginRepositoryImp
import com.mlmesa.myproducts.data.repository.ProductsRepositoryImp
import com.mlmesa.myproducts.domain.repository.LoginRepository
import com.mlmesa.myproducts.domain.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        productsApiDataSource: ProductsApiDataSource,
        userDataDataSource: UserDataDataSource
    ): LoginRepository {
        return LoginRepositoryImp(productsApiDataSource, userDataDataSource)
    }

    @Provides
    @Singleton
    fun provideProductsRepository(
        productsApiDataSource: ProductsApiDataSource,
        productsDao: ProductsDao
    ) : ProductsRepository {
        return ProductsRepositoryImp(productsApiDataSource, productsDao)
    }
}