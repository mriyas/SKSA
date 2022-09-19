package com.suraksha.cloud.services

import com.suraksha.cloud.model.request.AppRegistrationRequest
import com.suraksha.cloud.model.request.LoginRequest
import com.suraksha.cloud.model.request.OtpGenerationRequest
import com.suraksha.cloud.model.request.OtpValidateRequest
import com.suraksha.cloud.model.response.*
import com.suraksha.cloud.model.response.auth.OtpGenerateResponse
import com.suraksha.cloud.model.response.auth.OtpVerifyResponse
import com.suraksha.cloud.model.response.auth.SurakshaUser
import com.suraksha.cloud.model.response.lab.CreateTestsResponse
import com.suraksha.cloud.model.response.lab.LabTest
import com.suraksha.cloud.model.response.lab.LabsTestsResponse
import retrofit2.Response
import retrofit2.http.*

interface LabsApiService {
    @GET("suraksha/public/test")
    suspend fun getTests(
        @Query("index") index: Int,
        @Query("pageSize") pageSize: Int
    ): Response<BaseResponse<LabsTestsResponse>>

    @POST("suraksha/public/test")
    suspend fun createTest(
        @Body test: LabTest
    ): Response<BaseResponse<CreateTestsResponse>>

    @PUT("suraksha/public/test/{id}")
    suspend fun editTest(
        @Body test: LabTest,
        @Path("id") testId:String,
    ): Response<BaseResponse<CreateTestsResponse>>
}