package com.timelysoft.tsjdomcom.service

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class NetworkRepository {

    fun auth(map: HashMap<String, String>) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().auth(map)
            when {
                response.isSuccessful -> {
                    emit(Resource.success(response.body()))
                }
                else -> {
                    emit(Resource.error("Неверный логин или пароль", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }
}