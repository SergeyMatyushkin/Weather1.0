package com.example.weather10.viewmodel

import com.example.weather10.model.Weather

open class AppState {
    data class Success(val weatherData: Weather) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}