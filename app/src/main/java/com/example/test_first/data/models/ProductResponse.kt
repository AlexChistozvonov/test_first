package com.example.test_first.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductResponse(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String?,
    val price: Int?,
    val rating: Float?,
    val brand: String?,
    val category: String?
) : Parcelable

data class Products(
    val products: List<ProductResponse>
)