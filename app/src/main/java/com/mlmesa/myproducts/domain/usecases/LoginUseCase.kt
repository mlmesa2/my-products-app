package com.mlmesa.myproducts.domain.usecases

import com.mlmesa.myproducts.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(username: String, password: String) =
        loginRepository.login(username, password)
}