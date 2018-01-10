package com.example.phatnguyen.demoecommerce.Fragment

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.phatnguyen.demoecommerce.Activity.CheckOutActivity
import com.example.phatnguyen.demoecommerce.Adapter.CartAdapter
import com.example.phatnguyen.demoecommerce.Adapter.ProductListAdapter
import com.example.phatnguyen.demoecommerce.DataModel.CartDataModel
import com.example.phatnguyen.demoecommerce.Database.CartDatabaseAPI
import com.example.phatnguyen.demoecommerce.Database.database
import com.example.phatnguyen.demoecommerce.R
import com.example.phatnguyen.demoecommerce.Utils.ProgressDialogUtils
import com.malinskiy.materialicons.IconDrawable
import com.malinskiy.materialicons.Iconify
import com.vlonjatg.progressactivity.ProgressFrameLayout
import info.hoang8f.widget.FButton
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import java.io.Serializable
import java.util.ArrayList

/**
 * Created by phatnguyen on 11/11/17.
 */
class CartFragment: Fragment(),Serializable {
    private  val TAG = "CartFragment"
    private var cartProductList: ArrayList<CartDataModel>? = null
//    private var productID: ArrayList<String>? = null
    internal var adapter: CartAdapter? = null
//    private var totalPrice: Int? = 0
//    private  val cartRef = Firebase("https://ecommerce-demo-7f6de.firebaseio.com/Carts")
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var progressRelativeLayout: ProgressFrameLayout

    companion object {
        val sharedInstance: CartFragment by lazy { Holder.INSTANCE }
    }

    private object Holder { val INSTANCE = CartFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView =  inflater!!.inflate(R.layout.cart_fragment_layout, container, false)
        activity.title = "Cart"
//        ProgressDialogUtils.sharedInstance.showProgressDialogWithStyle(activity,"LOADING CART", ProgressDialog.STYLE_SPINNER)
        val cartlistView = rootView?.findViewById(R.id.cartlistView) as ListView
        val checkoutBtn = rootView?.findViewById(R.id.Checkoutbutton) as FButton
        checkoutBtn.setOnClickListener {
            val intent = Intent(activity, CheckOutActivity::class.java)
            intent.putExtra("listProduct",cartProductList)
            startActivity(intent)
        }
        val loadCart = Thread ({
            cartProductList = ArrayList()
            val db = context.database.writableDatabase
            db.select(CartDatabaseAPI.TABLE_NAME).exec {
                parseList( object : MapRowParser<Map<String, Any?>> {
                    override fun parseRow(columns: Map<String, Any?>): Map<String, Any?> {
                        Log.e("Your result", columns.toString())
                        val cartItems = CartDataModel((columns[CartDatabaseAPI.PRODUCT_ID] as Long).toInt(),
                                columns[CartDatabaseAPI.PRODUCT_IMAGE] as String,
                                columns[CartDatabaseAPI.PRODUCT_NAME] as String,
                                (columns[CartDatabaseAPI.PRODUCT_PRICE] as Long),
                                columns[CartDatabaseAPI.SELLER_NAME] as String,
                                (columns[CartDatabaseAPI.TOTAL_AMOUNT] as Long).toInt(),
                                (columns[CartDatabaseAPI.TOTAL_MONEY] as Long),
                                columns[CartDatabaseAPI.CREATED_TIME] as String)
                        cartProductList!!.add(cartItems)
                        return columns
                    }
                }
                )
            }

            if (activity == null) {
                return@Thread
            }

            if (cartProductList!!.size > 0) {
                activity.runOnUiThread {
                    adapter = CartAdapter(cartProductList!!,context, object : ProductListAdapter.ButtonClickListener {
                        override fun onButtonClick(position: Int) {
                            val db = context.database.writableDatabase
                            db.delete(CartDatabaseAPI.TABLE_NAME,CartDatabaseAPI.PRODUCT_ID + "=" + cartProductList!![position].productID.toString(),null)
                            adapter?.notifyDataSetChanged()
                            reloadView()//To change body of created functions use File | Settings | File Templates.
//                            ProgressDialogUtils.sharedInstance.hideProgressDialog()
                        }
                    })
                    adapter?.notifyDataSetChanged()
                    cartlistView.adapter = adapter
//                    ProgressDialogUtils.sharedInstance.hideProgressDialog()
                }
            }
            else {
                activity.runOnUiThread {
                    checkoutBtn.visibility = View.GONE
                    ProgressDialogUtils.sharedInstance.hideProgressDialog()
                    progressRelativeLayout = rootView?.findViewById(R.id.progressCart) as ProgressFrameLayout
                    val emptyDrawable = IconDrawable(context, Iconify.IconValue.zmdi_shopping_cart)
                            .colorRes(android.R.color.darker_gray)
                    progressRelativeLayout.showEmpty(emptyDrawable,
                            "Empty Cart",
                            "There are no content to view now.")
                }
            }
        })
        loadCart.start()
//        progressRelativeLayout = rootView?.findViewById(R.id.progress) as ProgressFrameLayout
//        val emptyDrawable = IconDrawable(context, Iconify.IconValue.zmdi_shopping_cart)
//                .colorRes(android.R.color.darker_gray)
//        progressRelativeLayout.showEmpty(emptyDrawable,
//                "Empty Cart",
//                "There are no content to view now.")
//        val CheckoutButton = rootView?.findViewById(R.id.Checkoutbutton) as info.hoang8f.widget.FButton
//        productID = ArrayList()
//        cartlistView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
//            //Cart item click
//        }
//        CheckoutButton.setOnClickListener {
//            val toast = Toast.makeText(context, "", Toast.LENGTH_LONG)
//            val view = toast.view
//            view.setBackgroundResource(R.drawable.rounded_square)
//            val text = view.findViewById(android.R.id.message) as TextView
//            text.setTextColor(resources.getColor(R.color.white))
//            text.text = " TOTAL AMOUNT PAYABLE IS " + totalPrice.toString()
//            toast.show()
//        }

        return rootView
    }

//    fun loadAdapter() {
//        cartRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (postSnapshot in dataSnapshot.children) {
//                    Log.d(TAG,"POST SNAPSHOT IS " + postSnapshot)
//                    val cartProduct = postSnapshot.getValue(ProductDataModel::class.java)
//                    val product = ProductDataModel(cartProduct.productType, cartProduct.category, cartProduct.productImage!!, cartProduct.getprice(), cartProduct.seller!!, cartProduct._id!!, cartProduct.productName!!, cartProduct.updateTime!!)
//                    totalPrice = totalPrice!! + cartProduct.getprice()!!
//                    cartProductList!!.add(product)
//                    productID!!.add(product._id!!)
//                }
//                if (cartProductList!!.isEmpty()) {
//                    ProgressDialogUtils.sharedInstance.hideProgressDialog()
//                    val toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
//                    val view = toast.view
//                    view.setBackgroundResource(R.drawable.rounded_square)
//                    val text = view.findViewById(android.R.id.message) as TextView
//                    text.text = " YOU GOT TO PUT SOMETHING IN THE CART! "
//                    text.setTextColor(resources.getColor(R.color.white))
//                    toast.show()
////                    val intent = Intent(context, UserActivity::class.java)
////                    this@CartActivity.startActivity(intent)
//                } else {
//                    adapter = CartAdapter(cartProductList!!,context, object : ProductListAdapter.ButtonClickListener {
//                        override fun onButtonClick(position: Int) {
//                            val deleteref = Firebase("https://ecommerce-demo-7f6de.firebaseio.com/Carts" + productID!![position])
//                            deleteref.removeValue()
//                            Snackbar.make(cartlistView, resources.getString(R.string.deleted_cart), Snackbar.LENGTH_LONG)
//                                    .setAction("Action", null).show()
//                            adapter?.notifyDataSetChanged()
//                            reloadView()
//                        }
//                    })
//                    cartlistView.adapter = adapter
//                }
//            }
//
//            override fun onCancelled(firebaseError: FirebaseError) {
//                println("The read failed: " + firebaseError.message)
//            }
//        })
//        ProgressDialogUtils.sharedInstance.hideProgressDialog()
//    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context as OnFragmentInteractionListener?
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun reloadView() {
        val ft = fragmentManager.beginTransaction()
        ft.detach(CartFragment.sharedInstance).attach(CartFragment.sharedInstance).commit()
    }
    interface OnFragmentInteractionListener
}
