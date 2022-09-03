package com.suraksha.cloud.model.response.auth

data class OtpVerifyResponse(
    var otpCode: Int = 0
) : UserDataResponse()

