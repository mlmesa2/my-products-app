package com.mlmesa.myproducts.di

import kotlin.annotation.AnnotationRetention.RUNTIME
import javax.inject.Qualifier

@Retention(RUNTIME)
@Qualifier
annotation class Dispatcher(val myProductsDispatchers: MyProductsDispatchers)

enum class MyProductsDispatchers {
    Default,
    IO,
}