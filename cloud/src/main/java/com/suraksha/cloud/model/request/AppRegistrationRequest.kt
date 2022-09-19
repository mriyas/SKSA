package com.suraksha.cloud.model.request

import com.google.gson.annotations.SerializedName

data class AppRegistrationRequest(
    @SerializedName("deviceId")
    var deviceId: String = "",
    @SerializedName("clientVersion")
    var clientVersion: String = "",
    @SerializedName("modelInfo")
    var modelInfo: String = "",
    @SerializedName("osInfo")
    var osInfo: String = "",

)