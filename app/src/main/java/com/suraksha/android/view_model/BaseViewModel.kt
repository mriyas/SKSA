package com.suraksha.android.view_model

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.suraksha.android.database.datastore.DataStoreManager
import com.suraksha.android.model.error.UserErrors
import com.suraksha.cloud.model.response.AppRegistrationResponse
import com.suraksha.cloud.model.response.auth.UserDataResponse
import com.suraksha.cloud.model.response.auth.UserType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by Anees on 04-04-2022
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    open var mContext: Context? = null
    var mDataStoreManager: DataStoreManager? = null
    val isLoggedOut= MutableLiveData<Boolean>(false)
    val errorModel= UserErrors(null)

    val TAG=javaClass.simpleName

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

    fun getAppid(): Flow<Int?>? {
        return mDataStoreManager?.getAppId

    }


   fun saveUserName(userName: String){
        viewModelScope.launch() {
            mDataStoreManager?.saveUserName(userName)
        }
    }

    fun getUserName1(): Flow<String?>? {
        return mDataStoreManager?.getUserName

    }

    fun saveUserData(response: UserDataResponse){
        viewModelScope.launch() {
            mDataStoreManager?.saveUserData(response)
        }
    }

    fun getUserData(): Flow<UserDataResponse?>? {
        return mDataStoreManager?.getUserData()
    }


    fun getUserType():Int
    {
        var userType = UserType.NOT_DEFINED
        runBlocking {
            viewModelScope.launch {
                getUserData()?.catch { e ->
                    e.printStackTrace()
                }?.collect {
                    userType = it?.userType?:UserType.NOT_DEFINED
                }
            }
        }
        if(userType==UserType.NOT_DEFINED)
            logout()
        return userType
    }

    fun getToken(): String? {
        var token = null as String?
        runBlocking {
            viewModelScope.launch {
                getUserData()?.catch { e ->
                    e.printStackTrace()
                }?.collect {
                    token = it?.token
                }
            }
        }
      /*  if(token?.isNullOrEmpty()==true)
            logout()*/
        return token

    }

    fun getRefreshToken(): String? {
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


    fun getUserId(): Long? {
        var userId = 0L as Long?
        runBlocking {
            viewModelScope.launch {
                getUserData()?.catch { e ->
                    e.printStackTrace()
                }?.collect {
                    userId = it?.userId
                }
            }
        }
        return userId

    }

    fun getUserName(): String? {
        var userName= null as String?
        runBlocking {
            viewModelScope.launch {
                getUserData()?.catch { e ->
                    e.printStackTrace()
                }?.collect {
                    userName = it?.userName
                }
            }
        }
        return userName

    }
    fun getUserProfileUrl(): String? {
        var userprofileUrl= null as String?
        runBlocking {
            viewModelScope.launch {
                getUserData()?.catch { e ->
                    e.printStackTrace()
                }?.collect {
                    userprofileUrl = it?.profileUrl
                }
            }
        }
        return userprofileUrl

    }








}