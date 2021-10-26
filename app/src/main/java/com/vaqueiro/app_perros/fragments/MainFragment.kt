package com.vaqueiro.app_perros.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.vaqueiro.app_perros.databinding.FragmentMainBinding
import com.vaqueiro.app_perros.R

class MainFragment : Fragment(R.layout.fragment_main) {

    //IMPLEMENTACION DE BINDING
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        //NO ME FUNCIONO ASI :(
        binding.btnLoadPerros.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_firstFragment)
        }*/

        binding.btnLoadPerros.setOnClickListener{
            requireActivity().supportFragmentManager.commit {
                replace(R.id.fragment_container_view,FirstFragment())
            }
        }
    }

}