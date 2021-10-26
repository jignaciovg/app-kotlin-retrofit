package com.vaqueiro.app_perros.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.vaqueiro.app_perros.R
import com.vaqueiro.app_perros.databinding.FragmentSecondBinding
import com.vaqueiro.app_perros.models.Dog


class SecondFragment : Fragment(R.layout.fragment_second) {

    //IMPLEMENTACION DE BINDING CON FRAGMENTS
    private var _binding:FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}