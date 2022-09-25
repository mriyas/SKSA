package com.suraksha.cloud.datasource

import com.suraksha.cloud.model.LabTest
import com.suraksha.cloud.services.BookingApiService
import javax.inject.Inject

class BookingDataSource @Inject constructor(
    private val labsApiService: BookingApiService
): BaseDataSource() {
    suspend fun getBookings(index:Int,pageSize:Int) = getResult {
        labsApiService.getBookings(index, pageSize)
    }
    suspend fun getBookingDetails(id:String) = getResult {
        labsApiService.getBookingDetails(id)
    }
    suspend fun createTest(test: LabTest) = getResult {
        labsApiService.createTest(test)
    }
    suspend fun editTest(test: LabTest) = getResult {
        labsApiService.editTest(test,test.testId)
    }
}