package com.example.test_first.data.network

import com.example.test_first.core.util.constants.Constants.LIMIT_PRODUCTS
import com.example.test_first.data.models.Products
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/products")
    suspend fun getProductList(
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = LIMIT_PRODUCTS
    ): Products
}

