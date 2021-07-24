package com.example.weather10.view.details

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.weather10.model.WeatherDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection


private const val WEATHER_API_KEY = "8723e083-1328-4c61-8c0d-46dbd00e11fd"

@RequiresApi(Build.VERSION_CODES.N)
class WeatherLoader(
    private val listener: WeatherLoaderListener,
    private val lat: Double,
    private val lon: Double
) {
    fun loadWeather() {
        try {
            val uri = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
            //Handler создаётся в UI-потоке, а ВЫЗЫВАЕТ метод post в рабочем потоке
            val handler = Handler()
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    //формирование запроса urlConnection
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    //добавление заголовка в запрос urlConnection
                    urlConnection.addRequestProperty(
                        "X-Yandex-API-Key",
                        WEATHER_API_KEY
                    )
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    // преобразование ответа от сервера (JSON) в модель данных (WeatherDTO)
                    val weatherDTO: WeatherDTO =
                        Gson().fromJson(getLines(bufferedReader), WeatherDTO::class.java)
                    //вызываем метод в рабочем потоке
                    handler.post { listener.onLoaded(weatherDTO) }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    //Обработка ошибки
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            //TODO Обработка ошибки
            listener.onFailed(e)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }


    interface WeatherLoaderListener {
        //загружен
        fun onLoaded(weatherDTO: WeatherDTO)

        //неудачно
        fun onFailed(throwable: Throwable)
    }
}