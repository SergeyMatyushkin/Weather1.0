package com.example.weather10.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather10.R
import com.example.weather10.databinding.MainFragmentBinding
import com.example.weather10.view.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        binding = MainFragmentBinding.inflate(layoutInflater)
        val view = binding.getRoot()
        setContentView(view)

        binding.name.setText(viewModel.getName())
        binding.button.setOnClickListener {
            viewModel.userClicked() }


    }



}