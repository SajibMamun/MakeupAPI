package com.example.productapiretrofit.API

import com.example.productapiretrofit.dataclass.ResponseProduct
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {

    @GET("products.json")
    fun getAllProducts():Call<ResponseProduct>
}