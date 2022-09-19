package com.suraksha.android.repository

import com.suraksha.cloud.ApiState
import com.suraksha.cloud.datasource.UserDataSource
import com.suraksha.cloud.model.request.AppRegistrationRequest
import com.suraksha.cloud.model.request.LoginRequest
import com.suraksha.cloud.model.request.OtpGenerationRequest
import com.suraksha.cloud.model.request.OtpValidateRequest
import com.suraksha.cloud.model.response.AppRegistrationResponse
import com.suraksha.cloud.model.response.auth.OtpGenerateResponse
import com.suraksha.cloud.model.response.auth.OtpVerifyResponse
import com.suraksha.cloud.model.response.auth.SurakshaUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository@Inject constructor(private val userDataSource: UserDataSource) {

    suspend fun registerApp(request: AppRegistrationRequest): Flow<ApiState<AppRegistrationResponse>> {

        return flow {
            emit(ApiState.loading())
            val result = userDataSource.registerApp(request)

            //Cache to database if response is successful
            if (result.status == ApiState.Status.SUCCESS) {
                //do db operations
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


    suspend fun login(request: LoginRequest): Flow<ApiState<SurakshaUser>> {

        return flow {
            emit(ApiState.loading())
            val result = userDataSource.login(request)

            emit(result)
        }.flowOn(Dispatchers.IO)
    }


    suspend fun generateOtp(request: OtpGenerationRequest): Flow<ApiState<OtpGenerateResponse>> {

        return flow {
            emit(ApiState.loading())
            val result = userDataSource.generateOtp(request)

            //Cache to database if response is successful
            if (result.status == ApiState.Status.SUCCESS) {
                //do db operations
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


    suspend fun verifyOtp(request: OtpValidateRequest): Flow<ApiState<OtpVerifyResponse>> {

        return flow {
            emit(ApiState.loading())
            val result = userDataSource.verifyOtp(request)

            //Cache to database if response is successful
            if (result.status == ApiState.Status.SUCCESS) {
                //do db operations
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


}