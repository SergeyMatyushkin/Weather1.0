package com.example.weather10.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather10.R
import com.example.weather10.databinding.MainActivityBinding
import com.example.weather10.databinding.MainFragmentBinding
import com.example.weather10.view.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding:  MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
//        binding = MainActivityBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)

    }



}