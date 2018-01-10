package com.example.phatnguyen.demoecommerce.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import com.example.phatnguyen.demoecommerce.Adapter.OrderListAdapter
import com.example.phatnguyen.demoecommerce.Application.Ecommerce
import com.example.phatnguyen.demoecommerce.DataModel.OrderDataModel
import com.example.phatnguyen.demoecommerce.DataModel.UserDataModel
import com.example.phatnguyen.demoecommerce.R
import com.example.phatnguyen.demoecommerce.Utils.Constant
import com.example.phatnguyen.demoecommerce.Utils.NetworkUtils
import com.example.phatnguyen.demoecommerce.Utils.ProgressDialogUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder



/**
 * Created by phatnguyen on 1/7/18.
 */
class ManageOrdersActivity: AppCompatActivity() {
    val TAG = "ManageOrders"
    private lateinit var userSpinner: Spinner
    private lateinit var orders_list: ListView
    private var mUserName: ArrayList<String>? = null
    private var mUser: ArrayList<UserDataModel>? = null
    private var mOrder: ArrayList<OrderDataModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manage_orders_layout)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#03A9F4")
        }
        mUserName = ArrayList<String>()
        mUser = ArrayList<UserDataModel>()
        mOrder = ArrayList<OrderDataModel>()
        userSpinner = findViewById(R.id.user_list) as Spinner
        orders_list = findViewById(R.id.orders_list) as ListView

//        val randomThread = Thread({
//            if ((this as Activity).isFinishing) {
//                return@Thread
//            }
//            this.runOnUiThread {
//                ProgressDialogUtils.sharedInstance.showProgressDialog(this,"Loading ...")
//            }
//
//        })
//        randomThread.start()

        val getUserAdapter = Thread({
            Log.d(TAG, "runThread(): ${Thread.currentThread().name}")
            val json = NetworkUtils.Companion.sharedInstance.readUrl(Constant.SERVER_URL + "login")
            val user = Gson().fromJson(json, Array<UserDataModel>::class.java)

            for(item in user) {
                mUser!!.add(item)
                mUserName!!.add(item.username.toString())
            }

            if (this == null) {
                return@Thread
            }

            if (mUserName!!.size > 0) {
                this.runOnUiThread {
                    Log.i(TAG, "runOnUiThread")
                    val userNameAdapter = ArrayAdapter<String>(applicationContext,
                            R.layout.spinner_item, mUserName)
                    userNameAdapter.notifyDataSetChanged()
                    userSpinner.adapter = userNameAdapter
                }
            }
        })
        getUserAdapter.start()

        val getOrdersAdapter = Thread ({
            Log.d(TAG, "runThread(): ${Thread.currentThread().name}")
            val json = NetworkUtils.sharedInstance.readUrl(Constant.SERVER_URL + "order")
            val gson = GsonBuilder()
                    .setDateFormat(Ecommerce.sharedInstance.df).create()
            val orders = gson.fromJson(json, Array<OrderDataModel>::class.java)

            for (item in orders) {
                mOrder!!.add(item)
            }

            if (this == null) {
                return@Thread
            }

            if (mOrder!!.size > 0) {
                this.runOnUiThread {
                    Log.i(TAG, "runOnUiThread")
                    val adapter = OrderListAdapter(applicationContext,mOrder,object: OrderListAdapter.ButtonClickListener {
                        override fun onButtonClick(position: Int) {
                            //SHow detail here
                            val intent = Intent(applicationContext, ProductDetailActivity::class.java)
                            intent.putExtra("orderId", mOrder!![position].id_order_products)
                            startActivity(intent)
                        }
                    })
                    adapter.notifyDataSetChanged()
                    orders_list.adapter = adapter
//                    ProgressDialogUtils.sharedInstance.hideProgressDialog()
                }
            }
            else {
//                ProgressDialogUtils.sharedInstance.hideProgressDialog()
            }

        })

        getOrdersAdapter.start()

        userSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?,
                                        position: Int, id: Long) {
                Log.v("item", parent.getItemAtPosition(position) as String)
                    val getUserOrderAdapter = Thread({
                        Log.d(TAG, "runThread(): ${Thread.currentThread().name}")
                        Log.d(TAG, Constant.SERVER_URL + "order/getfrom&id=" + mUser!![position]._id)
                        val json = NetworkUtils.sharedInstance.readUrl(Constant.SERVER_URL + "order/getfrom&id=" + mUser!![position]._id)
                        val gson = GsonBuilder()
                                .setDateFormat(Ecommerce.sharedInstance.df).create()
                        val userOrders = gson.fromJson(json, Array<OrderDataModel>::class.java)

                        mOrder!!.clear()
                        for(item in userOrders) {
                            mOrder!!.add(item)
                        }

                        if (this == null) {
                            return@Thread
                        }

                        if (mOrder!!.size > 0) {
                            runOnUiThread {
                                Log.i(TAG, "runOnUiThread")
                                val adapter = OrderListAdapter(applicationContext,mOrder,object: OrderListAdapter.ButtonClickListener {
                                    override fun onButtonClick(position: Int) {
                                        //SHow detail here
                                        val intent = Intent(applicationContext, ProductDetailActivity::class.java)
                                        intent.putExtra("orderId", mOrder!![position].id_order_products)
                                        startActivity(intent)
                                    }
                                })
                                adapter.notifyDataSetChanged()
                                orders_list.adapter = adapter
                                ProgressDialogUtils.sharedInstance.hideProgressDialog()
                            }
                        }
                        else {
                            runOnUiThread {
                                ProgressDialogUtils.sharedInstance.hideProgressDialog()
                            }
                        }
                    })
                    getUserOrderAdapter.start()
            }



            override fun onNothingSelected(parent: AdapterView<*>) {
                // TODO Auto-generated method stub
            }
        }

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