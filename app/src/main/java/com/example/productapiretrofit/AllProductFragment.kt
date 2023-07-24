package com.example.productapiretrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.productapiretrofit.databinding.FragmentAllProductBinding


class AllProductFragment : Fragment() {
    lateinit var binding: FragmentAllProductBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentAllProductBinding.inflate(inflater,container,false)

        return binding.root
    }

    companion object {

    }
}