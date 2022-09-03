package com.suraksha.cloud.model.request

data class AppRegistrationRequest(
    var deviceId: String = "",
    var osInfo: String = "",
    var deviceInfo: String = "",
    var appVersion: String = "",
    var deviceToken: String = "",
)