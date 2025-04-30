package com.mlmesa.myproducts.domain.repository

import com.mlmesa.myproducts.common.Result
import com.mlmesa.myproducts.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(username: String, password: String): Flow<Result<UserData>>
    suspend fun isLoggedIn(): Boolean
}