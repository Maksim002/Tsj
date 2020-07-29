package com.timelysoft.tsjdomcom.service


import androidx.lifecycle.liveData
import com.timelysoft.tsjdomcom.service.model.ChangePasswordModel
import com.timelysoft.tsjdomcom.service.model.RequestForConnectModel
import com.timelysoft.tsjdomcom.service.request.*
import com.timelysoft.tsjdomcom.service.request.provider.CreateSupplier
import com.timelysoft.tsjdomcom.service.request.provider.ProviderEdit
import com.timelysoft.tsjdomcom.service.request.user.Edit
import kotlinx.coroutines.Dispatchers
import okhttp3.MultipartBody

class NetworkRepository {

    fun auth(params: Map<String, String>) = liveData(Dispatchers.IO) {

        try {
            val response = RetrofitService.apiService().auth(params)
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
            val response = RetrofitService.apiService().news()
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
            val response = RetrofitService.apiService().messages(id)
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
            val response = RetrofitService.apiService().message(id)
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
            val response = RetrofitService.apiService().deleteMessage(id)
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
            val response = RetrofitService.apiService().addresses()
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
            val response = RetrofitService.apiService().status(id)
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
            val response = RetrofitService.apiService().operations()
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
            val response = RetrofitService.apiService().periods()
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
            val response = RetrofitService.apiService().services(id)
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
            val response = RetrofitService.apiService()
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
            val response = RetrofitService.apiService().requests()
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
            val response = RetrofitService.apiService().requestTypes()
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
            val response = RetrofitService.apiService().requestAddresses()
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
            val response = RetrofitService.apiService().requestUpdate(body)
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
            val response = RetrofitService.apiService().requestAdd(body)
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
            val response = RetrofitService.apiService().requestGet(id)
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
            val response = RetrofitService.apiService().requestDelete(id)
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
            val response = RetrofitService.apiService().downloadLink(id)
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
                    RetrofitService.apiService().sendMessageToManager(body, title, file)
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
            val response = RetrofitService.apiService().houses()
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
            val response = RetrofitService.apiService().placements(id)
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
            val response = RetrofitService.apiService().persons(id)
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
            val response = RetrofitService.apiService().messageTypes()
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
                RetrofitService.apiService().messageToPerson(personId, body, title, file)
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
            val response = RetrofitService.apiService().references(id)
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
            val response = RetrofitService.apiService().addReferences(certificateRequest)
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
            val response = RetrofitService.apiService().updateReferences(certificateRequest)
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
            val response = RetrofitService.apiService().reference(id)
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
            val response = RetrofitService.apiService().relatives()
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
            val response = RetrofitService.apiService().forgotPassword(email)
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
            val response = RetrofitService.apiService().votes(typeId, id)
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
            val response = RetrofitService.apiService().votingAddress()
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
            val response = RetrofitService.apiService().reply(id)
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
            val response = RetrofitService.apiService().votingType()
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
            val response = RetrofitService.apiService().votingVariants(id)
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
            val response = RetrofitService.apiService().votingPost(body)
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
            val response = RetrofitService.apiService().votingDetail(id)
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
            val response = RetrofitService.apiService().requestForConnect(body)
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
            val response = RetrofitService.apiService().newsDetail(id)
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
            val response = RetrofitService.apiService().newsComment(id)
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
            val response = RetrofitService.apiService().newsCommentPost(body)
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
            val response = RetrofitService.apiService().newsCommentDelete(id)
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
            val response = RetrofitService.apiService().changePassword(model)
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
            val response = RetrofitService.apiService().sendFeedback(model)
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
            val response = RetrofitService.apiService().sendFirebaseToken(model)
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
            val response = RetrofitService.apiService().managers(id)
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
            val response = RetrofitService.apiService().downloadCertificate(helpId, chairmanId)
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

    fun user() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiService().user()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    }else{
                        emit(ResultStatus.error("Ошибка при получении пользователей"))
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

    fun edit(model: Edit) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiService().edit(model)
            when {
                response.isSuccessful -> {
                    emit(ResultStatus.success(null, "Ваше данные обновлены"))
                }
                else -> {
                    emit(ResultStatus.error("Произошла ошибка обновлении данных"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun provider() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiService().provider()
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    }else{
                        emit(ResultStatus.error("Ошибка при получении поставщика"))
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

    fun createSupplier(model: CreateSupplier) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiService().createSupplier(model)
            when {
                response.isSuccessful -> {
                    emit(ResultStatus.success(null, "Поставщик добавлен успешно"))
                }
                else -> {
                    emit(ResultStatus.error("Произошла ошибка при добавлении поставщика"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun providerDelete(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiService().providerDelete(id)
            when {
                response.isSuccessful -> {
                    emit(ResultStatus.success(null, "Поставщик удалён"))
                }
                else -> {
                    emit(ResultStatus.error("Произошла ошибка при удалении"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
        }
    }

    fun providerEdit(model: ProviderEdit) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiService().providerEdit(model)
            when {
                response.isSuccessful -> {
                    emit(ResultStatus.success(null, "Данные поставщика обновлены"))
                }
                else -> {
                    emit(ResultStatus.error("Произошла ошибка при обновлении данных"))
                }
            }
        } catch (e: Exception) {
            emit(ResultStatus.netwrok("Проблеммы с подключение интернета", null))
        }
    }

    fun providerId(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiService().providerId(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    }else{
                        emit(ResultStatus.error("Ошибка при получении поставщика"))
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

    fun editId(id: Int) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitService.apiService().editId(id)
            when {
                response.isSuccessful -> {
                    if (response.body() != null) {
                        emit(ResultStatus.success(response.body()))
                    }else{
                        emit(ResultStatus.error("Ошибка при получении пользователя"))
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
}