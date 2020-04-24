package com.timelysoft.tsjdomcom.service

import androidx.lifecycle.liveData
import com.timelysoft.tsjdomcom.service.model.ChangePasswordModel
import com.timelysoft.tsjdomcom.service.model.RequestForConnectModel
import com.timelysoft.tsjdomcom.service.request.*
import kotlinx.coroutines.Dispatchers
import okhttp3.MultipartBody

class NetworkRepository {
    // Оброзец
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

    // Мая работа

    fun auth(params: Map<String, String>) = liveData(Dispatchers.IO) {

        try {
            val response = RetrofitService.apiServiceNew().auth(params)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Не найдено"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun refreshToken(params: Map<String, String>) = liveData(Dispatchers.IO) {

        try {
            val response = RetrofitService.apiServiceNew().refreshToken(params)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Не найдено"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun news() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().news()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun messages(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().messages(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun message(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().message(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun deleteMessage(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().deleteMessage(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun addresses() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().addresses()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun status(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().status(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun operations() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().operations()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun periods() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().periods()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun services(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().services(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun invoices(placementId: Int, serviceId: Int, operationId: Int, dateTo: String, dateFrom: String) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().invoices(placementId, serviceId, operationId, dateTo, dateFrom)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun requests() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requests()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun requestTypes() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestTypes()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun requestAddresses() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestAddresses()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun requestUpdate(body: UpdateRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestUpdate(body)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun requestAdd(body: AddRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestAdd(body)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun requestGet(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestGet(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun requestDelete(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestDelete(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun downloadLink(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().downloadLink(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun sendMessageToManager(body: String, title: String, file: List<MultipartBody.Part>) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().sendMessageToManager(body, title, file)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun houses() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().houses()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun placements(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().placements(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun persons(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().persons(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun messageTypes() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().messageTypes()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun messageToPerson(personId: Int, body: String, title: String, file: List<MultipartBody.Part>) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().messageToPerson(personId, body, title, file)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun references(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().references(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun addReferences(certificateRequest: CertificateRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().addReferences(certificateRequest)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun updateReferences(certificateRequest: CertificateRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().updateReferences(certificateRequest)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun reference(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().reference(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun relatives() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().relatives()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun forgotPassword(email: String) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().forgotPassword(email)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun votes(typeId: Int, id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().votes(typeId, id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun votingAddress() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().votingAddress()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun reply(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().reply(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun votingType() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().votingType()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun votingVariants(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().votingVariants(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun votingPost(body: VotingRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().votingPost(body)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun votingDetail(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().votingDetail(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun requestForConnect(body: RequestForConnectModel) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestForConnect(body)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun newsDetail(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().newsDetail(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun newsComment(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().newsComment(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun newsCommentPost(body: NewsCommentRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().newsCommentPost(body)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun newsCommentDelete(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().newsCommentDelete(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun changePassword(model: ChangePasswordModel) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().changePassword(model)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun sendFeedback(model: FeedbackRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().sendFeedback(model)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun sendFirebaseToken(model: FirebaseTokenModel) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().sendFirebaseToken(model)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun managers(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().managers(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun downloadCertificate(helpId: Int, chairmanId: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().downloadCertificate(helpId, chairmanId)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(Resource.success(response.body()))
                    } else {
                        emit(Resource.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(Resource.error("Не найдено"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.netwrok("Проблеммы с подключение интернета", null))
        }
    }
}