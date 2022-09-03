package com.suraksha.cloud

import com.suraksha.cloud.model.CloudError

data class Resource<out T>(val status: Status, val data: T?, val errorDetails: CloudError? = null) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(errorDetails: CloudError? = null, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, errorDetails)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}