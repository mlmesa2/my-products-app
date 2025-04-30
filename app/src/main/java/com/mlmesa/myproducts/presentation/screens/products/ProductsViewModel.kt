package com.mlmesa.myproducts.presentation.screens.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlmesa.myproducts.common.Result
import com.mlmesa.myproducts.domain.model.ProductDomain
import com.mlmesa.myproducts.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface ProductsState {
    object Loading : ProductsState
    data class Success(val products: List<ProductDomain>) : ProductsState
    data class Error(val message: String) : ProductsState
}

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _productsState = MutableStateFlow<ProductsState>(ProductsState.Loading)
    val productsState: StateFlow<ProductsState> = _productsState
        .onStart {
            fetchProducts()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ProductsState.Loading
        )

    fun fetchProducts() {
        _productsState.value = ProductsState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            getProductsUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _productsState.value = ProductsState.Success(result.data)
                    }
                    is Result.Error -> {
                        _productsState.value = ProductsState.Error(result.exception?.message ?: "Unknown error")
                    }
                    else -> {}
                }
            }
        }
    }
}