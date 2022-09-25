package com.suraksha.android.repository

import com.suraksha.cloud.ApiState
import com.suraksha.cloud.datasource.BookingDataSource
import com.suraksha.cloud.model.response.lab.CreateTestsResponse
import com.suraksha.cloud.model.LabTest
import com.suraksha.cloud.model.response.lab.BookingListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BookingRepository @Inject constructor(private val bookingDataSource: BookingDataSource) {

    suspend fun getBookings(index: Int, pageSize: Int): Flow<ApiState<BookingListResponse>> {

        return flow {
            emit(ApiState.loading())
            val result = bookingDataSource.getBookings(index, pageSize)

            //Cache to database if response is successful
            if (result.status == ApiState.Status.SUCCESS) {
                //do db operations
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getBookingDetails(id: String): Flow<ApiState<LabTest>> {

        return flow {
            emit(ApiState.loading())
            val result = bookingDataSource.getBookingDetails(id)

            //Cache to database if response is successful
            if (result.status == ApiState.Status.SUCCESS) {
                //do db operations
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


    suspend fun createTest(request: LabTest, isEdit:Boolean=false): Flow<ApiState<CreateTestsResponse>> {

        return flow {
            emit(ApiState.loading())
            val result = if(isEdit)bookingDataSource.editTest(request) else bookingDataSource.createTest(request)

            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}