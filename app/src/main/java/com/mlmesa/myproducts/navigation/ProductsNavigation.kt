package com.mlmesa.myproducts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mlmesa.myproducts.presentation.screens.login.LoginRoute
import com.mlmesa.myproducts.presentation.screens.product_details.ProductDetailRoute
import com.mlmesa.myproducts.presentation.screens.products.ProductsRoute
import kotlinx.serialization.Serializable

@Serializable
object LoginNavigation

@Serializable
object ProductsNavigation

//Para proxima implementacion
@Serializable
data class ProductDetailNavigation(val id: Int)

@Composable
fun ProductsNavigation(
    isLogged: Boolean,
    modifier: Modifier = Modifier
) {
    val navHost = rememberNavController()
    NavHost(
        navController = navHost,
        startDestination = if (isLogged) ProductsNavigation else LoginNavigation,
        modifier = modifier
    ) {
        composable<LoginNavigation> {
            LoginRoute(
                onLoginSuccess = {
                    navHost.navigate(ProductsNavigation)
                }
            )
        }

        composable<ProductsNavigation> {
            ProductsRoute()
        }

        //Para proxima implementacion
        composable<ProductDetailNavigation> {
            ProductDetailRoute()
        }
    }
}