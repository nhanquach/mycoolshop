package com.example.phatnguyen.demoecommerce.Utils

import org.apache.commons.io.IOUtils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

/**
 * Created by phatnguyen on 12/10/17.
 */
class NetworkUtils {
    companion object {
        val sharedInstance: NetworkUtils by lazy { Holder.INSTANCE }
    }
    private object Holder { val INSTANCE = NetworkUtils() }

    @Throws(Exception::class)
    fun readUrl(urlString: String) : String {
        val `in` = URL(urlString).openStream()
        try {
            return IOUtils.toString(`in`)
        } finally {
            IOUtils.closeQuietly(`in`)
        }

    }
}