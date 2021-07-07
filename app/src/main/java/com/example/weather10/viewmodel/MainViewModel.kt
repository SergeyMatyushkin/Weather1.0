package com.example.weather10.viewmodel

import android.os.SystemClock.sleep
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather10.model.Repository
import com.example.weather10.model.RepositoryImpl

class MainViewModel (
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData() ,
            private val repository: Repository = RepositoryImpl()
) :
    ViewModel() {

    fun getLiveData() = liveDataToObserve
    fun getWeatherFromLocalSource() = getWeather()
    fun getWeatherFromRemoteSource() = getWeather()

    fun getWeather() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repository.getWeatherFromLocalStorage()))
        }.start()
    }


}
