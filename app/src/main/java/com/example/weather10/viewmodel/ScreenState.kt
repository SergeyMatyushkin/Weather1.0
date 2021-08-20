package com.example.weather10.viewmodel

import com.example.weather10.model.Weather

sealed class ScreenState {
    data class Success(val weatherData: List<Weather>) : ScreenState()
    data class Error(val error: Throwable) : ScreenState()
    object Loading : ScreenState()
}