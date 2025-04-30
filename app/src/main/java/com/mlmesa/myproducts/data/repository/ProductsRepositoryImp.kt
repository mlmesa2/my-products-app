package com.mlmesa.myproducts.data.repository

import com.mlmesa.myproducts.common.Result
import com.mlmesa.myproducts.data.local.database.dao.ProductsDao
import com.mlmesa.myproducts.data.remote.api.ProductsApiDataSource
import com.mlmesa.myproducts.data.remote.toProductEntity
import com.mlmesa.myproducts.domain.model.ProductDetailDomain
import com.mlmesa.myproducts.domain.model.ProductDomain
import com.mlmesa.myproducts.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsRepositoryImp @Inject constructor(
    private val productsApiDataSource: ProductsApiDataSource,
    private val productsDao: ProductsDao
) : ProductsRepository {

    override suspend fun getProducts(): Flow<Result<List<ProductDomain>>> = flow {
        val localProducts = productsDao.getProducts().first()
        if (localProducts.isNotEmpty()) {
            emit(Result.Success(localProducts.map { it.toProductDomain() }))
        } else {
            emit(Result.Loading)
        }

        when (val response = productsApiDataSource.getProducts()) {
            is Result.Success -> {
                productsDao.insertProducts(response.data.products.map { it.toProductEntity() })
                emit(Result.Success(productsDao.getProducts().first().map { it.toProductDomain() }))
            }

            else -> {
                if (response is Result.Error) {
                    emit(Result.Error(response.exception))
                }
            }
        }
    }

    override suspend fun getProductById(id: Int): Flow<Result<ProductDetailDomain>> = flow {
        productsDao.getProductById(id).collect {
            emit(Result.Success(it.toProductDetailDomain()))
        }
    }
}