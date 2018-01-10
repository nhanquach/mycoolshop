package com.example.phatnguyen.demoecommerce.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.phatnguyen.demoecommerce.DataModel.OrderDataModel
import com.example.phatnguyen.demoecommerce.R
import info.hoang8f.widget.FButton

/**
 * Created by phatnguyen on 1/7/18.
 */
class OrderListAdapter() : BaseAdapter() {

    private lateinit var mContext: Context
    private var orderList: ArrayList<OrderDataModel>? = null
    private var mDesClickListener: ButtonClickListener? = null
    internal lateinit var viewHolder: ViewHolder
    internal lateinit var currOrder: OrderDataModel

    constructor(c: Context,orderList: ArrayList<OrderDataModel>?,desListener: ButtonClickListener) : this() {
        mContext = c
        this.orderList = orderList
        mDesClickListener = desListener
    }

    internal class ViewHolder {
        var orderName: TextView? = null
        var orderer: TextView? = null
        var address: TextView? = null
        var delivery_note: TextView? = null
        var price: TextView? = null
        var phone: TextView? = null
        var email: TextView? = null
        var status: TextView? = null
        var orderDesBtn: FButton? = null

    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater = mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.order_item_list_layout, parent, false)
        }
        if(orderList!= null && orderList!!.size !=0) {
            currOrder = orderList?.get(position)!!
        }

        viewHolder = ViewHolder()
        viewHolder.orderName = convertView?.findViewById(R.id.orderName) as TextView
        viewHolder.orderer = convertView?.findViewById(R.id.orderer) as TextView
        viewHolder.address = convertView?.findViewById(R.id.address) as TextView
        viewHolder.delivery_note = convertView?.findViewById(R.id.delivery_note) as TextView
        viewHolder.price = convertView?.findViewById(R.id.price) as TextView
        viewHolder.phone = convertView?.findViewById(R.id.phone) as TextView
        viewHolder.email = convertView?.findViewById(R.id.email) as TextView
        viewHolder.status = convertView?.findViewById(R.id.status) as TextView
        viewHolder.orderDesBtn = convertView?.findViewById(R.id.orderDesBtn) as FButton
        viewHolder.orderDesBtn?.tag = position

        viewHolder.orderName?.text = "Order" + currOrder!!.created_at
        viewHolder.orderer?.text = currOrder.id_user
        viewHolder.address?.text = currOrder.address
        viewHolder.delivery_note?.text = currOrder.delivery_note
        viewHolder.price?.text = currOrder.price.toString()
        viewHolder.phone?.text = currOrder.phone
        viewHolder.email?.text = currOrder.email
        viewHolder.status?.text = currOrder.status
        viewHolder.orderDesBtn?.setOnClickListener ({ v ->
            if (mDesClickListener != null) {
                mDesClickListener!!.onButtonClick(v.tag as Int)
            }
        })
        return convertView
    }

    interface ButtonClickListener {
        fun onButtonClick(position: Int)
    }

    override fun getCount(): Int {
        // TODO Auto-generated method stub
        return orderList?.size!!
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