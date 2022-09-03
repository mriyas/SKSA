package com.suraksha.android.model

import com.suraksha.android.model.error.SurakshaError
import com.suraksha.android.view.utility.SingleLiveEvent

class UserLiveData : SingleLiveEvent<SurakshaUser>() {

    var mData = SurakshaUser()


}