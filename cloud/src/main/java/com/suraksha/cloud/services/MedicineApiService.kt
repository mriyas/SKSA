package com.suraksha.cloud.services

import com.suraksha.cloud.model.response.*
import com.suraksha.cloud.model.response.lab.CreateTestsResponse
import com.suraksha.cloud.model.LabTest
import com.suraksha.cloud.model.Medicines
import com.suraksha.cloud.model.response.lab.BookingListResponse
import com.suraksha.cloud.model.response.lab.LabsTestsResponse
import com.suraksha.cloud.model.response.lab.MedicineListResponse
import retrofit2.Response
import retrofit2.http.*

interface MedicineApiService {
    @GET("suraksha/public/medicine")
    suspend fun getMedicines(
        @Query("index") index: Int,
        @Query("pageSize") pageSize: Int
    ):Response<BaseResponse<MedicineListResponse>>


    @GET("suraksha/public/medicine/{id}")
    suspend fun getMedicineDetails(
        @Path("id") testId:String,
    ): Response<BaseResponse<Medicines>>

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