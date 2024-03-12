package com.example.test_first.data.productList

import com.example.test_first.data.network.Api
import com.example.test_first.core.util.extantion.ErrorMapper
import com.example.test_first.core.util.extantion.LoadingResult
import com.example.test_first.core.util.extantion.runLoading
import com.example.test_first.data.models.Products
import com.example.test_first.di.IoDispatcher
import com.example.test_first.domain.productList.ProductListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val networkService: Api,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val errorMapper: ErrorMapper
) : ProductListRepository {

    override suspend fun getProductList(skip: Int): LoadingResult<Products> {
        return withContext(coroutineDispatcher) {
            runLoading(errorMapper) {
                networkService.getProductList(skip = skip)
            }
        }
    }
}
