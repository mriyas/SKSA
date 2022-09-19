package com.suraksha.android

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.suraksha.android.view_model.BaseViewModel
import com.suraksha.cloud.CloudConnector

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SurakshaApplication : Application(), CloudConnector.CloudConnectorInterface{
    private var viewModel: BaseViewModel ?=null
    val TAG=javaClass.simpleName


    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        CloudConnector.connectorInterface = this
        viewModel = BaseViewModel(this)
    }

    override fun getToken(): String {
       return viewModel?.getToken().toString()
    }
    override fun getAppId(): Long {
        return viewModel?.getAppId()?.toLong()?:0
    }


    override fun getContext(): Context {
        return this
    }

}