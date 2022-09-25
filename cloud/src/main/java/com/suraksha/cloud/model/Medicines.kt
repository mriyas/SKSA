package com.suraksha.cloud.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.suraksha.cloud.model.response.ResponseData
import kotlinx.parcelize.Parcelize

@Parcelize
class Medicines:ResponseData(),Parcelable {
    /*"": 68,
                    "": "Losartan",
                    "": 150,
                    "": "INR",
                    "": 0*/
    @SerializedName("medicineId")
    var medicineId:String=""

    @SerializedName("medicineName")
    var medicineName:String=""

    @SerializedName("price")
    var price:String=""

    @SerializedName("currency")
    var currency:String=""

    @SerializedName("prescription")
    var prescription:String=""

    @SerializedName("imageUrl")
    var imageUrl:String=""

    @SerializedName("medicalComposition")
    var medicalComposition:String=""



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