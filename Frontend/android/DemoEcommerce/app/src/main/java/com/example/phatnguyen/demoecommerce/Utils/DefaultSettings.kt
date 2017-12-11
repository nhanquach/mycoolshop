package com.example.phatnguyen.demoecommerce.Utils

import android.content.Context
import android.preference.PreferenceManager

/**
 * Created by phatnguyen on 11/18/17.
 */
class DefaultSettings {
    companion object {
        val sharedInstance: DefaultSettings by lazy { Holder.INSTANCE }
    }

    private object Holder { val INSTANCE = DefaultSettings() }

    fun setDefaults(key: String, value: String, context: Context) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getDefaults(key: String, context: Context): String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(key, "")
    }
}