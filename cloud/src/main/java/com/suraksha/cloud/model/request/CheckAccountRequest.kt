package com.suraksha.cloud.model.request

import com.google.gson.annotations.SerializedName

data class CheckAccountRequest(
    @SerializedName("mobileNumber")
    var number: String = "",
    var appId: Int = 0
)