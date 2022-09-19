package com.suraksha.cloud.datasource

import com.suraksha.cloud.model.request.LoginRequest
import com.suraksha.cloud.model.request.OtpGenerationRequest
import com.suraksha.cloud.model.request.OtpValidateRequest
import com.suraksha.cloud.model.response.lab.LabTest
import com.suraksha.cloud.services.LabsApiService
import javax.inject.Inject

class LabsDataSource @Inject constructor(
    private val labsApiService: LabsApiService
): BaseDataSource() {
    suspend fun getTests(index:Int,pageSize:Int) = getResult {
        labsApiService.getTests(index, pageSize)
    }
    suspend fun createTest(test:LabTest) = getResult {
        labsApiService.createTest(test)
    }
    suspend fun editTest(test:LabTest) = getResult {
        labsApiService.editTest(test,test.testId)
    }
}