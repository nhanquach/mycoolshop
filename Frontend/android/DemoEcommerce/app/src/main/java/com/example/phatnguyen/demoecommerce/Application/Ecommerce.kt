package com.example.phatnguyen.demoecommerce.Application

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import com.example.phatnguyen.demoecommerce.Database.CartDatabaseAPI
import com.example.phatnguyen.demoecommerce.Database.database
import com.example.phatnguyen.demoecommerce.Utils.ProgressDialogUtils
import com.firebase.client.Firebase
import org.jetbrains.anko.db.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by phatnguyen on 11/14/17.
 */
class Ecommerce: Application() {
    private val TAG = "Application"
    companion object {
        val sharedInstance: Ecommerce by lazy { Holder.INSTANCE }
    }
    private object Holder { val INSTANCE = Ecommerce() }

    val df = "yyyy-MM-dd HH:mm:ss"

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        createLocalDatabase()
//        Firebase.getDefaultConfig().isPersistenceEnabled = true
//        Firebase.setAndroidContext(this)
    }

    fun createLocalDatabase() {
        database.use {
            //Init cart table
            createTable(CartDatabaseAPI.TABLE_NAME, true,
                    CartDatabaseAPI.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                    CartDatabaseAPI.PRODUCT_ID to INTEGER,
                    CartDatabaseAPI.PRODUCT_IMAGE to TEXT,
                    CartDatabaseAPI.PRODUCT_NAME to TEXT,
                    CartDatabaseAPI.PRODUCT_PRICE to INTEGER,
                    CartDatabaseAPI.SELLER_NAME to TEXT,
                    CartDatabaseAPI.TOTAL_AMOUNT to INTEGER,
                    CartDatabaseAPI.TOTAL_MONEY to INTEGER,
                    CartDatabaseAPI.CREATED_TIME to TEXT)
        }
        Log.d(TAG,"Create local database successfully")
    }
}