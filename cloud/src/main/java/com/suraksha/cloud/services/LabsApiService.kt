package com.suraksha.cloud.services

import com.suraksha.cloud.model.response.*
import com.suraksha.cloud.model.response.lab.CreateTestsResponse
import com.suraksha.cloud.model.LabTest
import com.suraksha.cloud.model.response.lab.LabsTestsResponse
import retrofit2.Response
import retrofit2.http.*

interface LabsApiService {
    @GET("suraksha/public/test")
    suspend fun getTests(
        @Query("index") index: Int,
        @Query("pageSize") pageSize: Int
    ): Response<BaseResponse<LabsTestsResponse>>


    @GET("suraksha/public/test/{TEST_ID}")
    suspend fun getTestDetails(
        @Path("TEST_ID") id: String,
    ): Response<BaseResponse<LabTest>>

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