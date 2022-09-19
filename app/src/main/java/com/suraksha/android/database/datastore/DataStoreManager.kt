package com.suraksha.android.database.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.suraksha.cloud.model.response.auth.SurakshaUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 */
const val DataStore_NAME = "SURAKSHA_DATASTORE"
class DataStoreManager(val context: Context) {

    companion object {
        val Context.datastore : DataStore<androidx.datastore.preferences.core.Preferences> by  preferencesDataStore(name = DataStore_NAME)
        val APP_ID = intPreferencesKey("APP_ID")
        val TOKEN = stringPreferencesKey("TOKEN")
        val REFRESH_TOKEN = stringPreferencesKey("REFRESH_TOKEN")
        val USER_TYPE = intPreferencesKey("USER_TYPE")
        val USER_ID = longPreferencesKey("USER_ID")
        val USER_NAME = stringPreferencesKey("USER_NAME")
        val USER_PROFILE_URL = stringPreferencesKey("USER_PROFILE_URL")
        val PHONE_NUMBER = stringPreferencesKey("PHONE_NUMBER")
        val ADDRESS = stringPreferencesKey("ADDRESS")

    }

suspend fun logout() {
    context.datastore.edit {
        it.clear()
    }
}

    suspend fun saveAppId(appId: Int) {
        context.datastore.edit { preferences->
            preferences[APP_ID] = appId
        }
    }
    val getAppId: Flow<Int>
        get() = context.datastore.data.map { preferences ->
            preferences[APP_ID]?:0
        }


    suspend fun saveUserName(userName: String) {
        context.datastore.edit { preferences->
            preferences[USER_NAME] = userName
        }
    }

    val getUserName: Flow<String?>
        get() = context.datastore.data.map { preferences ->
            preferences[USER_NAME]
        }

    suspend fun saveUserData(response: SurakshaUser) {
        context.datastore.edit {
            it[USER_ID] = response.userId
            it[USER_TYPE] =response.userType
            it[TOKEN] = response.token
         //   it[REFRESH_TOKEN] = response.refreshToken
            it[USER_NAME] = response.userName
          //  it[USER_PROFILE_URL] = response.profileUrl
        }
    }


    fun getUserData() = context.datastore.data.map {
        val user=SurakshaUser()
        user.userId = it[USER_ID]?:0L
        user.userType = it[USER_TYPE]?:0
        user.userName = it[USER_NAME]?:""
       // profileUrl = it[USER_PROFILE_URL]?:"",
        user.token = it[TOKEN]?:""
       // refreshToken = it[REFRESH_TOKEN]?:"",

        user
    }



}