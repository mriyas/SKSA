package com.suraksha.android.model

import com.suraksha.android.view.utility.SingleLiveEvent
import com.suraksha.cloud.model.response.auth.SurakshaUser

class UserLiveData : SingleLiveEvent<SurakshaUser>() {

    var mData = SurakshaUser()


}