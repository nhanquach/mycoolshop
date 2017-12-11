package com.example.phatnguyen.demoecommerce.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.phatnguyen.demoecommerce.DataModel.ProductDataModel
import com.example.phatnguyen.demoecommerce.R
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList


/**
 * Created by phatnguyen on 11/15/17.
 */
class ProductListAdapter() : BaseAdapter(), Parcelable {
    private lateinit var mContext: Context
    private var mCartClickListener: ButtonClickListener? = null
    internal var screenWidth: Int = 0
    internal lateinit var viewHolder: ViewHolder
    internal lateinit var imageLoader: ImageLoader
    internal lateinit var options: DisplayImageOptions
    private var productList: ArrayList<ProductDataModel>? = null

    constructor(parcel: Parcel) : this() {
        screenWidth = parcel.readInt()
    }

    constructor(c: Context, productList: ArrayList<ProductDataModel>?, screenWidth: Int, cartListener: ButtonClickListener) : this() {
        mContext = c
        this.productList = productList
        this.screenWidth = screenWidth
        mCartClickListener = cartListener

        options = DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .resetViewBeforeLoading(true)
                .build()
        val config = ImageLoaderConfiguration.Builder(mContext)
                .defaultDisplayImageOptions(options)
                .threadPriority(Thread.MAX_PRIORITY)
                .threadPoolSize(5)
                .diskCacheExtraOptions(screenWidth, Math.round((screenWidth / 2).toFloat()), null)
                .memoryCache(WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .build()
        imageLoader = ImageLoader.getInstance()
        imageLoader.init(config)
    }

    internal class ViewHolder {
        var productImage: ImageView? = null
        var productName: TextView? = null
        var addedInfo: TextView? = null
        var productPrice: TextView? = null
        var addToCart: info.hoang8f.widget.FButton? = null
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        // TODO Auto-generated method stub

        if (convertView == null) {
            val inflater = mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.product_fragment_list_item_layout, parent, false)
        }

        val currentProduct = productList?.get(position)

        viewHolder = ViewHolder()
        viewHolder.productName = convertView?.findViewById(R.id.productname) as TextView
        viewHolder.productPrice = convertView.findViewById(R.id.productprice) as TextView
        viewHolder.productImage = convertView.findViewById(R.id.productimageview) as ImageView
        viewHolder.addedInfo = convertView.findViewById(R.id.addedInfo) as TextView
        viewHolder.addedInfo?.visibility = View.GONE
        viewHolder.addToCart = convertView.findViewById(R.id.Addtocart) as info.hoang8f.widget.FButton

        viewHolder.addToCart?.tag = position

        viewHolder.addToCart?.setOnClickListener({ v ->
            if (mCartClickListener != null)
                mCartClickListener!!.onButtonClick(v.tag as Int)
        })

        viewHolder.productImage?.layoutParams?.width = screenWidth
        viewHolder.productImage?.layoutParams?.height = Math.round((screenWidth / 2).toFloat())
        viewHolder.productImage?.requestLayout()

        viewHolder.productName?.text = currentProduct?.name
        viewHolder.productPrice?.text = currentProduct?.price.toString()

        Picasso.with(mContext)
                .setIndicatorsEnabled(false)
        Picasso.with(mContext)
                .load(currentProduct?.image)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.rect_error)
                .into(viewHolder.productImage)


        return convertView
    }

    interface ButtonClickListener {
        fun onButtonClick(position: Int)
    }

    override fun getCount(): Int {
        // TODO Auto-generated method stub
        return productList?.size!!
    }

    override fun getItem(position: Int): Any? {
        // TODO Auto-generated method stub
        return null
    }

    override fun getItemId(position: Int): Long {
        // TODO Auto-generated method stub
        return position.toLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(screenWidth)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductListAdapter> {
        override fun createFromParcel(parcel: Parcel): ProductListAdapter {
            return ProductListAdapter(parcel)
        }

        override fun newArray(size: Int): Array<ProductListAdapter?> {
            return arrayOfNulls(size)
        }
    }

}