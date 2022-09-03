package com.suraksha.android.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

data class FilterData(val id:Int, val title:String): BaseObservable() {
    @Bindable
    var enabled: Int = DISABLED
        set(value) {
            field = value
            //notifyPropertyChanged(BR.enabled)
        }
    companion object {
        @JvmStatic
        val ENABLED = 1

        @JvmStatic
        val DISABLED = 0

    }
    fun onCheckedChanged(isChecked: Boolean) {
        enabled = if (isChecked) ENABLED else DISABLED

    }

}