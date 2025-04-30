package com.mlmesa.myproducts.domain.usecases

import com.mlmesa.myproducts.domain.repository.LoginRepository
import javax.inject.Inject

class IsLoggedInUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke() = loginRepository.isLoggedIn()
}