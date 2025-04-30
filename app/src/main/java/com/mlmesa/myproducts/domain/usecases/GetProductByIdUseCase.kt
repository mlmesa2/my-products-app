package com.mlmesa.myproducts.domain.usecases

import com.mlmesa.myproducts.domain.repository.ProductsRepository
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(id: Int) = productsRepository.getProductById(id)
}