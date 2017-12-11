package com.example.phatnguyen.demoecommerce.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.example.phatnguyen.demoecommerce.DataModel.ProductDataModel
import com.example.phatnguyen.demoecommerce.R
import com.firebase.client.Firebase
import com.squareup.picasso.Picasso
import java.util.ArrayList

/**
 * Created by phatnguyen on 11/16/17.
 */
class ProductDetailActivity: AppCompatActivity() {
    private val TAG = "ProductDetailActivity"
    private val ProductName: String? = null
    private var productid: String? = null
    private val BidReferences: ArrayList<Firebase>? = null
    private var Product: ProductDataModel? = null
    private val Username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail_layout)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#03A9F4")
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        Product = intent.getSerializableExtra("IntentProduct") as ProductDataModel
        Log.d(TAG,"Showing" + Product!!.id.toString() + Product!!.name.toString())
        val productImage = findViewById(R.id.SellerProductImageView) as ImageView
        val productName = findViewById(R.id.SellerProductName) as TextView
        val productPrice = findViewById(R.id.SellerProductPrice) as TextView

        productName.text = Product!!.name.toString()
        productPrice.text = Integer.toString(Product!!.price)
        Picasso.with(this)
                .load(Product!!.image)
                .error(R.drawable.rect_error)
                .into(productImage)
        productid = Product!!.id.toString()

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