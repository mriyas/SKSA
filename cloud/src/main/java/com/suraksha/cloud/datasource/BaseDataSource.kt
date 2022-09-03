package com.suraksha.cloud.datasource

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.suraksha.cloud.Resource
import com.suraksha.cloud.Utils
import com.suraksha.cloud.model.CloudError
import com.suraksha.cloud.model.CloudError.ErrorCodes.API_ERROR
import com.suraksha.cloud.model.CloudError.ErrorCodes.EXCEPTION
import com.suraksha.cloud.model.response.BaseResponse
import com.suraksha.cloud.model.response.ResponseData
import retrofit2.Response

abstract class BaseDataSource {

    @RequiresApi(Build.VERSION_CODES.M)
    protected suspend fun <T : Any> getResult(call: suspend () -> Response<BaseResponse<T>>): Resource<T> {
        try {
            if(!Utils.hasInternetConnection()){
                return error(CloudError.ErrorCodes.NO_INTERNET, CloudError.ErrorMessages.NO_INTERNET)
            }
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return if(body.isValidResponse()) {
                        Resource.success(body.data)
                    }else{
                        var message = "Api failed"
                        if(body.data is ResponseData){
                            message = (body.data as ResponseData).responseDataMsg
                        }
                        error(body.status?.statusCode ?: API_ERROR, message)
                    }
                }
            }
            return error(response.code(),response.message())
        } catch (e: Exception) {
            return error(EXCEPTION, CloudError.ErrorMessages.EXCEPTION)
        }
    }

    private fun <T> error(errorCode: Int, message: String): Resource<T> {
        Log.e("remoteDataSource", message)
        val error = CloudError(errorCode, message)
        return Resource.error(error)
    }

}