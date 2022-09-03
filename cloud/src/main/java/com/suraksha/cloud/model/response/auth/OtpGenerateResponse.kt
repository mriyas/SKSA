package com.suraksha.cloud.model.response.auth

import com.suraksha.cloud.model.response.ResponseData

data class OtpGenerateResponse(
    var otpCode: Int = 0
): ResponseData()