package com.example.phatnguyen.demoecommerce.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.phatnguyen.demoecommerce.DataModel.ProductDataModel
import com.example.phatnguyen.demoecommerce.R
import com.squareup.picasso.Picasso
import java.util.ArrayList

/**
 * Created by phatnguyen on 11/26/17.
 */
class CartAdapter() : BaseAdapter() {
    private var mCartProductList: List<ProductDataModel>? = null
    private var mdeleteClickListener: ProductListAdapter.ButtonClickListener? = null
    private var mContext: Context? = null
    constructor(CartProductList: ArrayList<ProductDataModel>, mContext: Context, mdeleteClickListener: ProductListAdapter.ButtonClickListener): this() {
        mCartProductList = CartProductList
        this.mContext = mContext
        this.mdeleteClickListener = mdeleteClickListener
    }

    override fun getCount(): Int {
        return mCartProductList?.size!!
    }

    override fun getItem(position: Int): ProductDataModel {
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

            convertView.tag = item
        } else {
            item = convertView.tag as ViewItemHolder
        }

        val currentProduct = mCartProductList?.get(position)

        Picasso.with(mContext).setIndicatorsEnabled(false)
        Picasso.with(mContext)
                .load(currentProduct?.image)
                .resize(200, 200)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.rect_error)
                .into(item.ProductImageView)

        item.ProductTitle!!.text = currentProduct?.name
        item.PriceTextView!!.text = currentProduct?.price.toString()
        item.SellerTextView!!.text = currentProduct?.seller
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
    }
}