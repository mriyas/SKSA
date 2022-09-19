package com.suraksha.cloud.model

data class APIError(var errorCode: Int, var errorMessage: String){

    object ErrorCodes{
        var EXCEPTION = 10001
        var API_ERROR = 10002
        var NO_INTERNET = 10003
    }

    object ErrorMessages{
        var NO_INTERNET = "Check your network connectivity"
        var EXCEPTION = "Error"
    }
}