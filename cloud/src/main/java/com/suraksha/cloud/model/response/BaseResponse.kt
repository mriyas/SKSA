package com.suraksha.cloud.model.response

import com.google.gson.annotations.SerializedName

open class BaseResponse<T> {
    @SerializedName("status")
    var status: ResponseStatus? = null
    @SerializedName("data")
    var data: T? = null
    @SerializedName("version")
    var currentVersion: String? = null

    fun isValidResponse() : Boolean{
        var isValid  = false;
        if(null != status){
            if(status?.statusCode == 20001
                && null != data){
                isValid = true
            }
        }
        return isValid;
    }
}