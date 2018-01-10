package com.example.phatnguyen.demoecommerce.DataModel

import java.io.Serializable

/**
 * Created by phatnguyen on 12/17/17.
 */
class CartDataModel: Serializable {
    var _id : Int = 0
    var productID : Int = 0
    var productImage : String? = null
    var productName : String? = null
    var productPrice : Long = 0
    var sellerName : String? = null
    var totalAmount : Int = 0
    var totalMoney : Long = 0
    var createdTime : String? = null

    constructor(productID: Int, productImage: String, productName: String, productPrice: Long, sellerName: String, totalAmount: Int, totalMoney: Long, createdTime: String) {
        this.productID = productID
        this.productImage = productImage
        this.productName = productName
        this.productPrice = productPrice
        this.sellerName = sellerName
        this.totalAmount = totalAmount
        this.totalMoney = totalMoney
        this.createdTime = createdTime
    }
}