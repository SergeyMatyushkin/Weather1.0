package com.example.weather10.repository

import com.example.weather10.model.Weather
import com.example.weather10.model.getRussianCities
import com.example.weather10.model.getWorldCities

class MainRepositoryImpl : MainRepository {
    //то же самое, что и :  override fun getWeatherFromServer(): Weather { return Weather() }
    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}