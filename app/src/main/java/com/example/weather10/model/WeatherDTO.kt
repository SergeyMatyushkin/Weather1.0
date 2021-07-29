package com.example.weather10.model

data class WeatherDTO (
    val fact: FactDTO?
)
data class FactDTO(
    val temperature: Int?,
    val feelsLike: Int?,
    val condition: String?,
    val icon: String?
)
