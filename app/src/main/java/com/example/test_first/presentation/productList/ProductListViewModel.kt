package com.example.test_first.presentation.productList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_first.core.util.extantion.LoadingResult
import com.example.test_first.domain.productList.ProductListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productListRepository: ProductListRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductListState())
    val uiState = _uiState.asStateFlow()

    private fun emitEvent(event: ProductListEvent) {
        _uiState.value = _uiState.value.applyEvent(event)
    }

    fun getProductList(skip: Int) {
        emitEvent(ProductListEvent.Loading)
        viewModelScope.launch {
            when (val result = productListRepository.getProductList(skip)) {
                is LoadingResult.Error -> {
                    emitEvent(ProductListEvent.Error(result.exception))
                }

                is LoadingResult.Success -> {
                    emitEvent(ProductListEvent.Success(result.data))
                }
            }

        }
    }
}
