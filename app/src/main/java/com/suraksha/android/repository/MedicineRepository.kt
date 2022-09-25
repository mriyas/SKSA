package com.suraksha.android.repository

import com.suraksha.cloud.ApiState
import com.suraksha.cloud.datasource.MedicineDataSource
import com.suraksha.cloud.model.response.lab.CreateTestsResponse
import com.suraksha.cloud.model.LabTest
import com.suraksha.cloud.model.Medicines
import com.suraksha.cloud.model.response.lab.MedicineListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MedicineRepository @Inject constructor(private val medicineDataSource: MedicineDataSource) {

    suspend fun getMedicines(index: Int, pageSize: Int): Flow<ApiState<MedicineListResponse>> {

        return flow {
            emit(ApiState.loading())
            val result = medicineDataSource.getMedicines(index, pageSize)

            //Cache to database if response is successful
            if (result.status == ApiState.Status.SUCCESS) {
                //do db operations
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMedicineDetails(id: String): Flow<ApiState<Medicines>> {

        return flow {
            emit(ApiState.loading())
            val result = medicineDataSource.getMedicineDetails(id)

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
            val result = if(isEdit)medicineDataSource.editTest(request) else medicineDataSource.createTest(request)

            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}