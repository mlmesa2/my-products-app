package com.mlmesa.myproducts.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlmesa.myproducts.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mlmesa.myproducts.common.Result

sealed interface LoginState {
    object Idle : LoginState
    object Loading : LoginState
    object Success : LoginState
    data class Error(val message: String) : LoginState
}
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(username: String, password: String) {
        _loginState.value = LoginState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase(username, password).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _loginState.value = LoginState.Success
                    }

                    else -> {
                        if (result is Result.Error) {
                            _loginState.value = LoginState.Error(result.exception?.message ?: "Unknown error")
                        }
                    }
                }
            }
        }
    }
}