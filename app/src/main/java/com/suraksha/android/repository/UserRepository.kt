package com.suraksha.android.repository

import com.suraksha.cloud.Resource
import com.suraksha.cloud.datasource.UserDataSource
import com.suraksha.cloud.model.request.AppRegistrationRequest
import com.suraksha.cloud.model.request.CheckAccountRequest
import com.suraksha.cloud.model.request.OtpGenerationRequest
import com.suraksha.cloud.model.request.OtpValidateRequest
import com.suraksha.cloud.model.response.AppRegistrationResponse
import com.suraksha.cloud.model.response.auth.OtpGenerateResponse
import com.suraksha.cloud.model.response.auth.OtpVerifyResponse
import com.suraksha.cloud.model.response.ResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository@Inject constructor(private val userDataSource: UserDataSource) {

    suspend fun registerApp(request: AppRegistrationRequest): Flow<Resource<AppRegistrationResponse>> {

        return flow {
            emit(Resource.loading())
            val result = userDataSource.registerApp(request)

            //Cache to database if response is successful
            if (result.status == Resource.Status.SUCCESS) {
                //do db operations
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


    suspend fun checkAccount(request: CheckAccountRequest): Flow<Resource<ResponseData>> {

        return flow {
            emit(Resource.loading())
            val result = userDataSource.checkAccount(request)

            //Cache to database if response is successful
            if (result.status == Resource.Status.SUCCESS) {
                //do db operations
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


    suspend fun generateOtp(request: OtpGenerationRequest): Flow<Resource<OtpGenerateResponse>> {

        return flow {
            emit(Resource.loading())
            val result = userDataSource.generateOtp(request)

            //Cache to database if response is successful
            if (result.status == Resource.Status.SUCCESS) {
                //do db operations
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


    suspend fun verifyOtp(request: OtpValidateRequest): Flow<Resource<OtpVerifyResponse>> {

        return flow {
            emit(Resource.loading())
            val result = userDataSource.verifyOtp(request)

            //Cache to database if response is successful
            if (result.status == Resource.Status.SUCCESS) {
                //do db operations
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


}