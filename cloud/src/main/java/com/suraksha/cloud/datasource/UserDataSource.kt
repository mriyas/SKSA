package com.suraksha.cloud.datasource

import com.suraksha.cloud.model.request.AppRegistrationRequest
import com.suraksha.cloud.model.request.LoginRequest
import com.suraksha.cloud.model.request.OtpGenerationRequest
import com.suraksha.cloud.model.request.OtpValidateRequest
import com.suraksha.cloud.services.UserApiService
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userApiService: UserApiService
): BaseDataSource() {
    suspend fun registerApp(appRegistrationRequest: AppRegistrationRequest) = getResult {
        userApiService.registerApp(appRegistrationRequest)
    }

    suspend fun login(checkAccountRequest: LoginRequest) = getResult {
        userApiService.login(checkAccountRequest)
    }

    suspend fun generateOtp(otpGenerationRequest: OtpGenerationRequest) = getResult {
        userApiService.generateOtp(otpGenerationRequest)
    }

    suspend fun verifyOtp(otpValidateRequest: OtpValidateRequest) = getResult {
        userApiService.verifyOtp(otpValidateRequest)
    }
}