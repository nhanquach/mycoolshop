package com.example.phatnguyen.demoecommerce.Fragment

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.phatnguyen.demoecommerce.Adapter.CartAdapter
import com.example.phatnguyen.demoecommerce.Adapter.ProductListAdapter
import com.example.phatnguyen.demoecommerce.DataModel.ProductDataModel
import com.example.phatnguyen.demoecommerce.R
import com.example.phatnguyen.demoecommerce.Utils.ProgressDialogUtils
import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import com.firebase.client.ValueEventListener
import com.malinskiy.materialicons.IconDrawable
import com.malinskiy.materialicons.Iconify
import com.vlonjatg.progressactivity.ProgressFrameLayout
import kotlinx.android.synthetic.main.cart_fragment_layout.*
import java.util.ArrayList

/**
 * Created by phatnguyen on 11/11/17.
 */
class CartFragment: Fragment() {
    private  val TAG = "CartFragment"
//    private var cartProductList: ArrayList<ProductDataModel>? = null
//    private var productID: ArrayList<String>? = null
//    internal var adapter: CartAdapter? = null
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
        progressRelativeLayout = rootView?.findViewById(R.id.progress) as ProgressFrameLayout
        val emptyDrawable = IconDrawable(context, Iconify.IconValue.zmdi_shopping_cart)
                .colorRes(android.R.color.darker_gray)
        progressRelativeLayout.showEmpty(emptyDrawable,
                "Empty Cart",
                "There are no content to view now.")
//        val cartlistView = rootView?.findViewById(R.id.cartlistView) as ListView
//        val CheckoutButton = rootView?.findViewById(R.id.Checkoutbutton) as info.hoang8f.widget.FButton
//        cartProductList = ArrayList()
//        productID = ArrayList()
//        ProgressDialogUtils.sharedInstance.showProgressDialogWithStyle(activity,"LOADING CART", ProgressDialog.STYLE_SPINNER)
//        loadAdapter()
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
