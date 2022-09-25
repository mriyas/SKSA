package com.suraksha.cloud.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.suraksha.cloud.model.response.ResponseData
import kotlinx.parcelize.Parcelize

@Parcelize
class Bookings:ResponseData(),Parcelable {
    /*"": 23,
    "": "aswanj",
    "": 100.9,
    "": "7.00AM to 10AM",
    "": "trivandrum",
    "": 1,
    "": 0*/
    @SerializedName("bookingId")
    var bookingId:String=""

    @SerializedName("patientName")
    var patientName:String=""

    @SerializedName("price")
    var price:String=""

    @SerializedName("timeSlot")
    var timeSlot:String=""

    @SerializedName("location")
    var location:String=""

    @SerializedName("service")
    var service:String=""

    @SerializedName("status")
    var status:String=""




        /*,
        "Data": {
        "id": 27,
        "vendor_id": 47,
        "user_id": 17,
        "type": 2,
        "price": 100.9,
        "slotRangeId": 12,
        "slotRange": "7.00AM to 10AM",
        "prescriptionUrl": "dfgdsfgdsfg/dsfdaf.png",
        "name": "aswanj",
        "phoneNumber": 456789445,
        "doctorsName": "asedd",
        "location": "trivandrum",
        "pincode": 234234,
        "total": 40,
        "gst": 20,
        "discount": 300,
        "gtotal": 200,
        "collection": "home",
        "gender": "Fermale",
        "bystander": null,
        "status": 0,
        "created_at": 1663858395,
        "updated_at": null,
        "testDetails": [
        {
            "id": 34,
            "orderId": 27,
            "type": 2,
            "itemId": 56,
            "itemName": 100.9,
            "itemPrice": "Dollo 150",
            "itemQuantity": 5,
            "created_at": 1663858395,
            "updated_at": null
        },
        {
            "id": 35,
            "orderId": 27,
            "type": 2,
            "itemId": 14,
            "itemName": 100.9,
            "itemPrice": "Dollo 150",
            "itemQuantity": 5,
            "created_at": 1663858395,
            "updated_at": null
        },
        {
            "id": 36,
            "orderId": 27,
            "type": 2,
            "itemId": 14,
            "itemName": 100.9,
            "itemPrice": "Dollo 150",
            "itemQuantity": 5,
            "created_at": 1663858395,
            "updated_at": null
        }
        ]
    }
    }
    }*/



}