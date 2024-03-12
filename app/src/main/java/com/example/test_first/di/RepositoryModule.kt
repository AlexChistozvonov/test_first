package com.example.test_first.di

import com.example.test_first.data.productList.ProductListRepositoryImpl
import com.example.test_first.domain.productList.ProductListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun productListRepository(
        productListRepository: ProductListRepositoryImpl
    ): ProductListRepository
}
