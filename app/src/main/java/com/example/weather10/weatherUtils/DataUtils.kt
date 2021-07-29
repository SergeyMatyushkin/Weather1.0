package com.example.weather10.weatherUtils


import com.example.weather10.model.FactDTO
import com.example.weather10.model.Weather
import com.example.weather10.model.WeatherDTO
import com.example.weather10.model.getDefaultCity


fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
    val fact: FactDTO = weatherDTO.fact!!
    return listOf(Weather(getDefaultCity(), fact.temperature!!, fact.feelsLike!!,
        fact.condition!!))
}
