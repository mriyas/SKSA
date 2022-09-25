package com.suraksha.android.view_model

import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.suraksha.android.database.datastore.DataStoreManager
import com.suraksha.android.database.preferences.SurakshaSharedPreferance
import com.suraksha.android.model.error.UserErrors
import com.suraksha.cloud.ApiState
import com.suraksha.cloud.model.response.AppRegistrationResponse
import com.suraksha.cloud.model.response.auth.SurakshaUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by Anees on 04-04-2022
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    open var mContext: Context? = null
    lateinit var mDataStoreManager: DataStoreManager
    val isLoggedOut= MutableLiveData<Boolean>(false)
    val errorModel= UserErrors(null)

    val TAG=javaClass.simpleName
    val apiState = MutableStateFlow(ApiState<Any>(ApiState.Status.IDLE))
    init {
        mContext = application
        mDataStoreManager = DataStoreManager(application)
    }

    fun logout()
    {
        viewModelScope.launch() {
            mDataStoreManager?.logout()
        }
        isLoggedOut.postValue(true)
    }

    fun saveAppId(appRegistrationResponse: AppRegistrationResponse){
        viewModelScope.launch() {
            mDataStoreManager?.saveAppId(appRegistrationResponse.appId)
        }
    }

    fun getAppid(): Flow<Int?> {
        return mDataStoreManager.getAppId

    }


   fun saveUserName(userName: String){
        viewModelScope.launch() {
            mDataStoreManager?.saveUserName(userName)
        }
    }

    fun getUserName1(): Flow<String?>? {
        return mDataStoreManager?.getUserName

    }

    fun saveUserData(response: SurakshaUser){
        viewModelScope.launch() {
            mDataStoreManager?.saveUserData(response)
            val pref=SurakshaSharedPreferance(mContext)
            pref.setStringPrefValue(SurakshaSharedPreferance.Constants.MY_WEB_SERVICE_TOKEN,response.token)
            pref.setLongPrefValue(SurakshaSharedPreferance.Constants.APP_ID,getAppId().toLong())
            pref.setIntegerPrefValue(SurakshaSharedPreferance.Constants.SERVICE,response.service)
            pref.setIntegerPrefValue(SurakshaSharedPreferance.Constants.USER_TYPE,response.userType)
        }
    }

    fun getUserData(): Flow<SurakshaUser?>? {
        return mDataStoreManager?.getUserData()
    }



    fun getLoggedInService():Pair<Int,Int>{
        val pref=SurakshaSharedPreferance(mContext)
        var service=pref.getIntegerPrefValue(SurakshaSharedPreferance.Constants.SERVICE)
        var type=pref.getIntegerPrefValue(SurakshaSharedPreferance.Constants.USER_TYPE)


        return Pair(service,type)
    }



    fun getToken(): String? {
          val pref=SurakshaSharedPreferance(mContext)
           var token=pref.getStringPrefValue(SurakshaSharedPreferance.Constants.MY_WEB_SERVICE_TOKEN)

        return token

    }

  /*  fun getRefreshToken(): String? {
        var refreshToken = null as String?
        runBlocking {
            viewModelScope.launch {
                getUserData()?.catch { e ->
                    e.printStackTrace()
                }?.collect {
                    refreshToken = it?.refreshToken
                }
            }
        }
         if(refreshToken?.isNullOrEmpty()==true)
              logout()
        return refreshToken
    }
*/

    fun getLoggedInUserId(): Long {
        var userId = 0L
        runBlocking {
            viewModelScope.launch {
                getUserData()?.catch { e ->
                    e.printStackTrace()
                }?.collect {
                    userId = it?.userId?:0
                }
            }
        }
        return userId

    }


    fun getAppId(): Int {
        var appId = 0
        runBlocking(Dispatchers.IO) {
            appId = getAppid()?.first()?.toInt() ?: 0
        }

        return appId

    }


    fun isApiCallInProgress(): Boolean {
        return apiState.value.status == ApiState.Status.LOADING
    }

    fun isApiError(): Boolean {
        return apiState.value.status == ApiState.Status.ERROR
    }



}