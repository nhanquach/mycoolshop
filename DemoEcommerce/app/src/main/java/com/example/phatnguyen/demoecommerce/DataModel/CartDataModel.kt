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
    var productPrice : Double = 0.toDouble()
    var sellerName : String? = null
    var totalAmount : Int = 0
    var totalMoney : Double = 0.toDouble()
    var createdTime : String? = null

    constructor(productID: Int, productImage: String, productName: String, productPrice: Double, sellerName: String, totalAmount: Int, totalMoney: Double, createdTime: String) {
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