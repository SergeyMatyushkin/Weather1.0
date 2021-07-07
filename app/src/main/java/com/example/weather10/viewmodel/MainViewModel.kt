package com.example.weather10.viewmodel

import android.os.SystemClock.sleep
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather10.model.RepositoryImpl

class MainViewModel (
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
) :
    ViewModel() {

    fun getLiveData() = liveDataToObserve
    fun getWeatherFromLocalSource() = getDataFromLocalSource()
    fun getWeatherFromRemoteSource() = getDataFromLocalSource()

    fun getWeather() = getDataFromLocalSource(){
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorage()))
        }.start()
    }


}
