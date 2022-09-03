package com.suraksha.cloud

import android.content.Context

class CloudConnector {

    companion object {
        var connectorInterface: CloudConnectorInterface? = null
    }

    interface CloudConnectorInterface{
        fun getToken(): String
        fun getContext(): Context
    }
}