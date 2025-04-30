package com.mlmesa.myproducts.data.local.datastore

import androidx.datastore.core.DataStore
import com.example.application.proto.UserData
import com.example.application.proto.copy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserDataDataSource @Inject constructor(
    private val userDataPreference: DataStore<UserData>
) {

    val userData = userDataPreference.data.map {
        com.mlmesa.myproducts.domain.model.UserData(
            email = it.email,
            firstName = it.firstName,
            gender = it.gender,
            id = it.id,
            image = it.image,
            lastName = it.lastName,
            username = it.username
        )
    }

    suspend fun setEmail(email: String) {
        userDataPreference.updateData {
            it.copy {
                this.email = email
            }
        }
    }

    suspend fun setFirstName(firstName: String) {
        userDataPreference.updateData {
            it.copy {
                this.firstName = firstName
            }
        }
    }

    suspend fun setGender(gender: String) {
        userDataPreference.updateData {
            it.copy {
                this.gender = gender
            }
        }
    }

    suspend fun setId(id: Int) {
        userDataPreference.updateData {
            it.copy {
                this.id = id
            }
        }
    }

    suspend fun setImage(image: String) {
        userDataPreference.updateData {
            it.copy {
                this.image = image
            }
        }
    }

    suspend fun setLastName(lastName: String) {
        userDataPreference.updateData {
            it.copy {
                this.lastName = lastName
            }
        }
    }

    suspend fun setUserName(username: String) {
        userDataPreference.updateData {
            it.copy {
                this.username = username
            }
        }
    }

    suspend fun setAccessToken(accessToken: String, expirationInSeconds: Long = 3600) {
        val expirationTime =
            System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(expirationInSeconds)
        if (accessToken.isNotEmpty()) {
            userDataPreference.updateData {
                it.copy {
                    this.accessToken = ""
                }
            }
        } else {

            userDataPreference.updateData {
                it.copy {
                    this.accessToken = Crypto.encrypt(accessToken)
                }
            }
            userDataPreference.updateData {
                it.copy {
                    this.timestamp = expirationTime
                }
            }
        }
    }

    suspend fun setRefreshToken(refreshToken: String) {
        userDataPreference.updateData {
            it.copy {
                this.refreshToken = Crypto.encrypt(refreshToken)
            }
        }
    }

    suspend fun getAccessToken(): String? {
        val token = userDataPreference.data.map {
            Crypto.decrypt(it.accessToken)
        }.first()

        val expirationTime = userDataPreference.data.map {
            it.timestamp
        }.first()

        return if (token.isNotEmpty() && System.currentTimeMillis() > expirationTime) {
            token
        } else
            ""
    }

    fun getRefreshToken(): String {
        return userDataPreference.data.map {
            Crypto.decrypt(it.refreshToken)
        }.toString()
    }


    suspend fun isTokenValid(): Boolean {
        return getAccessToken() != null
    }

    suspend fun setUserData(
        email: String,
        firstName: String,
        gender: String,
        id: Int,
        image: String,
        lastName: String,
        username: String
    ) {
        userDataPreference.updateData {
            it.copy {
                this.email = email
                this.firstName = firstName
                this.gender = gender
                this.id = id
                this.image = image
                this.lastName = lastName
                this.username = username
            }
        }
    }
}