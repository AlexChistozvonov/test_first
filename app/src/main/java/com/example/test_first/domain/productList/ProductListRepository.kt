package com.example.test_first.domain.productList

import com.example.test_first.core.util.extantion.LoadingResult
import com.example.test_first.data.models.Products

interface ProductListRepository {
    suspend fun getProductList(skip: Int): LoadingResult<Products>
}