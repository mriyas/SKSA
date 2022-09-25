package com.suraksha.cloud.model.response

import com.google.gson.annotations.SerializedName

data class TimeSlot(@SerializedName("slotId")val slotId:Int) {

    @SerializedName("slotRange")
    var slotRange:String=""

    @SerializedName("noOfBookings")
     var noOfBookings:String = ""

    @SerializedName("status")
     var status:Int = 0
}