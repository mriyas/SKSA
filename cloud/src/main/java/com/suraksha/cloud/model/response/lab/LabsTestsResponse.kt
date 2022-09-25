package com.suraksha.cloud.model.response.lab

import com.google.gson.annotations.SerializedName
import com.suraksha.cloud.model.Bookings
import com.suraksha.cloud.model.LabTest
import com.suraksha.cloud.model.Medicines

data class LabsTestsResponse(
    @SerializedName("list") val tests:List<LabTest>
)
data class LabsTestDetailsResponse(
    @SerializedName("list") val tests:List<LabTest>
)
data class BookingListResponse(
    @SerializedName("list") val bookings:List<Bookings>
)

data class MedicineListResponse(
    @SerializedName("list") val bookings:List<Medicines>
)

data class CreateTestsResponse(
    @SerializedName("testId") val testId:Long
)
data class CreateTimeSlotResponse(
    @SerializedName("slotId") val slotId:Long
)
