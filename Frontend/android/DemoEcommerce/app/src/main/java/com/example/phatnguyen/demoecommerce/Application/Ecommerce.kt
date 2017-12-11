package com.example.phatnguyen.demoecommerce.Application

import android.app.Application
import com.firebase.client.Firebase

/**
 * Created by phatnguyen on 11/14/17.
 */
class Ecommerce: Application() {
    override fun onCreate() {
        super.onCreate()
//        Firebase.getDefaultConfig().isPersistenceEnabled = true
//        Firebase.setAndroidContext(this)
    }
}