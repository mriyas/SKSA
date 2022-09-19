package com.suraksha.cloud.services

import com.suraksha.cloud.model.request.AppRegistrationRequest
import com.suraksha.cloud.model.request.LoginRequest
import com.suraksha.cloud.model.request.OtpGenerationRequest
import com.suraksha.cloud.model.request.OtpValidateRequest
import com.suraksha.cloud.model.response.*
import com.suraksha.cloud.model.response.auth.OtpGenerateResponse
import com.suraksha.cloud.model.response.auth.OtpVerifyResponse
import com.suraksha.cloud.model.response.auth.SurakshaUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {
    @POST("suraksha/public/application")
    suspend fun registerApp(@Body request: AppRegistrationRequest): Response<BaseResponse<AppRegistrationResponse>>

    @POST("suraksha/public/login")
    suspend fun login(@Body request: LoginRequest): Response<BaseResponse<SurakshaUser>>

    @POST("api/auth/generate-otp")
    suspend fun generateOtp(@Body request: OtpGenerationRequest): Response<BaseResponse<OtpGenerateResponse>>

    @POST("api/auth/verify-otp")
    suspend fun verifyOtp(@Body request: OtpValidateRequest): Response<BaseResponse<OtpVerifyResponse>>
}