package com.suraksha.android.view_model

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.suraksha.android.SurakshaApplication
import com.suraksha.android.database.preferences.SurakshaSharedPreferance
import com.suraksha.android.model.error.UserErrors
import com.suraksha.android.repository.UserRepository
import com.suraksha.app.R
import com.suraksha.cloud.ApiState
import com.suraksha.cloud.model.APIError
import com.suraksha.cloud.model.request.LoginRequest
import com.suraksha.cloud.model.response.AppRegistrationResponse
import com.suraksha.cloud.model.response.auth.SurakshaUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
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

    private var _mUser = MutableLiveData(SurakshaUser())

    //  val mUser: LiveData<SurakshaUser> get() = _mUser
 
    val isSignInEnabled = ObservableBoolean(false)
    var buttonText = MutableLiveData<String>()

    var password: String = ""
        set(value) {
            field = value
            _mUser.value?.password = field
            isSignInEnabled.set(isValidUser())
            //  setErrorMessage(null)
        }

    init {

  //      doForgotPasswordClick()
        buttonText.value=application.getString(R.string.sign_in)
    }


    private fun isValidUser(): Boolean {

        val user = _mUser?.value
        if (user?.email?.trim().isNullOrEmpty()) {
            buttonText.postValue(application.getString(R.string.invalid_email))
            return false
        }
        if (user?.password?.trim().isNullOrEmpty()) {
            buttonText.postValue(application.getString(R.string.invalid_password))
            buttonText.value=(application.getString(R.string.invalid_password))
            return false
        }
        buttonText.postValue(application.getString(R.string.sign_in))
        return true
    }

    var email: String = ""
        set(value) {
            field = value
            _mUser.value?.email = field
            isSignInEnabled.set(isValidUser())
            //setErrorMessage(null)
        }


    private fun doLogin() {
        if (true) {//todo need validation
            //    mobileNumber = countryCode + mobileNumberWithoutCode.value
            val appId = getAppId()


            if (appId <= 0) {

                doAppRegistration()

                return
            }
            val request = LoginRequest()
            request.userEmail=email
            request.userPassword=password
            request.userType=3
            request.appId=appId


            viewModelScope.launch {

                userRepository.login(request)

                    .catch {
                         apiState.value =
                            ApiState.error(APIError(-1, it.localizedMessage))
                    }

                    .collect {

                        when (it.status) {
                            ApiState.Status.SUCCESS -> {

                                if(it.data is SurakshaUser) {
                                    val user=it.data as SurakshaUser
                                    _mUser.value = user
                                    saveUserData(user)
                                    apiState.value = ApiState.success(_mUser)
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


        } else {
            //      userLiveData?.postMobileValidationFailed()
        }
    }

    private fun doAppRegistration() {
        viewModelScope.launch {
               val appRegisterViewModel = AppRegisterViewModel(application, userRepository)
            appRegisterViewModel.registerApp()

            appRegisterViewModel. apiState.collect {
                when (it.status) {
                    ApiState.Status.SUCCESS -> {

                        val appId = if(it?.data is AppRegistrationResponse) {
                            (it?.data as AppRegistrationResponse)?.appId as Int
                        } else 0

                        if (appId <= 0) {
                             apiState.value =
                                ApiState.error(APIError(-100, "App registration failed"))
                        } else {

                            doLogin()
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

    fun isUserLoggedIn(): Boolean {


        return !getToken().isNullOrEmpty()
    }

    fun doLogout(){
        clearToken()
    }

    private fun clearToken() {
        val pref= SurakshaSharedPreferance(mContext)
        pref.clear()

    }


    fun generateOtp(errorModel: UserErrors?) {

    }

    fun verifyOtp(errorModel: UserErrors) {

    }


    private fun doSignUp() {

        email="seethapharma@gmail.com"
        password="Qwe@123456"
    }

    fun doFacebookSignup() {

    }

    fun doGoogleSignUp() {

    }

    fun doFacebookLogin() {

    }

    fun doGoogleLogin() {

    }

    fun doForgotPasswordClick() {

        email="lab@healthbuss.in"
        password="Qwe@123456"
    }

    fun doSignInClick() {

        doLogin()
    }

    fun doSignUpClick() {
 doSignUp()
    }



}