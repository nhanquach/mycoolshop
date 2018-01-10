package com.example.phatnguyen.demoecommerce.Utils

import java.util.*

/**
 * Created by phatnguyen on 1/6/18.
 */
class RandomTask {
    companion object {
        val sharedInstance: RandomTask by lazy { Holder.INSTANCE }
    }
    private object Holder { val INSTANCE = RandomTask() }

    fun randomGenarator(): Int {
        val rand = Random()
        return rand.nextInt(999999999)
    }
}