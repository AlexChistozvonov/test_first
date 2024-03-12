package com.example.test_first.presentation.productList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.test_first.R
import com.example.test_first.core.util.NetworkException
import com.example.test_first.core.util.constants.Constants.LIMIT_PRODUCTS
import com.example.test_first.core.util.extantion.hasConnection
import com.example.test_first.core.util.extantion.hide
import com.example.test_first.core.util.extantion.navigateSafe
import com.example.test_first.core.util.extantion.onClick
import com.example.test_first.core.util.extantion.show
import com.example.test_first.core.util.extantion.showGeneralErrorDialog
import com.example.test_first.data.models.Products
import com.example.test_first.databinding.ProductListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class ProductListFragment : Fragment(R.layout.product_list_fragment) {

    private val binding by viewBinding(ProductListFragmentBinding::bind)
    private val viewModel by viewModels<ProductListViewModel>()

    private val productListAdapter by lazy {
        ProductListAdapter {
            findNavController().navigateSafe(
                ProductListFragmentDirections.openProductViewFragment(it)
            )
        }
    }
    private var currentSkip = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initObservers()
        initRecyclerView()
    }

    private fun init() = with(binding) {
        btCurrent.text = (currentSkip / LIMIT_PRODUCTS + 1).toString()
        viewModel.getProductList(currentSkip)
        btNext.onClick {
            currentSkip += LIMIT_PRODUCTS
            viewModel.getProductList(currentSkip)
            btCurrent.text = (currentSkip / LIMIT_PRODUCTS + 1).toString()
        }
        btBack.onClick {
            currentSkip -= LIMIT_PRODUCTS
            viewModel.getProductList(currentSkip)
            btCurrent.text = (currentSkip / LIMIT_PRODUCTS + 1).toString()
        }
        btRefresh.onClick { viewModel.getProductList(currentSkip) }
    }

    private fun initObservers() {
        viewModel.uiState.onEach { handleState(it) }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleState(state: ProductListState) = with(binding) {
        when (state.event) {
            is ProductListEvent.Success -> {
                progressBar.hide()
                recyclerview.show()
                llPages.show()
                btRefresh.hide()

                if (state.productList?.products?.size!! > 0) {
                    btNext.show()
                } else btNext.hide()
                updateList(state.productList)
                if (currentSkip > 0) btBack.show() else btBack.hide()
            }

            is ProductListEvent.Loading -> progressBar.show()

            is ProductListEvent.Error -> {

                if (hasConnection(context)) {
                    showGeneralErrorDialog(
                        context = requireContext(),
                        exception = state.error
                    )
                } else {
                    showGeneralErrorDialog(
                        context = requireContext(),
                        exception = NetworkException()
                    )
                }
                btRefresh.show()
                progressBar.hide()
                recyclerview.hide()
                llPages.hide()
            }
        }
    }

    private fun initRecyclerView() = with(binding) {
        val recyclerView: RecyclerView = recyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = productListAdapter
    }

    private fun updateList(data: Products?) {
        data?.products?.let { productListAdapter.setData(it) }
    }
}