package com.example.phatnguyen.demoecommerce.DataModel

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by phatnguyen on 1/9/18.
 */
class OrderProductDataModel {
    @SerializedName("_id") val _id: Int = 0
    @SerializedName("order_id") var order_id: String? = null
    @SerializedName("product_id") var product_id: String? = null
    @SerializedName("product_name") var product_name: String? = null
    @SerializedName("product_price") var product_price: Long = 0.toLong()
    @SerializedName("product_quantity") var product_quantity: Int = 0
}