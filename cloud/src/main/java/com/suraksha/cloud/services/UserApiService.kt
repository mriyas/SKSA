package com.suraksha.cloud.services

import com.suraksha.cloud.model.request.AppRegistrationRequest
import com.suraksha.cloud.model.request.CheckAccountRequest
import com.suraksha.cloud.model.request.OtpGenerationRequest
import com.suraksha.cloud.model.request.OtpValidateRequest
import com.suraksha.cloud.model.response.*
import com.suraksha.cloud.model.response.auth.OtpGenerateResponse
import com.suraksha.cloud.model.response.auth.OtpVerifyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {
    @POST("api/app/registration")
    suspend fun registerApp(@Body request: AppRegistrationRequest): Response<BaseResponse<AppRegistrationResponse>>

    @POST("api/auth/check-account")
    suspend fun checkAccount(@Body request: CheckAccountRequest): Response<BaseResponse<ResponseData>>

    @POST("api/auth/generate-otp")
    suspend fun generateOtp(@Body request: OtpGenerationRequest): Response<BaseResponse<OtpGenerateResponse>>

    @POST("api/auth/verify-otp")
    suspend fun verifyOtp(@Body request: OtpValidateRequest): Response<BaseResponse<OtpVerifyResponse>>
}