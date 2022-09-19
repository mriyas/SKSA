package com.suraksha.android.view_model

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.suraksha.android.SurakshaApplication
import com.suraksha.android.repository.UserRepository
import com.suraksha.app.BuildConfig
import com.suraksha.cloud.ApiState
import com.suraksha.cloud.model.APIError
import com.suraksha.cloud.model.request.AppRegistrationRequest
import com.suraksha.cloud.model.response.AppRegistrationResponse
import com.suraksha.cloud.model.response.auth.SurakshaUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

private const val APP_NOT_REGISTERED = -1

@HiltViewModel
class AppRegisterViewModel @Inject constructor(
    private val application: SurakshaApplication,
    private val userRepository: UserRepository
) : BaseViewModel(application) {


    private var _appRegistrationResponse = MutableLiveData(AppRegistrationResponse())
    val appRegistrationResponses: LiveData<AppRegistrationResponse> get() = _appRegistrationResponse
    var context: Context? = null


    init {
        context = application
    }


    fun registerApp() {
        val appRegistrationRequest = AppRegistrationRequest()

        val deviceId: String = Settings.Secure.getString(
            application.contentResolver,
            Settings.Secure.ANDROID_ID
        )
        val manufacturer: String = Build.MANUFACTURER
        val model: String = Build.MODEL
        val version: Int = Build.VERSION.SDK_INT
        val versionRelease: String = Build.VERSION.RELEASE
        appRegistrationRequest.deviceId = deviceId
        appRegistrationRequest.clientVersion = BuildConfig.VERSION_NAME
        appRegistrationRequest.modelInfo = "${manufacturer}_${model}".replace(" ", "_")
        appRegistrationRequest.osInfo = "Android_${version}_${versionRelease}".replace(" ", "_")


        /*FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
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
                    _appRegistrationResponse.value = it?.data
                    it.data?.let { it1 -> saveAppId(it1) }

                }
            }
            // Log and toast
            // val msg = getString(R.string.msg_token_fmt, token)

            // Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })

*/
        viewModelScope.launch {
            userRepository.registerApp(appRegistrationRequest).catch {
                 apiState.value =
                    ApiState.error(APIError(-1, it.localizedMessage))
            }.collect {
                when (it.status) {
                    ApiState.Status.SUCCESS -> {
                        if(it?.data is AppRegistrationResponse) {
                            _appRegistrationResponse.value = it?.data as AppRegistrationResponse
                            it.data?.let { it1 -> saveAppId(it1 as AppRegistrationResponse) }
                            Thread.sleep(1000)
                            apiState.value = ApiState.success(it?.data)
                        }
                    }
                    ApiState.Status.ERROR -> {
                         apiState.value =
                            ApiState.error(it.error)
                    }
                    ApiState.Status.LOADING -> {
                        //  errorModel.uiUpdate = true
                         apiState.value = ApiState.loading()
                    }
                    ApiState.Status.IDLE -> {
                         apiState.value = ApiState.idle()

                    }
                }
            }


        }
    }





}