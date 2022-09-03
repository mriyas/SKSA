package com.suraksha.android.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.suraksha.android.SurakshaApplication
import com.suraksha.android.repository.UserRepository
import com.suraksha.android.view.utility.Validator
import com.suraksha.android.model.SurakshaUser
import com.suraksha.android.model.error.UserErrors
import com.suraksha.android.model.UserLiveData
import com.suraksha.cloud.Resource
import com.suraksha.cloud.model.request.CheckAccountRequest
import com.suraksha.cloud.model.request.OtpGenerationRequest
import com.suraksha.cloud.model.request.OtpValidateRequest
import com.suraksha.cloud.model.response.auth.OtpVerifyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * Created by Anees on 28-03-2022
 */
@HiltViewModel
class UserViewModel @Inject constructor(
    private val application: SurakshaApplication,
    private val userRepository: UserRepository
) : BaseViewModel(application) {
    val mUser = MutableLiveData<SurakshaUser>()


    init {
        initUser()
    }

    private fun initUser(user:SurakshaUser?=null) {
        mUser.value = user ?: SurakshaUser()
    }


    fun checkAccount(countryCode: String, errorModel: UserErrors) {
        if (true) {//todo need validation
        //    mobileNumber = countryCode + mobileNumberWithoutCode.value
            val request = CheckAccountRequest()
            val appId: Int?
            runBlocking(Dispatchers.IO) {
                appId = getAppid()?.first()?.toInt()
            }
            request.number = mUser.value?.phoneNumber.toString()
            if (appId != null) {
                request.appId = appId
            }
            viewModelScope.launch {
                userRepository.checkAccount(request).collect {

                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                        //    errorModel.uiUpdate = false
                          //  userLiveData?.postCheckAccountSuccess()
                            Log.i("App reg", "Success")
                        }
                        Resource.Status.ERROR -> {
                          //  errorModel.uiUpdate = false
                          //  userLiveData?.postCheckAccountFailed()
                            Log.i("App reg", "Error")
                        }
                        Resource.Status.LOADING -> {
                          //  errorModel.uiUpdate = true
                            Log.i("App reg", "Loading")
                        }
                    }


                }
            }

        } else {
      //      userLiveData?.postMobileValidationFailed()
        }
    }

    fun generateOtp(errorModel: UserErrors?) {

    }

    fun verifyOtp(errorModel: UserErrors) {

    }

    private fun checkAlreadyRegisteredUser(
        it: Resource<OtpVerifyResponse>,
        errorModel: UserErrors
    ) {
        if (!it.data?.token.isNullOrEmpty()) {
            // existing user
                runBlocking {
// save user data
                    it.data?.let { it1 -> saveUserData(it1) }

// for getting userdata
                    viewModelScope.launch {
                        getUserData()?.catch { e ->
                            e.printStackTrace()
                        }?.collect {
                            val userId = it?.userId
                            val userType = it?.userType
                            val token = it?.token
                        }
                    }
                }




        } else {

        }

    }



    fun doSignUp(){

    }
    fun doFacebookSignup(){

    }

    fun doGoogleSignUp(){

    }

    fun doFacebookLogin(){

    }

    fun doGoogleLogin(){

    }

    fun doForgotPasswordClick(){

    }

    fun doSignInClick(){

    }

    fun doSignUpClick(){

    }

}