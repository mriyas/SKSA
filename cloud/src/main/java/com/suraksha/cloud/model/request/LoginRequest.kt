package com.suraksha.cloud.model.request

import com.google.gson.annotations.SerializedName
 class LoginRequest {

     @SerializedName("appId")
     var appId: Int = 0

     @SerializedName("userEmail")
     var userEmail: String = ""

     @SerializedName("userType")
     var userType: Int = 3

     @SerializedName("userPassword")
     var userPassword: String = ""
 }