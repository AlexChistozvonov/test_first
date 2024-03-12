package com.example.test_first.presentation.productList

import androidx.recyclerview.widget.DiffUtil
import com.example.test_first.data.models.ProductResponse

class DiffUtiProductList(
    private val oldList: List<ProductResponse>,
    private val newList: List<ProductResponse>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }

            oldList[oldItemPosition].title != newList[newItemPosition].title -> {
                false
            }

            oldList[oldItemPosition].description != newList[newItemPosition].description -> {
                false
            }

            oldList[oldItemPosition].thumbnail != newList[newItemPosition].thumbnail -> {
                false
            }

            else -> true
        }
    }
}
