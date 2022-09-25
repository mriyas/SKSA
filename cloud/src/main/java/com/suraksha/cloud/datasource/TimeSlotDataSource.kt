package com.suraksha.cloud.datasource

import com.suraksha.cloud.model.request.CreateTimeSlotRequest
import com.suraksha.cloud.services.TimeSlotApiService
import javax.inject.Inject

class TimeSlotDataSource @Inject constructor(
    private val timeSlotApiService: TimeSlotApiService
): BaseDataSource() {

    suspend fun createTimeSlot(test: CreateTimeSlotRequest) = getResult {
        timeSlotApiService.createSlot(test)
    }

}