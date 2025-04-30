package com.mlmesa.myproducts.domain.usecases

import com.mlmesa.myproducts.domain.repository.ProductsRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke() = productsRepository.getProducts()
}