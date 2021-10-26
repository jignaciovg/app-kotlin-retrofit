package com.vaqueiro.app_perros.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.commit
import com.vaqueiro.app_perros.R
import com.vaqueiro.app_perros.databinding.ActivityFragmentsBinding
import com.vaqueiro.app_perros.fragments.MainFragment

class FragmentsActivity : AppCompatActivity() {

    //IMPLEMENTACION DE BINDING
    private lateinit var binding: ActivityFragmentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)

        binding = ActivityFragmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbarFragment
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, MainFragment())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}