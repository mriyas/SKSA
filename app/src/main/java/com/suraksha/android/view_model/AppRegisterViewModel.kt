package com.suraksha.android.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.suraksha.android.SurakshaApplication
import com.suraksha.android.repository.UserRepository
import com.suraksha.cloud.Resource
import com.suraksha.cloud.model.request.AppRegistrationRequest
import com.suraksha.cloud.model.response.AppRegistrationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

private const val  APP_NOT_REGISTERED=-1

@HiltViewModel
class AppRegisterViewModel @Inject constructor(
    application: SurakshaApplication,
    private val userRepository: UserRepository
) : BaseViewModel(application) {

    private val _appRegistrationResponse = MutableLiveData<Resource<AppRegistrationResponse>>()
    //  val appRegistrationResponse = LiveData<Resource<AppRegistrationResponse>>()
    var appRegistrationResponses: MutableLiveData<AppRegistrationResponse> = MutableLiveData()
    var context: Context? = null


    init {
        context = application
    }


    fun registerApp(request: AppRegistrationRequest) {


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                //  Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d(TAG, "firebase token is $token")
            request.deviceToken = token
            viewModelScope.launch {
                userRepository.registerApp(request).collect {
                    _appRegistrationResponse.value = it
                    it.data?.let { it1 -> saveAppId(it1) }

                }
            }
            // Log and toast
            // val msg = getString(R.string.msg_token_fmt, token)

            // Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })


    }

    fun getAppId(): Int {
        val appId: Int?
        runBlocking(Dispatchers.IO) {
            appId = getAppid()?.first()?.toInt()
        }
        return appId?:APP_NOT_REGISTERED
    }


}