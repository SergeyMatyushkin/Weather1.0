package com.example.weather10.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather10.repository.MainRepository
import com.example.weather10.repository.MainRepositoryImpl

import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<Any> = MutableLiveData(),
    private val repositoryImpl: MainRepository = MainRepositoryImpl()
) :
    ViewModel() {

    //получение LiveData
    fun requestLiveData() = liveDataToObserve

    //получение данных погоды русских и зарубежных городов из локального источника
    fun requestWeatherFromLocalSourceRus() = requestDataFromLocalSource(isRussian = true)
    fun requestWeatherFromLocalSourceWorld() = requestDataFromLocalSource(isRussian = false)

    //получение данных погоды из сети
    fun requestWeatherFromRemoteSource() = requestDataFromLocalSource(isRussian = true)

    //имитация запроса к БД
    private fun requestDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserve.value = ScreenState.Loading
        Thread {
            //имитируем процес загрузки на секунду
            sleep(1000)
            //сохраняем данные в LiveData (состояние - приложение работает)
            liveDataToObserve.postValue(
                ScreenState.Success(
                    //if возвращает значение
                    if (isRussian) repositoryImpl.getWeatherFromLocalStorageRus()
                    else repositoryImpl.getWeatherFromLocalStorageWorld()
                )
            )
        }.start()
    }
}