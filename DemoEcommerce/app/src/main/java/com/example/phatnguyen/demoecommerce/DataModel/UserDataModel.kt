package com.example.phatnguyen.demoecommerce.DataModel

import com.google.gson.annotations.SerializedName

/**
 * Created by phatnguyen on 12/11/17.
 */
class UserDataModel {
    @SerializedName("_id") val _id: Int = 0
    @SerializedName("email") val email: String? = null
    @SerializedName("username") val username: String? = null
    @SerializedName("password") val password: String? = null
    @SerializedName("userType") val userType: String? = null
}