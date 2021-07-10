package com.example.weather10.view

import MainFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weather10.R
import com.example.weather10.databinding.MainActivityBinding

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

    }

}