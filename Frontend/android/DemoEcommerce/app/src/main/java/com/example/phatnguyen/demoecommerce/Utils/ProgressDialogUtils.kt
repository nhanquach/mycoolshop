package com.example.phatnguyen.demoecommerce.Utils

import android.app.Activity
import android.app.ProgressDialog
import android.support.annotation.VisibleForTesting

/**
 * Created by phatnguyen on 11/14/17.
 */
class ProgressDialogUtils {
    companion object {
        val sharedInstance: ProgressDialogUtils by lazy { Holder.INSTANCE }
    }
    private object Holder { val INSTANCE = ProgressDialogUtils() }
    @VisibleForTesting
    var mProgressDialog: ProgressDialog? = null

    fun showProgressDialog(activity: Activity,msg: String) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(activity)
            mProgressDialog!!.setMessage(msg)
            mProgressDialog!!.isIndeterminate = true
        }
            mProgressDialog!!.show()

    }
    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }

    }

    fun cancelProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing()) {
            mProgressDialog!!.cancel()
        }
    }

    fun setProgressValue(progressValue: Int) {
        mProgressDialog!!.progress = progressValue
    }

    fun showProgressDialogWithStyle(activity: Activity,msg: String, style: Int) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(activity)
            mProgressDialog!!.setCancelable(false)
            mProgressDialog!!.setMessage(msg)
            mProgressDialog!!.setProgressStyle(style)
            mProgressDialog!!.isIndeterminate = true
        }
        mProgressDialog!!.show()
    }
}