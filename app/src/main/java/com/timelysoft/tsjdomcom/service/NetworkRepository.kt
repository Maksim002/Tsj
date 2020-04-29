package com.timelysoft.tsjdomcom.service


import androidx.lifecycle.liveData
import com.timelysoft.tsjdomcom.service.model.ChangePasswordModel
import com.timelysoft.tsjdomcom.service.model.RequestForConnectModel
import com.timelysoft.tsjdomcom.service.request.*
import kotlinx.coroutines.Dispatchers
import okhttp3.MultipartBody

class NetworkRepository {

    fun auth(params: Map<String, String>) = liveData(Dispatchers.IO) {

        try {
            val response = RetrofitService.apiServiceNew().auth(params)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun news() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().news()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun messages(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().messages(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun message(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().message(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun deleteMessage(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().deleteMessage(id)
            when {
                response.isSuccessful -> {
                    emit(ResultStatus.success(null, "Ваше сообщение удалено"))
                }
                else -> {
                    emit(ResultStatus.error("Произошла ошибка при удалении"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun addresses() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().addresses()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun status(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().status(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun operations() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().operations()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun periods() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().periods()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun services(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().services(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun invoices(
        placementId: Int,
        serviceId: Int,
        operationId: Int,
        dateFrom: String,
        dateTo: String
    ) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew()
                .invoices(placementId, serviceId, operationId, dateFrom, dateTo)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun requests() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requests()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun requestTypes() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestTypes()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun requestAddresses() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestAddresses()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun requestUpdate(body: UpdateRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestUpdate(body)
            when {
                response.isSuccessful -> {
                        emit(ResultStatus.success(null, "Заявка обновлена"))
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun requestAdd(body: AddRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestAdd(body)
            when {
                response.isSuccessful -> {
                    emit(ResultStatus.success(null, "Заявка добавлена"))
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }


    fun requestGet(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestGet(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun requestDelete(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestDelete(id)
            when {
                response.isSuccessful -> {
                        emit(ResultStatus.success(null, "Ошибка при получении данных"))
                    }
                    else -> {
                        emit(ResultStatus.error("Не известная ошибка"))
                    }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun downloadLink(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().downloadLink(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun sendMessageToManager(body: String, title: String, file: List<MultipartBody.Part>) =
        liveData(Dispatchers.IO) {
            try {
                val response =
                    RetrofitService.apiServiceNew().sendMessageToManager(body, title, file)
                when {
                    response.isSuccessful -> {
                        emit(ResultStatus.success(null, "Ваше сообщение отправлено!"))
                    }
                    else -> {
                        emit(ResultStatus.error("Не известная ошибка"))
                    }
                }
            } catch (e: Exception) {
                emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
            }
        }

    fun houses() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().houses()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun placements(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().placements(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun persons(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().persons(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun messageTypes() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().messageTypes()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun messageToPerson(
        personId: Int,
        body: String,
        title: String,
        file: List<MultipartBody.Part>
    ) = liveData(Dispatchers.IO) {
        try {
            val response =
                RetrofitService.apiServiceNew().messageToPerson(personId, body, title, file)
            when {
                response.isSuccessful -> {
                        emit(ResultStatus.success(null, "Ваше сообщение отправлено!"))
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun references(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().references(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun addReferences(certificateRequest: CertificateRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().addReferences(certificateRequest)
            when {
                response.isSuccessful -> {
                        emit(ResultStatus.success(null, "Данные отправлены"))
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun updateReferences(certificateRequest: CertificateRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().updateReferences(certificateRequest)
            when {
                response.isSuccessful -> {
                    emit(ResultStatus.success(null, "Данные обновлены"))
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun reference(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().reference(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun relatives() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().relatives()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun forgotPassword(email: String) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().forgotPassword(email)
            when {
                response.isSuccessful -> {
                        emit(ResultStatus.success(null, "На ваш email отправлено пиьсмо!"))
                }
                else -> {
                    emit(ResultStatus.error("Такого email не существует"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun votes(typeId: Int, id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().votes(typeId, id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun votingAddress() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().votingAddress()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Неверный логин или пароль"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun reply(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().reply(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun votingType() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().votingType()
            when {
                response.isSuccessful -> {
                        emit(ResultStatus.success(response.body()))
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun votingVariants(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().votingVariants(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun votingPost(body: VotingRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().votingPost(body)
            when {
                response.isSuccessful -> {
                        emit(ResultStatus.success(null, "Ваш голос принят!"))
                }
                else -> {
                    emit(ResultStatus.error("Вы уже проголосовали"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun votingDetail(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().votingDetail(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun requestForConnect(body: RequestForConnectModel) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().requestForConnect(body)
            when {
                response.isSuccessful -> {
                        emit(ResultStatus.success(null, "Ваше заявка отправлена!"))
                }
                else -> {
                    emit(ResultStatus.error("Ошибка при отправлении данных"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun newsDetail(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().newsDetail(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun newsComment(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().newsComment(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun newsCommentPost(body: NewsCommentRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().newsCommentPost(body)
            when {
                response.isSuccessful -> {
                        emit(ResultStatus.success(null, "Ваши коментарии отправлены!"))
                }
                else -> {
                    emit(ResultStatus.error("Что-то пошло не так"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun newsCommentDelete(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().newsCommentDelete(id)
            when {
                response.isSuccessful -> {
                        emit(ResultStatus.success(null, "Удалено!"))
                }
                else -> {
                    emit(ResultStatus.error("Вы уже удалили"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun changePassword(model: ChangePasswordModel) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().changePassword(model)
            when {
                response.isSuccessful -> {
                        emit(ResultStatus.success(null, "Пароль успешно изменен"))
                }
                else -> {
                    emit(ResultStatus.error("Неверный старый пароль"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun sendFeedback(model: FeedbackRequest) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().sendFeedback(model)
            when {
                response.isSuccessful -> {
                        emit(ResultStatus.success(null, "Ваше письмо отправлено!"))
                }
                else -> {
                    emit(ResultStatus.error("Произошла ошибка при отправлении!"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun sendFirebaseToken(model: FirebaseTokenModel) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().sendFirebaseToken(model)
            when {
                response.isSuccessful -> {
                        emit(ResultStatus.success(null))
                }
                else -> {
                    emit(ResultStatus.error("Не известная token"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun managers(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().managers(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    } else {
                        emit(ResultStatus.error("Ошибка при получении данных"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Не известная ошибка"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun downloadCertificate(helpId: Int, chairmanId: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiServiceNew().downloadCertificate(helpId, chairmanId)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body(), "загрузка началось"))
                    }
                }
                else -> {
                    emit(ResultStatus.error("Ошибка проверьте данные на заполнение"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }
}