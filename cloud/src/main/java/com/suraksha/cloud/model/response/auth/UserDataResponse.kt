package com.suraksha.cloud.model.response.auth

import com.suraksha.cloud.model.response.ResponseData

open class UserDataResponse (
    var token: String = "",
    var appId: Int = 0,
    var userId: Long = 0,
    var userName: String = "",
    var userType: Int = UserType.NOT_DEFINED,
    var refreshToken: String = "",
    var profileUrl: String = ""

): ResponseData()

object UserType{
    val TEACHER =0
    val STUDENT =1
    val NOT_DEFINED=-1
}