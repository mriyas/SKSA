package com.suraksha.cloud.datasource

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.suraksha.cloud.ApiState
import com.suraksha.cloud.Utils
import com.suraksha.cloud.model.APIError
import com.suraksha.cloud.model.APIError.ErrorCodes.API_ERROR
import com.suraksha.cloud.model.APIError.ErrorCodes.EXCEPTION
import com.suraksha.cloud.model.response.BaseResponse
import com.suraksha.cloud.model.response.ResponseData
import retrofit2.Response

abstract class BaseDataSource {

    @RequiresApi(Build.VERSION_CODES.M)
    protected suspend fun <T : Any> getResult(call: suspend () -> Response<BaseResponse<T>>): ApiState<T> {
        try {
            if(!Utils.hasInternetConnection()){
                return error(APIError.ErrorCodes.NO_INTERNET, APIError.ErrorMessages.NO_INTERNET)
            }
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()?.response
                if (body != null) {
                    return if(body.isValidResponse()) {
                        ApiState.success(body.data)
                    }else{
                        var status=body?.status
                        var message =status?.statusMessage?: "Api failed"

                        error(body.status?.statusCode ?: API_ERROR, message)
                    }
                }
            }
            return error(response.code(),response.message())
        } catch (e: Exception) {
            return error(EXCEPTION, APIError.ErrorMessages.EXCEPTION)
        }
    }

    private fun <T> error(errorCode: Int, message: String): ApiState<T> {
        Log.e("remoteDataSource", message)
        val error = APIError(errorCode, message)
        return ApiState.error(error)
    }

}