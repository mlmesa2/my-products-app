package com.mlmesa.myproducts.data.repository

import com.mlmesa.myproducts.common.Result
import com.mlmesa.myproducts.common.Result.*
import com.mlmesa.myproducts.data.local.datastore.UserDataDataSource
import com.mlmesa.myproducts.data.remote.api.ProductsApiDataSource
import com.mlmesa.myproducts.data.remote.model.LoginRequest
import com.mlmesa.myproducts.domain.model.UserData
import com.mlmesa.myproducts.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    private val productsApiDataSource: ProductsApiDataSource,
    private val userDataDataSource: UserDataDataSource
) : LoginRepository {

    override suspend fun login(
        username: String,
        password: String
    ): Flow<Result<UserData>> = flow {

        when (val result = productsApiDataSource.login(LoginRequest(username, password))) {
            is Success -> {
                userDataDataSource.setAccessToken(result.data.accessToken)
                userDataDataSource.setUserData(
                    email = result.data.email,
                    firstName = result.data.firstName,
                    gender = result.data.gender,
                    id = result.data.id,
                    image = result.data.image,
                    lastName = result.data.lastName,
                    username = result.data.username
                )
                emit(Success(userDataDataSource.userData.first()))
            }

            is Error -> {
                emit(Error(result.exception))
            }

            else -> {
                emit(Empty)

            }
        }
    }

    override suspend fun isLoggedIn(): Boolean = userDataDataSource.isTokenValid()


}