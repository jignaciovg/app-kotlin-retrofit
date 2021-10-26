package com.vaqueiro.app_perros.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vaqueiro.app_perros.R
import com.vaqueiro.app_perros.activities.DogActivity
import com.vaqueiro.app_perros.adapters.DogAdapter
import com.vaqueiro.app_perros.databinding.FragmentFirstBinding
import com.vaqueiro.app_perros.http.ApiDogs
import com.vaqueiro.app_perros.models.Dog
import com.vaqueiro.app_perros.models.MessageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirstFragment : Fragment(R.layout.fragment_first) {

    //IMPLEMENTACION DE BINDING CON FRAGMENTS
    private var _binding:FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var dogRecyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter
    private var baseURL = "https://dog.ceo/api/"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dogRecyclerView = binding.recyclerDogs
        dogRecyclerView.setHasFixedSize(true)

        dogRecyclerView.layoutManager = LinearLayoutManager(context)
        getDataApi()

    }

    private fun setAdapter(dogList: MutableList<Dog>){
        dogAdapter = DogAdapter(dogList){dog ->

            Toast.makeText(context, dog.name,Toast.LENGTH_LONG).show()

            val intent = Intent(context,DogActivity::class.java)
            intent.putExtra("dog",dog)
            startActivity(intent)

        }
        dogRecyclerView.adapter = dogAdapter
    }

    fun getDataApi(){

        val lst:MutableList<Dog> = mutableListOf()
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseURL)
            .build()
            .create(ApiDogs::class.java)

        val builder = retrofitBuilder.getDogs()

        Log.d("DOG","getData")
        builder.enqueue(object: Callback<MessageResponse?> {
            override fun onResponse(
                call: Call<MessageResponse?>,
                response: Response<MessageResponse?>
            ) {
                Log.d("DOG","Here")
                val responseBody = response.body()!!
                for(imageUrl in responseBody.message)
                {
                    lst.add(Dog(imageUrl,imageUrl))
                }
                setAdapter(lst)
            }
            override fun onFailure(call: Call<MessageResponse?>, t: Throwable) {
                Log.d("DOG","Error")
            }
        })
    }



}