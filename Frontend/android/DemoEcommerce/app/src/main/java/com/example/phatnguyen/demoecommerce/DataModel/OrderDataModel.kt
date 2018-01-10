package com.example.phatnguyen.demoecommerce.DataModel

import com.example.phatnguyen.demoecommerce.Application.Ecommerce
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by phatnguyen on 1/7/18.
 */
class OrderDataModel {
    @SerializedName("_id") val _id: Int = 0
    @SerializedName("id_order_products") var id_order_products: String? = null
    @SerializedName("id_user") var id_user: String? = null
    @SerializedName("created_at") var created_at: Date? = null
    @SerializedName("address") var address: String? = null
    @SerializedName("status") var status: String? = null
    @SerializedName("delivery_note") var delivery_note: String? = null
    @SerializedName("price") var price: Float = 0.toFloat()
    @SerializedName("phone") var phone: String? = null
    @SerializedName("email") var email: String? = null
}