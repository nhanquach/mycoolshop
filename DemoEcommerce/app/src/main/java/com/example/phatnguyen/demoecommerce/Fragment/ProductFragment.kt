package com.example.phatnguyen.demoecommerce.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.phatnguyen.demoecommerce.Adapter.ProductListAdapter
import com.example.phatnguyen.demoecommerce.DataModel.ProductDataModel
import com.example.phatnguyen.demoecommerce.R
import kotlin.collections.ArrayList
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.ContentValues
import android.widget.*
import com.example.phatnguyen.demoecommerce.DataModel.CategoryDataModel
import com.example.phatnguyen.demoecommerce.DataModel.SubCategoryDataModel
import com.example.phatnguyen.demoecommerce.Database.CartDatabaseAPI
import com.example.phatnguyen.demoecommerce.Database.database
import com.example.phatnguyen.demoecommerce.Utils.Constant
import com.example.phatnguyen.demoecommerce.Utils.NetworkUtils
import com.example.phatnguyen.demoecommerce.Utils.ProgressDialogUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.product_fragment_layout.*
import android.widget.EditText
import com.example.phatnguyen.demoecommerce.CustomView.DynamicValueTextView
import android.app.Activity
import com.example.phatnguyen.demoecommerce.DataModel.CartDataModel
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select


/**
 * Created by phatnguyen on 11/11/17.
 */
class ProductFragment : Fragment() {
    private val TAG = "ProductFrament"
    private var listener: OnFragmentInteractionListener? = null
//    private val productDisplayRef = Firebase("https://ecommerce-demo-7f6de.firebaseio.com/productDisplay")
    private var productList: ArrayList<ProductDataModel>? = null
    private var cateItems: ArrayList<String>? = null
    private var subCateItems: ArrayList<String>? = null

    companion object {
        val sharedInstance: ProductFragment by lazy { Holder.INSTANCE }
    }

    private object Holder { val INSTANCE = ProductFragment() }

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.product_fragment_layout, container, false)
//        if (!((context as Activity).isFinishing)) {
//            ProgressDialogUtils.sharedInstance.showProgressDialog(activity, "Loading ...")
//        }
        activity.title = "Home"
        productList = ArrayList<ProductDataModel>()
        cateItems = ArrayList<String>()
        subCateItems = ArrayList<String>()

        val productListView= rootView?.findViewById(R.id.product_list) as ListView
        val categorySpinner = rootView.findViewById(R.id.category) as Spinner
        val subCategorySpinner = rootView.findViewById(R.id.subCategory) as Spinner
        subCategorySpinner.dropDownHorizontalOffset
        categorySpinner.dropDownHorizontalOffset
//        val cateItems = arrayOf("Điện thoại", "Laptop")
//        val subMobileCate = arrayOf("Apple","Samsung")
//        val subLaptopCate = arrayOf("Dell","Asus")

        val loadCategoryData = Thread({
            Log.d(TAG, "runThread(): ${Thread.currentThread().name}")

            val json = NetworkUtils.Companion.sharedInstance.readUrl(Constant.SERVER_URL + "category")
            val category = Gson().fromJson(json, Array<CategoryDataModel>::class.java)

            for (item in category) {
                cateItems!!.add(item.name.toString())
            }
            //prevent from crashin
            if (activity == null) {
                return@Thread
            }

            if (cateItems!!.size > 0) {
                activity.runOnUiThread {
                    Log.i(TAG, "runOnUiThread")
                    val categoryAdapter = ArrayAdapter<String>(context,
                            R.layout.spinner_item, cateItems)
                    categoryAdapter.notifyDataSetChanged()
                    categorySpinner.adapter = categoryAdapter
                }
            }
         })



        val loadSubCategoryData = Thread({
            Log.d(TAG, "runThread(): ${Thread.currentThread().name}")

            val json = NetworkUtils.Companion.sharedInstance.readUrl(Constant.SERVER_URL + "subcategory")
            val subCategory = Gson().fromJson(json, Array<SubCategoryDataModel>::class.java)

            for (item in subCategory) {
                subCateItems!!.add(item.name.toString())
            }

            if (activity == null) {
                return@Thread
            }

            if (subCateItems!!.size > 0) {
                activity.runOnUiThread {
                    Log.i(TAG, "runOnUiThread")
                    val subCategoryAdapter = ArrayAdapter<String>(context,
                            R.layout.spinner_item, subCateItems)
                    subCategoryAdapter.notifyDataSetChanged()
                    subCategorySpinner.adapter = subCategoryAdapter
                }
            }
        })


        val loadProductData = Thread({
            Log.d(TAG, "runThread(): ${Thread.currentThread().name}")

            val json = NetworkUtils.Companion.sharedInstance.readUrl(Constant.SERVER_URL + "allproducts")
            val product = Gson().fromJson(json, Array<ProductDataModel>::class.java)

            for (item in product) {
                productList!!.add(item)
            }

            if (activity == null) {
                return@Thread
            }

            if (productList!!.size > 0) {
                activity.runOnUiThread {
                    Log.i(TAG, "runOnUiThread")
                    val adapter = ProductListAdapter(context, productList,
                            activity.windowManager.defaultDisplay.width,
                            object : ProductListAdapter.ButtonClickListener {
                                @SuppressLint("ShowToast")
                                override fun onButtonClick(position: Int) {
                                    Log.d(TAG, "Add" + position + " " + productList!![position].id + " " + "to cart")
                                    //Add cart here
                                    var didParseRow : Boolean = false
                                    var dt: DynamicValueTextView
                                    val db = context.database.writableDatabase
                                    val values = ContentValues()
                                    dt = productListView.getChildAt(position).findViewById(R.id.text_view) as DynamicValueTextView
                                    //Check if record is already existed we'll update quantity value, if not we'll insert new record
                                    db.select(CartDatabaseAPI.TABLE_NAME).exec {
                                        parseList(object : MapRowParser<Map<String, Any?>> {
                                            override fun parseRow(columns: Map<String, Any?>): Map<String, Any?> {
                                                Log.e("Your result", columns.toString())
                                                if (productList!![position].id == (columns[CartDatabaseAPI.PRODUCT_ID] as Long).toInt()) {
                                                    val oldQuantity = (columns[CartDatabaseAPI.TOTAL_AMOUNT] as Long).toInt()
                                                    if (dt != null) {
                                                        val currQuantity = dt.getValues()
                                                        val newQuantity = currQuantity + oldQuantity
                                                        val updateValues = ContentValues()
                                                        updateValues.put(CartDatabaseAPI.TOTAL_AMOUNT, newQuantity)
                                                        db.update(CartDatabaseAPI.TABLE_NAME, updateValues, CartDatabaseAPI.PRODUCT_ID + "=" + productList!![position].id.toString(), null)
                                                        Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
                                                        didParseRow = true
                                                    }
                                                }
                                                else {
                                                    values.put(CartDatabaseAPI.PRODUCT_ID, productList!![position].id)
                                                    values.put(CartDatabaseAPI.PRODUCT_IMAGE, productList!![position].image)
                                                    values.put(CartDatabaseAPI.PRODUCT_NAME, productList!![position].name)
                                                    values.put(CartDatabaseAPI.PRODUCT_PRICE, productList!![position].price)
                                                    values.put(CartDatabaseAPI.SELLER_NAME, productList!![position].seller)
                                                    if (dt != null) {
                                                        values.put(CartDatabaseAPI.TOTAL_AMOUNT, dt.getValues())
                                                    }
                                                    values.put(CartDatabaseAPI.TOTAL_MONEY, productList!![position].price)
                                                    values.put(CartDatabaseAPI.CREATED_TIME, System.currentTimeMillis().toString())
                                                    db.insert(CartDatabaseAPI.TABLE_NAME, null, values)
                                                    Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
                                                    didParseRow = true
                                                }
                                                return columns
                                            }
                                        }
                                        )
                                    }
                                    if (didParseRow == false) {
                                        values.put(CartDatabaseAPI.PRODUCT_ID, productList!![position].id)
                                        values.put(CartDatabaseAPI.PRODUCT_IMAGE, productList!![position].image)
                                        values.put(CartDatabaseAPI.PRODUCT_NAME, productList!![position].name)
                                        values.put(CartDatabaseAPI.PRODUCT_PRICE, productList!![position].price)
                                        values.put(CartDatabaseAPI.SELLER_NAME, productList!![position].seller)
                                        if (dt != null) {
                                            values.put(CartDatabaseAPI.TOTAL_AMOUNT, dt.getValues())
                                        }
                                        values.put(CartDatabaseAPI.TOTAL_MONEY, productList!![position].price)
                                        values.put(CartDatabaseAPI.CREATED_TIME, System.currentTimeMillis().toString())
                                        db.insert(CartDatabaseAPI.TABLE_NAME, null, values)
                                        Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            })
                    adapter.notifyDataSetChanged()
                    productListView.adapter = adapter
//                    ProgressDialogUtils.sharedInstance.hideProgressDialog()
                }
            }
            else {
//                ProgressDialogUtils.sharedInstance.hideProgressDialog()
            }
        })

        loadCategoryData.start()

        loadSubCategoryData.start()

        loadProductData.start()



//        categorySpinner.onItemSelectedListener = object : OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View?,
//                                        position: Int, id: Long) {
////                Log.v("item", parent.getItemAtPosition(position) as String)
////                if ((parent.getItemAtPosition(position) as String) == "Điện thoại") {
////                    loadAdapter("Điện thoại",productListView,"Category",rootView)
////                    val subCategoryAdapter = ArrayAdapter<String>(context,
////                            R.layout.spinner_item, subMobileCate)
////                    subCategoryAdapter.notifyDataSetChanged()
////                    subCategorySpinner.adapter = subCategoryAdapter
////                }
////                else if ((parent.getItemAtPosition(position) as String) == "Laptop") {
////                    loadAdapter("Laptop",productListView,"Category",rootView)
////                    val subCategoryAdapter = ArrayAdapter<String>(context,
////                            R.layout.spinner_item, subLaptopCate)
////                    subCategoryAdapter.notifyDataSetChanged()
////                    subCategorySpinner.adapter = subCategoryAdapter
////                }
//
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//                // TODO Auto-generated method stub
//            }
//        }



//        subCategorySpinner.onItemSelectedListener = object : OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View?,
//                                        position: Int, id: Long) {
////                if ((categorySpinner.selectedItem as String) == "Điện thoại") {
////                    if ((parent.getItemAtPosition(position) as String) == "Apple") {
////                        loadAdapter("Apple", productListView, "SubCategory",rootView)
////                    } else if ((parent.getItemAtPosition(position) as String) == "Samsung") {
////                        loadAdapter("Samsung", productListView, "SubCategory",rootView)
////                    }
////                 }
////                else if ((categorySpinner.selectedItem as String) == "Laptop") {
////                    if ((parent.getItemAtPosition(position) as String) == "Dell") {
////                        loadAdapter("Dell", productListView, "SubCategory",rootView)
////                    } else if ((parent.getItemAtPosition(position) as String) == "Asus") {
////                        loadAdapter("Asus", productListView, "SubCategory",rootView)
////                    }
////                }
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//        }

        return rootView
    }


//    fun loadAdapter(filterString: String,listView: ListView,type: String,view: View) {
//        productDisplayRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                resetViewData()
//                for (postSnapshot in dataSnapshot.children) {
//                    val post = postSnapshot.getValue<ProductDataModel>(ProductDataModel::class.java)
//                    if (type == "Category") {
//                        if (post.category == filterString) {
//                            val instanceProduct = ProductDataModel(post.productType,post.category,post.productImage.toString(),post.getprice()!!,post.seller.toString(),post._id.toString(),post.productName.toString(),post.updateTime!!)
//                            productList!!.add(instanceProduct)
//                            Log.d(TAG, "Fetching" + post._id.toString())
//                        }
//                    }
//                    else if (type == "SubCategory") {
//                        if (post.productType == filterString) {
//                            val instanceProduct = ProductDataModel(post.productType,post.category,post.productImage.toString(),post.getprice()!!,post.seller.toString(),post._id.toString(),post.productName.toString(),post.updateTime!!)
//                            productList!!.add(instanceProduct)
//                            Log.d(TAG, "Fetching" + post._id.toString())
//                        }
//                    }
//                }
//
//                val adapter = ProductListAdapter(context, productList,
//                        activity.windowManager.defaultDisplay.width,
//                        object : ProductListAdapter.ButtonClickListener {
//                            override fun onButtonClick(position: Int) {
//                                Log.d(TAG,"Insert" + position + " " + productList!![position]._id + " " + "to adapter")
//                                val intentProduct = ProductDataModel(productList!![position].productType,
//                                        productList!![position].category,
//                                        productList!![position].productImage,
//                                        productList!![position].getprice(),
//                                        productList!![position].seller,
//                                        productList!![position]._id,
//                                        productList!![position].productName,
//                                        productList!![position].updateTime)
//                                System.out.println("INTENT PRODUCT PRODUCT ID" + intentProduct._id.toString())
//                                cartButtonClick(position, view, productList!!, productDisplayRef)
////                                val sellerDetailIntent = Intent(activity, ProductDetailActivity::class.java)
////                                sellerDetailIntent.putExtra("IntentProduct", intentProduct)
////                                activity.startActivity(sellerDetailIntent)
//                            }
//                        })
//                adapter.notifyDataSetChanged()
//                listView.adapter = adapter
//            }
//
//            override fun onCancelled(firebaseError: FirebaseError) {
//                Log.e(TAG,"The read failed: " + firebaseError.message)
//            }
//        })
//    }

//    fun cartButtonClick(position: Int, v: View, productList: ArrayList<ProductDataModel>, ref: Firebase) {
//        val addedInfo = v.findViewById(R.id.addedInfo) as TextView
//        addedInfo.visibility = View.VISIBLE
//        addedInfo.text = "This product is in your cart"
//        val product = productList[position]
//        val cartProduct = ProductDataModel(product.productType,product.category,product.productImage.toString(),product.getprice()!!,product.seller.toString(),product._id.toString(),product.productName.toString(),product.updateTime!!)
//        ref.child(cartProduct._id).setValue(cartProduct)
//        UpdateCartCounterInFirebase()
//    }

//    fun resetViewData() {
//        productList?.clear()
//    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener
}