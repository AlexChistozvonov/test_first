package com.example.test_first.presentation.productList

import com.example.test_first.data.models.Products

sealed class ProductListEvent {
    data class Success(val productList: Products?) : ProductListEvent()
    data object Loading : ProductListEvent()
    data class Error(val error: Exception?) : ProductListEvent()
}

data class ProductListState(
    val error: Exception? = null,
    val loading: Boolean = false,
    val productList: Products? = null,
    val event: ProductListEvent = ProductListEvent.Loading
) {
    fun applyEvent(event: ProductListEvent) = when (event) {
        is ProductListEvent.Error -> copy(error = event.error, event = event)
        is ProductListEvent.Success -> copy(
            productList = event.productList,
            loading = false,
            event = event
        )
        ProductListEvent.Loading -> copy(loading = true, event = event)
    }
}