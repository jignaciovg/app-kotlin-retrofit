package com.vaqueiro.app_perros.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vaqueiro.app_perros.R
import com.vaqueiro.app_perros.adapters.DogAdapter
import com.vaqueiro.app_perros.http.ApiDogs
import com.vaqueiro.app_perros.models.Dog
import com.vaqueiro.app_perros.models.MessageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var dogRecyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter
    private var baseURL = "https://dog.ceo/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogRecyclerView = findViewById(R.id.recycler_dogs)
        dogRecyclerView.setHasFixedSize(true)

        dogRecyclerView.layoutManager = LinearLayoutManager(this)
        //val dogs = getData1()
        //setAdapter(dogs)
        getDataApi()
    }

    private fun setAdapter(dogList: MutableList<Dog>){
        dogAdapter = DogAdapter(dogList){dog ->

        Toast.makeText(this, dog.name,Toast.LENGTH_LONG).show()

         val intent = Intent(this,DogActivity::class.java)
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

    private fun getData1():MutableList<Dog> {
        val lst: MutableList<Dog> = mutableListOf(
            Dog("Affenpinscher","https://images.dog.ceo/breeds/affenpinscher/n02110627_12431.jpg"),
            Dog("Redbone","https://images.dog.ceo/breeds/redbone/n02090379_1006.jpg"),
            Dog("Pug","https://images.dog.ceo/breeds/pug/n02110958_3644.jpg"),
            Dog("Affenpinscher","https://images.dog.ceo/breeds/affenpinscher/n02110627_12431.jpg"),
            Dog("Redbone","https://images.dog.ceo/breeds/redbone/n02090379_1006.jpg"),
            Dog("Pug","https://images.dog.ceo/breeds/pug/n02110958_3644.jpg"),
        )
        return lst
    }
}