package com.example.productapiretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.productapiretrofit.databinding.ItemProductRcvBinding
import com.example.productapiretrofit.databinding.ItemlayoutdesignBinding
import com.example.productapiretrofit.dataclass.ResponseProduct
import com.example.productapiretrofit.dataclass.ResponseProductItem

class ProductAdapter:androidx.recyclerview.widget.ListAdapter<ResponseProductItem,ProductAdapter.ProductViewHolder> (
    COMPARATOR){

    class ProductViewHolder(var binding: ItemProductRcvBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
       return ProductViewHolder(ItemProductRcvBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let {
            holder.binding.apply {
                productName.text=it.name
                productDescription.text=it.description
                productImage.load(it.imageLink)
                productPrice.text="${it.priceSign} ${it.price} ${it.currency}"


            }
        }
    }


    companion object{
        var COMPARATOR=object :DiffUtil.ItemCallback<ResponseProductItem>(){
            override fun areItemsTheSame(
                oldItem: ResponseProductItem,
                newItem: ResponseProductItem
            ): Boolean {
             return oldItem==newItem
            }

            override fun areContentsTheSame(
                oldItem: ResponseProductItem,
                newItem: ResponseProductItem
            ): Boolean {
         return  oldItem.id==newItem.id
            }


        }
    }
}