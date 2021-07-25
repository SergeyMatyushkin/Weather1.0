package com.example.weather10.view

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weather10.R
import com.example.weather10.databinding.MainActivityBinding
import com.example.weather10.view.main.MainFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private val receiver = MainBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }

        //регистрируем MainBroadcastReceiver
        //подписываемся на сообщение перехода в режим самолета
        registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
    }

    override fun onDestroy() {
        //отписываемся от сообщения перехода в режим самолета
        unregisterReceiver(receiver)
        super.onDestroy()

    }
}