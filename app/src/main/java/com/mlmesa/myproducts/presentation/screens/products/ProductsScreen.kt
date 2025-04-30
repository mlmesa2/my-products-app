package com.mlmesa.myproducts.presentation.screens.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.mlmesa.myproducts.R
import com.mlmesa.myproducts.domain.model.ProductDomain
import com.mlmesa.myproducts.presentation.ui.components.ProductsList
import com.mlmesa.myproducts.presentation.ui.components.formatPrice

@Composable
fun ProductsRoute(
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel = hiltViewModel()
){
    val uiState = viewModel.productsState.collectAsStateWithLifecycle()

    ProductsScreen(
        modifier = modifier,
        uiState = uiState.value,
        onRetry = viewModel::fetchProducts
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier,
    uiState: ProductsState,
    onRetry: () -> Unit
) {
    var showDialog by remember { mutableStateOf<ProductDomain?>(null) }
    Scaffold() { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                ProductsState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                is ProductsState.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Error: ${uiState.message}",
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = onRetry
                        ) {
                            Text(text = stringResource(R.string.try_again))
                        }
                    }
                }
                is ProductsState.Success -> {
                    if (uiState.products.isEmpty()) {
                        Text(
                            text = "There are no products available",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .align(Alignment.Center),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    } else {
                        ProductsList(
                            productsList = uiState.products,
                            onProductClick = {
                                showDialog = it
                            }
                        )
                    }
                }
            }

            showDialog?.let { product ->
                ProductDetailDialog(
                    product = product,
                    onDismiss = { showDialog = null }
                )
            }
        }
    }
}

@Composable
fun ProductDetailDialog(
    product: ProductDomain,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = product.title) },
        text = {
            Column {
                AsyncImage(
                    model = product.images.firstOrNull() ?: product.thumbnail,
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 16.dp)
                )

                Text("Brand: ${product.brand}")
                Text("Price: ${formatPrice(product.price)}")

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}