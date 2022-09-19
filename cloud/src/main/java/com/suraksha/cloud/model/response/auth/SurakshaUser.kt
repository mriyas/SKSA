package com.suraksha.cloud.model.response.auth

import com.google.gson.annotations.SerializedName
import com.suraksha.cloud.model.response.ResponseData

open class SurakshaUser : ResponseData(){

    private var status: Int = 0
    private var cloudId: Long? = 0

    var phoneNumber:String?=null
    var username:String?=null
    var address:String?=null
    var password:String?=null

    @SerializedName("token")
    var token:String=""

    @SerializedName("userId")
    var userId:Long=0

    @SerializedName("userType")
    var userType:Int=3

    @SerializedName("userName")
    var userName:String=""

    @SerializedName("service")
    var service:Int?=null

    @SerializedName("userEmail")
    var email:String?=null



    companion object{
        object ServiceType{
            const val LAB=2
            const val PHARMACY=3
        }
    }

}