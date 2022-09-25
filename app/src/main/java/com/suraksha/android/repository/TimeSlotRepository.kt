package com.suraksha.android.repository

import com.suraksha.cloud.ApiState
import com.suraksha.cloud.datasource.TimeSlotDataSource
import com.suraksha.cloud.model.response.lab.CreateTestsResponse
import com.suraksha.cloud.model.request.CreateTimeSlotRequest
import com.suraksha.cloud.model.response.TimeSlot
import com.suraksha.cloud.model.response.lab.CreateTimeSlotResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TimeSlotRepository @Inject constructor(private val timeSlotDataSource: TimeSlotDataSource) {




    suspend fun createTimeSlot(timeslots: List<TimeSlot>, isEdit:Boolean=false): Flow<ApiState<CreateTimeSlotResponse>> {

        return flow {
            emit(ApiState.loading())
            val timeSlotRequest=CreateTimeSlotRequest()
            timeSlotRequest.timeSlots=timeslots
            val result = timeSlotDataSource.createTimeSlot(timeSlotRequest)

            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}