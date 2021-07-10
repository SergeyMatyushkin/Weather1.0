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
    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(isRussian = true)
    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussian =
    false)
    fun getWeatherFromRemoteSource() = getDataFromLocalSource(isRussian = true)


  //  fun getWeather() {
  //      liveDataToObserve.value = AppState.Loading
  //      Thread {
  //          sleep(1000)
  //          liveDataToObserve.postValue(AppState.Success(repository.getWeatherFromLocalStorage()))
  //      }.start()
  //  }

    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(if (isRussian)
                repository.getWeatherFromLocalStorageRus() else
                repository.getWeatherFromLocalStorageWorld()))
        }.start()
    }

}
