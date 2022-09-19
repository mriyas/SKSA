package com.suraksha.cloud

import com.suraksha.cloud.model.APIError

data class ApiState<out T>(val status: Status) {

    //constructor( status: Status,  data: T?,  error: APIError? = null) : this(status)
    //constructor( data: T?,  error: APIError? = null) : this(status)

    var data: Any?=null
    var error: APIError?=null
    enum class Status {
        IDLE,
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): ApiState<T> {
            //return ApiState(Status.SUCCESS, data, null)
            val error= ApiState<T>(Status.SUCCESS)
            error.data=data
            error.error=null
            return error
        }

        fun <T> error(errorDetails: APIError? = null, data: T? = null): ApiState<T> {
            val error= ApiState<T>(Status.ERROR)
            error.data=data
            error.error=errorDetails
            return error
        }

        fun <T> loading(data: T? = null): ApiState<T> {
            val error= ApiState<T>(Status.LOADING)
            error.data=data
            error.error=null
            return error
        }

        fun <T> idle(data: T? = null): ApiState<T> {
            val error= ApiState<T>(Status.IDLE)
            error.data=data
            error.error=null
            return error
        }
    }
}