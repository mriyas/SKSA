package com.suraksha.android.repository

import com.suraksha.cloud.ApiState
import com.suraksha.cloud.datasource.LabsDataSource
import com.suraksha.cloud.model.response.lab.CreateTestsResponse
import com.suraksha.cloud.model.LabTest
import com.suraksha.cloud.model.response.lab.LabsTestsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LabsRepository @Inject constructor(private val labsDataSource: LabsDataSource) {

    suspend fun getTests(index: Int, pageSize: Int): Flow<ApiState<LabsTestsResponse>> {

        return flow {
            emit(ApiState.loading())
            val result = labsDataSource.getTests(index, pageSize)

            //Cache to database if response is successful
            if (result.status == ApiState.Status.SUCCESS) {
                //do db operations
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTestDetails(id: String): Flow<ApiState<LabTest>> {

        return flow {
            emit(ApiState.loading())
            val result = labsDataSource.getTestDetails(id)

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
            val result = if(isEdit)labsDataSource.editTest(request) else labsDataSource.createTest(request)

            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}