package com.vaqueiro.app_perros.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.vaqueiro.app_perros.R
import com.vaqueiro.app_perros.databinding.ActivityDogBinding
import com.vaqueiro.app_perros.models.Dog

class DogActivity : AppCompatActivity() {

    //IMPLEMENTACION DE BINDING
    private lateinit var binding: ActivityDogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog)

        binding = ActivityDogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbarFragment
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dog = intent.getSerializableExtra("dog")as?Dog
        Picasso.get().load(dog?.imageURL).into(binding.imgDog)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}