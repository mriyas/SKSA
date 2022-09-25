package com.suraksha.android.view.utility.callbacks

import android.view.View

interface ClickHelper {

    fun onClick(view : View)

    fun onClick(view : View, any : Any)

    fun onClick(vararg data:Any)

    fun onWorkInProgress()
}