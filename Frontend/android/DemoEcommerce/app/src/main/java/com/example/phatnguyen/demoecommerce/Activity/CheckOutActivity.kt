package com.example.phatnguyen.demoecommerce.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatButton
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.phatnguyen.demoecommerce.DataModel.CartDataModel
import com.example.phatnguyen.demoecommerce.MetaData.OrdersMetaData
import com.example.phatnguyen.demoecommerce.R
import com.google.gson.GsonBuilder
import com.example.phatnguyen.demoecommerce.DataModel.ProductDataModel
import com.example.phatnguyen.demoecommerce.Utils.*
import com.github.kittinunf.fuel.Fuel
import java.util.ArrayList
import org.json.JSONObject




/**
 * Created by phatnguyen on 12/27/17.
 */
class CheckOutActivity : AppCompatActivity() {
    val TAG = "Checkout"
    private lateinit var mAddress: EditText
    private lateinit var mNote: EditText
    private lateinit var mPhone: EditText
    private lateinit var mEmail: EditText
    private lateinit var mCheckOut: Button
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comfirm_checkout)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#03A9F4")
        }
        val confirmBtn = findViewById(R.id.btn_checkout) as AppCompatButton
        confirmBtn.setOnClickListener {

        }
        mAddress = findViewById(R.id.address) as EditText
        mNote = findViewById(R.id.note) as EditText
        mPhone = findViewById(R.id.phone) as EditText
        mEmail = findViewById(R.id.email) as EditText
        mCheckOut = findViewById(R.id.btn_checkout) as Button
        mCheckOut.setOnClickListener {
            for (data in getPostData()) {
                Fuel.post(Constant.SERVER_URL + "order/create").body(data).response { request, response, result ->
                    Log.d(TAG, request.cUrlString())
                    result.fold(success = {
                        Toast.makeText(applicationContext,it.toString(),Toast.LENGTH_SHORT)
                    }, failure = {
                        Toast.makeText(applicationContext,String(it.errorData),Toast.LENGTH_SHORT)
                    })
                }
            }
        }
    }

    fun getProductExtraValue() : ArrayList<CartDataModel>? {
        val bundle = intent.extras
        if (bundle != null) {
            return bundle.get("listProduct") as ArrayList<CartDataModel>?
        }
        return null

    }

    fun getPostData(): ArrayList<String> {
        var jsonPostData: ArrayList<String>? = null
        val productList = getProductExtraValue() as ArrayList<CartDataModel>
        if (productList!!.size > 0) {
            for (item in productList) {
                 val orders = OrdersMetaData(RandomTask.sharedInstance.randomGenarator().toString(),mAddress.text.toString(),DefaultSettings.sharedInstance.getIntDefaults(UserSettings.id(),applicationContext).toString(),mNote.text.toString(),mPhone.text.toString(),mEmail.text.toString(),item.productPrice.toString(),DefaultSettings.sharedInstance.getDefaults(UserSettings.name(),applicationContext))
                 jsonPostData = ArrayList<String>()
                 jsonPostData.add(StringUtils.sharedInstance.object2JSON(orders))
            }
        }
        return jsonPostData!!
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