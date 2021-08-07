package com.example.weather10.repository

import com.example.weather10.model.WeatherDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

//Этим интерфейсом мы описываем конкретный запрос на сервер — запрос на данные погоды с
//сервера Яндекса. Он формируется простым методом с аннотациями: указывается endpoint ссылки
//(v2/informers), заголовок (@Header), а два параметра (@Query) запроса передаются в метод как
//аргументы. Возвращает метод уже готовый класс с ответом от сервера (WeatherDTO).

interface WeatherAPI {
    /*Можно добавлять различные параметры и пути, а также обозначить тип запроса — GET или POST —
    и в качестве параметра указать ссылку. Аннотации используются,
    чтобы указывать параметры запроса. Метод должен возвращать generic-тип Call.
    */
    @GET("v2/informers")
    fun getWeather(
        @Header("X-Yandex-API-Key") token: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<WeatherDTO>
}