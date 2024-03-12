package com.example.test_first.presentation.productList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test_first.R
import com.example.test_first.data.models.ProductResponse

class ProductListAdapter(private val listener: (id: ProductResponse) -> Unit) :
    RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

    private var oldProductsList = listOf<ProductResponse>()


    fun setData(newProductsList: List<ProductResponse>) {
        val diffUtil = DiffUtiProductList(oldProductsList, newProductsList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldProductsList = newProductsList
        diffResults.dispatchUpdatesTo(this)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val description: TextView = itemView.findViewById(R.id.tvDescription)
        val thumbnail: ImageView = itemView.findViewById(R.id.ivThumbnail)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.invoke(oldProductsList[layoutPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_products_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int): Unit = with(holder) {
        title.text = oldProductsList[position].title
        description.text = oldProductsList[position].description
        Glide.with(itemView.context).load(oldProductsList[position].thumbnail).into(thumbnail)
    }

    override fun getItemCount() = oldProductsList.size
}