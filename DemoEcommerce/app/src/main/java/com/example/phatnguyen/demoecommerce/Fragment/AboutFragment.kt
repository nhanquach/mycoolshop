package com.example.phatnguyen.demoecommerce.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.phatnguyen.demoecommerce.R
import com.malinskiy.materialicons.IconDrawable
import com.malinskiy.materialicons.Iconify
import com.vlonjatg.progressactivity.ProgressFrameLayout

/**
 * Created by phatnguyen on 11/11/17.
 */
class AboutFragment: Fragment() {
    private var listener: OnFragmentInteractionListener? = null
//    private lateinit var progressRelativeLayout: ProgressFrameLayout

    companion object {
        val sharedInstance: AboutFragment by lazy { Holder.INSTANCE }
    }

    private object Holder { val INSTANCE = AboutFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.about_fragment_layout, container, false)
        activity.title = "About"
//        progressRelativeLayout = rootView?.findViewById(R.id.progressAbout) as ProgressFrameLayout
//        val emptyDrawable = IconDrawable(context, Iconify.IconValue.zmdi_account_box_mail)
//                .colorRes(android.R.color.darker_gray)
//        progressRelativeLayout.showEmpty(emptyDrawable,
//                "Empty About",
//                "There are no content to view now.")
        return rootView
    }

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

    interface OnFragmentInteractionListener {
    }

}