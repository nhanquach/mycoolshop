package com.example.phatnguyen.demoecommerce.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.phatnguyen.demoecommerce.DataModel.OrderProductDataModel
import com.example.phatnguyen.demoecommerce.R

/**
 * Created by phatnguyen on 1/9/18.
 */
class OrderProductDetailAdapter() : BaseAdapter() {
    private lateinit var mContext: Context
    private var orderProductList: ArrayList<OrderProductDataModel>? = null
    internal lateinit var viewHolder: ViewHolder

    constructor(c: Context, orderProductList: ArrayList<OrderProductDataModel>?) : this() {
        mContext = c
        this.orderProductList = orderProductList
    }

    internal class ViewHolder {
        var productName: TextView? = null
        var productPrice: TextView? = null
        var productQuantity: TextView? = null
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        if (convertView == null) {
            val inflater = mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.product_detail_layout, parent, false)
        }
        val currentProduct = orderProductList?.get(position)

        viewHolder = ViewHolder()
        viewHolder.productName = convertView?.findViewById(R.id.SellerProductName) as TextView
        viewHolder.productPrice = convertView?.findViewById(R.id.SellerProductPrice) as TextView
        viewHolder.productQuantity = convertView?.findViewById(R.id.SellerProductQuantity) as TextView

        viewHolder.productName?.text = "Product: " + currentProduct?.product_name.toString()
        viewHolder.productPrice?.text = "Price: " + currentProduct?.product_price.toString()
        viewHolder.productQuantity?.text = "Quantity: " + currentProduct?.product_quantity.toString()

        return convertView
    }

    override fun getCount(): Int {
        // TODO Auto-generated method stub
        return orderProductList?.size!!
    }

    override fun getItem(position: Int): Any? {
        // TODO Auto-generated method stub
        return null
    }

    override fun getItemId(position: Int): Long {
        // TODO Auto-generated method stub
        return position.toLong()
    }

}