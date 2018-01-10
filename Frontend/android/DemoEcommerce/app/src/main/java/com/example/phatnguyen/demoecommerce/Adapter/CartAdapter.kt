package com.example.phatnguyen.demoecommerce.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.phatnguyen.demoecommerce.CustomView.DynamicValueTextView
import com.example.phatnguyen.demoecommerce.DataModel.CartDataModel
import com.example.phatnguyen.demoecommerce.DataModel.ProductDataModel
import com.example.phatnguyen.demoecommerce.R
import com.squareup.picasso.Picasso
import java.util.ArrayList

/**
 * Created by phatnguyen on 11/26/17.
 */
class CartAdapter() : BaseAdapter() {
    private var mCartProductList: ArrayList<CartDataModel>? = null
    private var mdeleteClickListener: ProductListAdapter.ButtonClickListener? = null
    private var mContext: Context? = null
    constructor(CartProductList: ArrayList<CartDataModel>, mContext: Context, mdeleteClickListener: ProductListAdapter.ButtonClickListener): this() {
        mCartProductList = CartProductList
        this.mContext = mContext
        this.mdeleteClickListener = mdeleteClickListener
    }

    override fun getCount(): Int {
        return mCartProductList?.size!!
    }

    override fun getItem(position: Int): CartDataModel {
        return mCartProductList?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val item: ViewItemHolder

        if (convertView == null) {
            val inflater = mContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.cart_item_layout, null)
            item = ViewItemHolder()
            item.ProductTitle = convertView!!.findViewById(R.id.productname) as TextView
            item.ProductImageView = convertView.findViewById(R.id.pimageview) as ImageView
            item.PriceTextView = convertView.findViewById(R.id.productprice) as TextView
            item.SellerTextView = convertView.findViewById(R.id.sellername) as TextView
            item.DeleteButton = convertView.findViewById(R.id.DeleteButton) as ImageButton
            item.TotalAmount = convertView.findViewById(R.id.text_view) as DynamicValueTextView

            convertView.tag = item
        } else {
            item = convertView.tag as ViewItemHolder
        }

        val currentProduct = mCartProductList?.get(position)

        Picasso.with(mContext).setIndicatorsEnabled(false)
        Picasso.with(mContext)
                .load(currentProduct?.productImage!!)
                .resize(200, 200)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.rect_error)
                .into(item.ProductImageView)

        item.ProductTitle!!.text = "Product: " + currentProduct?.productName
        item.PriceTextView!!.text = "Price: " + currentProduct?.totalMoney.toString()
        item.SellerTextView!!.text = "User: " + currentProduct?.sellerName
        item.TotalAmount!!.setValues(currentProduct?.totalAmount)
        item.DeleteButton!!.tag = position
        item.DeleteButton!!.setOnClickListener { v ->
            if (mdeleteClickListener != null)
                mdeleteClickListener!!.onButtonClick(v.tag as Int)
        }
        return convertView
    }

    private inner class ViewItemHolder {
        internal var ProductImageView: ImageView? = null
        internal var ProductTitle: TextView? = null
        internal var PriceTextView: TextView? = null
        internal var SellerTextView: TextView? = null
        internal var DeleteButton: ImageButton? = null
        internal var TotalAmount: DynamicValueTextView? = null
    }
}