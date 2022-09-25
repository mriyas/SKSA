package com.suraksha.cloud.model.request

import com.google.gson.annotations.SerializedName
import com.suraksha.cloud.model.response.TimeSlot

class CreateTimeSlotRequest {

     @SerializedName("Request")
     var timeSlots: List<TimeSlot> = emptyList()

 }