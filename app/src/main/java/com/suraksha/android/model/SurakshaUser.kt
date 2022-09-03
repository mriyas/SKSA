package com.suraksha.android.model

import com.suraksha.android.model.error.SurakshaError

class SurakshaUser {

    private var status: Int = 0
    private var cloudId: Long? = 0
    private var error: SurakshaError? = null

    var email:String?=null
    var phoneNumber:String?=null
    var username:String?=null
    var address:String?=null
    var password:String?=null

    init {
        this.error = null
    }


}