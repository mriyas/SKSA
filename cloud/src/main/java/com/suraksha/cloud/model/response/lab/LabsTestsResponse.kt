package com.suraksha.cloud.model.response.lab

import com.google.gson.annotations.SerializedName
import com.suraksha.cloud.model.response.ResponseData

data class LabsTestsResponse(
    @SerializedName("list") val tests:List<LabTest>
)

data class CreateTestsResponse(
    @SerializedName("testId") val testId:Long
)