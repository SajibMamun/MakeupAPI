package com.example.productapiretrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import com.example.productapiretrofit.API.RetrofitClient
import com.example.productapiretrofit.databinding.FragmentDetailsBinding
import com.example.productapiretrofit.dataclass.ResponseProductItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)


var ProductID=requireArguments().getInt(PRODUCT_KEY)

        var CallApiServiceById= RetrofitClient.service.getProductsById(ProductID)


        CallApiServiceById.enqueue(object : Callback<ResponseProductItem> {
            override fun onFailure(call: Call<ResponseProductItem>?, t: Throwable?) {


                if (t != null) {
                    Toast.makeText(requireContext(),"${t.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call<ResponseProductItem>?, response: Response<ResponseProductItem>?) {
                if (response != null) {
                    if(response.code()==200) {

                        var product=response.body()


                        binding.apply {

                            if (product != null) {
                                productName.text=product.name
                                productDescription.text=product.description
                                productImage.load(product.imageLink)
                                productPrice.text="${product.priceSign} ${product.price} ${product.currency}"
                                productTags.text=product.tagList.toString()
                            }
                        }







                    }
                }
            }

        })





        return binding.root
    }

    companion object {

        const val PRODUCT_KEY="productid"

    }
}

