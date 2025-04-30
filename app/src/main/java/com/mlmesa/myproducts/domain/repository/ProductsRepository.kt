package com.mlmesa.myproducts.domain.repository

import com.mlmesa.myproducts.domain.model.ProductDetailDomain
import com.mlmesa.myproducts.domain.model.ProductDomain
import kotlinx.coroutines.flow.Flow
import com.mlmesa.myproducts.common.Result

interface ProductsRepository {
    suspend fun getProducts(): Flow<Result<List<ProductDomain>>>
    suspend fun getProductById(id: Int): Flow<Result<ProductDetailDomain>>

}