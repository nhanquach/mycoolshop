package com.example.phatnguyen.demoecommerce.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.Window
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.phatnguyen.demoecommerce.Adapter.OrderListAdapter
import com.example.phatnguyen.demoecommerce.Adapter.OrderProductDetailAdapter
import com.example.phatnguyen.demoecommerce.DataModel.CartDataModel
import com.example.phatnguyen.demoecommerce.DataModel.CategoryDataModel
import com.example.phatnguyen.demoecommerce.DataModel.OrderProductDataModel
import com.example.phatnguyen.demoecommerce.DataModel.ProductDataModel
import com.example.phatnguyen.demoecommerce.R
import com.example.phatnguyen.demoecommerce.Utils.Constant
import com.example.phatnguyen.demoecommerce.Utils.NetworkUtils
import com.example.phatnguyen.demoecommerce.Utils.ProgressDialogUtils
import com.firebase.client.Firebase
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.util.ArrayList

/**
 * Created by phatnguyen on 11/16/17.
 */
class ProductDetailActivity: AppCompatActivity() {
    private val TAG = "ProductDetailActivity"
    private var mOrderProduct: ArrayList<OrderProductDataModel>? = null
    private lateinit var orderProductList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_product_list_layout)
        window.decorView.setBackgroundColor(Color.WHITE)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#03A9F4")
        }

        mOrderProduct = ArrayList<OrderProductDataModel>()
        orderProductList = findViewById(R.id.orderProductList) as ListView

        val getProductDetailAdapter = Thread ({
            Log.d(TAG, "runThread(): ${Thread.currentThread().name}")
            val json = NetworkUtils.Companion.sharedInstance.readUrl(Constant.SERVER_URL + "orderproducts/getproductfrom&id=" + getOrderId())
            val orderProductDetail = Gson().fromJson(json, Array<OrderProductDataModel>::class.java)

            for (item in orderProductDetail) {
                mOrderProduct!!.add(item)
            }

            if (this == null) {
                return@Thread
            }

            if (mOrderProduct!!.size > 0) {
                this.runOnUiThread {
                    Log.i(TAG, "runOnUiThread")
                    val adapter = OrderProductDetailAdapter(applicationContext,mOrderProduct)
                    adapter.notifyDataSetChanged()
                    orderProductList.adapter = adapter
                }
            }

        })
        getProductDetailAdapter.start()

    }

    fun getOrderId() :String? {
        val bundle = intent.extras
        if (bundle != null) {
            return bundle.get("orderId") as String?
        }
        return null
    }

//    @SuppressLint("SetTextI18n")
//    override fun onStart() {
//        super.onStart()
//        Product = intent.getSerializableExtra("IntentProduct") as ProductDataModel
//        Log.d(TAG,"Showing" + Product!!.id.toString() + Product!!.name.toString())
//        val productImage = findViewById(R.id.SellerProductImageView) as ImageView
//        val productName = findViewById(R.id.SellerProductName) as TextView
//        val productPrice = findViewById(R.id.SellerProductPrice) as TextView
//
//        productName.text = Product!!.name.toString()
//        productPrice.text = Product!!.price.toString()
//        Picasso.with(this)
//                .load(Product!!.image)
//                .error(R.drawable.rect_error)
//                .into(productImage)
//        productid = Product!!.id.toString()

//    }


    override fun onBackPressed() {
        startActivity(Intent(this, ManageOrdersActivity::class.java))
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                this.finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}