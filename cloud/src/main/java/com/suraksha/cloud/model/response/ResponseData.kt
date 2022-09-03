package com.suraksha.cloud.model.response

import com.google.gson.annotations.SerializedName

open class ResponseData(
    @SerializedName("code")
    var responseDataCode: Int = 0,
    @SerializedName("message")
    var responseDataMsg: String = ""
)