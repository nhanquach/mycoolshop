package com.example.phatnguyen.demoecommerce.Utils

import android.util.Log
import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

/**
 * Created by phatnguyen on 1/6/18.
 */
class StringUtils {
    companion object {
        val sharedInstance: StringUtils by lazy { Holder.INSTANCE }
    }

    private object Holder { val INSTANCE = StringUtils() }

    val TAG = "String Utils"

    private val ser = JsonSerializer<Date> { src, typeOfSrc, context -> if (src == null) null else JsonPrimitive(src.time) }

    fun object2JSON(obj: Any?): String {
        if (obj != null) {
            try {
                val mapper = GsonBuilder().registerTypeAdapter(Date::class.java,
                        ser).create()
                return mapper.toJson(obj)
            } catch (e: Exception) {
                Log.e(TAG, "Convert object to JSON fail", e)
            }

        }

        return ""
    }
}