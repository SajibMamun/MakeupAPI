package com.example.productapiretrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.productapiretrofit.API.ProductService
import com.example.productapiretrofit.API.RetrofitClient
import com.example.productapiretrofit.databinding.FragmentAllProductBinding
import com.example.productapiretrofit.dataclass.ResponseProduct
import com.example.productapiretrofit.dataclass.ResponseProductItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class AllProductFragment: Fragment(),ProductAdapter.ProductListener {
    lateinit var binding: FragmentAllProductBinding

    lateinit var adapter: ProductAdapter
    @Inject
    lateinit var service: ProductService

    var productlist:MutableLiveData<ResponseProduct> = MutableLiveData()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentAllProductBinding.inflate(inflater,container,false)

//Rcv Adapter
        adapter=ProductAdapter(this)
        binding.ProductRCV.adapter=adapter






CoroutineScope(Dispatchers.IO).launch {
    var response=service.getAllProducts()

    if(response.code()==200)
    {
        productlist.postValue(response.body())
    }

}

productlist.observe(viewLifecycleOwner){
    adapter.submitList(it)
}




        return binding.root
    }

    companion object {

    }

    override fun productClickedListener(productId: Int) {


        var bundle=Bundle()
        bundle.putInt(DetailsFragment.PRODUCT_KEY,productId)
        findNavController().navigate(R.id.action_allProductFragment_to_detailsFragment,bundle)
    }
}