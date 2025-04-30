package com.mlmesa.myproducts.data.remote

import com.mlmesa.myproducts.data.local.datastore.UserDataDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import kotlin.io.encoding.ExperimentalEncodingApi

class AuthInterceptor @Inject constructor(
    private val userDataDataSource: UserDataDataSource,
) : Interceptor {
    @OptIn(ExperimentalEncodingApi::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequestBuilder = request.newBuilder()
        val token = getToken()
        // Add the Authorization header
        newRequestBuilder.addHeader("Authorization", "Bearer $token")

        return chain.proceed(newRequestBuilder.build())
    }

    fun getToken(): String? {
        return runBlocking {
            val refreshToken: String? = userDataDataSource.getAccessToken()
            refreshToken
        }
    }
}