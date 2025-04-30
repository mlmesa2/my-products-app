package com.mlmesa.myproducts.data.remote.model

data class LoginResponse(
    val accessToken: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val id: Int,
    val image: String,
    val lastName: String,
    val refreshToken: String,
    val username: String
)