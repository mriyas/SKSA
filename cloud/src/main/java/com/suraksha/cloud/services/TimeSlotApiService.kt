package com.suraksha.cloud.services

import com.suraksha.cloud.model.request.CreateTimeSlotRequest
import com.suraksha.cloud.model.response.BaseResponse
import com.suraksha.cloud.model.response.lab.CreateTimeSlotResponse
import com.suraksha.cloud.model.response.lab.LabsTestsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TimeSlotApiService {
    @GET("suraksha/public/test")
    suspend fun getTests(
        @Query("index") index: Int,
        @Query("pageSize") pageSize: Int
    ): Response<BaseResponse<LabsTestsResponse>>


    @POST("suraksha/public/timeslot")
    suspend fun createSlot(
        @Body test: CreateTimeSlotRequest
    ): Response<BaseResponse<CreateTimeSlotResponse>>


}