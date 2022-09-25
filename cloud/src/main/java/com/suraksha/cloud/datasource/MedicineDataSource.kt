package com.suraksha.cloud.datasource

import com.suraksha.cloud.model.LabTest
import com.suraksha.cloud.services.MedicineApiService
import javax.inject.Inject

class MedicineDataSource @Inject constructor(
    private val medicineApiService: MedicineApiService
): BaseDataSource() {
    suspend fun getMedicines(index:Int,pageSize:Int) = getResult {
        medicineApiService.getMedicines(index, pageSize)
    }
    suspend fun getMedicineDetails(id:String) = getResult {
        medicineApiService.getMedicineDetails(id)
    }
    suspend fun createTest(test: LabTest) = getResult {
        medicineApiService.createTest(test)
    }
    suspend fun editTest(test: LabTest) = getResult {
        medicineApiService.editTest(test,test.testId)
    }
}