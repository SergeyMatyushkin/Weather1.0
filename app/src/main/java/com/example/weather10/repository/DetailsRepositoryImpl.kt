package com.example.weather10.repository.impls

import com.example.weather10.model.WeatherDTO
import com.example.weather10.repository.DetailsRepository
import com.example.weather10.repository.RemoteDataSource


class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) :

    DetailsRepository {
    override fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: retrofit2.Callback<WeatherDTO>
    ) {
        remoteDataSource.getWeatherDetails(lat, lon, callback)
    }
}