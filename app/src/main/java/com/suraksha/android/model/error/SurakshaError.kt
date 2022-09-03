package com.suraksha.android.model.error

class SurakshaError(var errorCode: Int, var errorMessage: String) {

    var data:Any?=null
    fun getCode(): Int {
        return errorCode
    }
    fun getMessage(): String {
        return errorMessage
    }
}