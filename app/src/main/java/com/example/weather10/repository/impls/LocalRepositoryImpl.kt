package com.example.weather10.repository.impls

import com.example.weather10.model.City
import com.example.weather10.model.Weather
import com.example.weather10.repository.LocalRepository
import com.example.weather10.view.room.HistoryDao
import com.example.weather10.view.room.HistoryEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) :
    LocalRepository {
    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.getAll())
    }

    override fun saveEntity(weather: Weather) {
        Thread { localDataSource.insert(convertWeatherToEntity(weather)) }.start()
    }

    private fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>):
            List<Weather> {
        return entityList.map {
            Weather(City(it.city, 0.0, 0.0), it.temperature, 0, it.condition)
        }
    }

    private fun convertWeatherToEntity(weather: Weather): HistoryEntity {
        return HistoryEntity(
            0, weather.city.city, weather.temperature,
            weather.condition
        )
    }
}