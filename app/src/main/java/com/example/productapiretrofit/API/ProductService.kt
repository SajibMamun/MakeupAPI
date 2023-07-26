package com.example.productapiretrofit.API

import com.example.productapiretrofit.dataclass.ResponseProduct
import com.example.productapiretrofit.dataclass.ResponseProductItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products.json")
    fun getAllProducts():Call<ResponseProduct>


//for getspecific product
    @GET("products/{pid}.json")
    fun getProductsById(@Path("pid") pid:Int):Call<ResponseProductItem>
}