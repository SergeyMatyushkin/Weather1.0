package com.example.weather10.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather10.app.App.Companion.getHistoryDao
import com.example.weather10.repository.LocalRepository
import com.example.weather10.repository.impls.LocalRepositoryImpl

//ViewModel для нового экрана
class HistoryViewModel(
    val historyLiveData: MutableLiveData<ScreenState> = MutableLiveData(),
    private val historyRepository: LocalRepository =
        LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {
    //получение данных из базы
    fun getAllHistory() {
        historyLiveData.value = ScreenState.Loading
        historyLiveData.value = ScreenState.Success(historyRepository.getAllHistory())
    }
}