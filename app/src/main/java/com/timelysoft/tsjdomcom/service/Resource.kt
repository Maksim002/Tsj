package com.timelysoft.tsjdomcom.service


data class Resource<out T>(val status: Status, val data: T?, val msg: String?) {
    companion object {
        fun <T> success(data: T?,msg: String = ""): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String = "", data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> netwrok(msg: String = "", data: T? = null): Resource<T> {
            return Resource(Status.NETWORK, data, msg)
        }



    }
}

enum class Status {
    SUCCESS,
    ERROR,
    NETWORK

}
