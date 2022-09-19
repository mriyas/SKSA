package com.suraksha.cloud.model.response.lab

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.suraksha.cloud.model.response.ResponseData
import kotlinx.parcelize.Parcelize

@Parcelize
class LabTest:ResponseData(),Parcelable {

    @SerializedName("duration")
    var duration:String=""

    @SerializedName("department")
    var department:String=""

    @SerializedName("testCode")
    var testCode:String=""

    @SerializedName("testId")
    var testId:String=""

    @SerializedName("confidentiality")
    var confidentiality:String=""

    @SerializedName("collectionCenter")
    var collectionCenter:String=""

    @SerializedName("testName")
    var testName:String=""

    @SerializedName("price")
    var testRate:String=""

    @SerializedName("description")
    var testDesc:String=""

    @SerializedName("prescription")
    var prescription:String="1"
    @SerializedName("status")
    var status:String="1"

    @SerializedName("currency")
    var currency:String="INR"

    fun getConfidentialityText():String{
        return if( confidentiality=="1") "YES" else "NO"
    }
    fun getPrescriptionRequired():String{
        return if( prescription=="1") "YES" else "NO"
    }
}