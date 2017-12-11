package com.example.phatnguyen.demoecommerce.DataModel

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

/**
 * Created by phatnguyen on 11/15/17.
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
class ProductDataModel {
    @SerializedName("id") val id: Int = 0
    @SerializedName("name") var name: String? = null
    @SerializedName("price") var price: Int = 0
    @SerializedName("description") var description: String? = null
    @SerializedName("created_at") var created_at: String ? = null
    @SerializedName("seller") var seller: String? = null
    @SerializedName("start_time") var start_time: String? = null
    @SerializedName("end_time") var end_time: String? = null
    @SerializedName("image") var image: String? = null

    //Old firebase model
//    var productName: String? = null
//        private set
//    var productImage: String? = null
//        private set
//    private var currentPrice: Int? = 0
//    var seller: String? = null
//        private set
//    var _id: String? = null
//        private set
//    var productType: String = ""
//    var category: String = ""
//    var productQuanlity: Int? = 0
//    var updateTime: Date? = null
//
//    constructor() {}
//
//    constructor(name: String, image: String, price: Int?) {
//        this.productName = name
//        this.productImage = image
//        this.currentPrice = price
//    }
//
//    constructor(name: String, image: String, price: Int?, seller: String) {
//        this.productName = name
//        this.productImage = image
//        this.currentPrice = price
//        this.seller = seller
//    }
//
//    constructor(name: String, image: String, price: Int?, seller: String, id: String, category: String) {
//        this.productName = name
//        this.productImage = image
//        this.currentPrice = price
//        this.seller = seller
//        this._id = id
//        this.productType = category
//    }
//
//    constructor(subCategory: String, category: String, image: String?, price: Int?, seller: String?, id: String?, name: String?, updateTime: Date?) {
//        this.productName = name
//        this.productImage = image
//        this.currentPrice = price
//        this.seller = seller
//        this._id = id
//        this.productType = subCategory
//        this.category = category
//        this.updateTime = updateTime
//    }
//
//    constructor(name: String, image: String, price: Int?, seller: String, id: String, quantity: Int?) {
//        this.productName = name
//        this.productImage = image
//        this.currentPrice = price
//        this.seller = seller
//        this._id = id
//        this.productQuanlity = quantity
//    }
//
//    fun getprice(): Int? {
//        return currentPrice
//    }
}