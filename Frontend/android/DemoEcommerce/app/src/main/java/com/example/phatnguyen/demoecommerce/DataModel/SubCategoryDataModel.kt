package com.example.phatnguyen.demoecommerce.DataModel

import com.google.gson.annotations.SerializedName

/**
 * Created by phatnguyen on 12/10/17.
 */
class SubCategoryDataModel {
    @SerializedName("_id") val _id: Int = 0
    @SerializedName("id") val id: String? = null
    @SerializedName("name") val name: String? = null
    @SerializedName("type") val type: String? = null
}