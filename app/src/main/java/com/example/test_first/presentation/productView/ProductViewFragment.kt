package com.example.test_first.presentation.productView

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.test_first.R
import com.example.test_first.data.models.ProductResponse
import com.example.test_first.databinding.ProductViewFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductViewFragment : Fragment(R.layout.product_view_fragment) {

    private val binding by viewBinding(ProductViewFragmentBinding::bind)
    private val args: ProductViewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(args.product)
    }

    private fun initView(product: ProductResponse) = with(binding) {
        context?.let { Glide.with(it).load(product.thumbnail).into(ivThumbnail) }
        tvTitle.text = product.title
        tvDescription.text = product.description
        product.brand?.let { tvBrand.text = getString(R.string.product_brand, it) }
        product.category?.let { tvCategory.text = getString(R.string.product_category, it) }
        product.price?.let { tvPrice.text = getString(R.string.product_price, it.toString()) }
        product.rating?.let { tvRating.text = getString(R.string.product_rating, it.toString()) }
    }
}