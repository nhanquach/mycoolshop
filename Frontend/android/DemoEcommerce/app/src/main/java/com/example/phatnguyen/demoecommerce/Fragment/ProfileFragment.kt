package com.example.phatnguyen.demoecommerce.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.example.phatnguyen.demoecommerce.Activity.LoginActivity
import com.example.phatnguyen.demoecommerce.R
import com.vlonjatg.progressactivity.ProgressFrameLayout
import android.view.MenuInflater
import android.graphics.Color
import com.example.phatnguyen.demoecommerce.Utils.DefaultSettings
import com.example.phatnguyen.demoecommerce.Utils.UserSettings
import android.text.style.ForegroundColorSpan
import android.text.SpannableString
import com.example.phatnguyen.demoecommerce.Utils.ProgressDialogUtils
import info.hoang8f.widget.FButton
import android.widget.TextView
import com.example.phatnguyen.demoecommerce.Activity.ManageOrdersActivity
import com.example.phatnguyen.demoecommerce.Database.database


/**
 * Created by phatnguyen on 11/11/17.
 */
class ProfileFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var progressRelativeLayout: ProgressFrameLayout

    companion object {
        val sharedInstance: ProfileFragment by lazy { Holder.INSTANCE }
    }

    private object Holder { val INSTANCE = ProfileFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.profile_fragment_layout, container, false)
        activity.title = "Profile"
        val manageOrdersBtn = rootView?.findViewById(R.id.manageOrdersBtn) as FButton
        val userName = rootView?.findViewById(R.id.user_profile_name) as TextView
        val userType = rootView?.findViewById(R.id.user_profile_type) as TextView
        userName.text = "Name: " + DefaultSettings.sharedInstance.getDefaults(UserSettings.name(),context)
        userType.text = "Type: " + DefaultSettings.sharedInstance.getDefaults(UserSettings.type(),context)
        if (DefaultSettings.sharedInstance.getDefaults(UserSettings.type(),context) == "user") {
            manageOrdersBtn.visibility = View.GONE
        }
        else {
            manageOrdersBtn.visibility = View.VISIBLE
        }
        manageOrdersBtn.setOnClickListener {
                val intent = Intent(context,ManageOrdersActivity::class.java)
                startActivity(intent)
        }
//        progressRelativeLayout = rootView?.findViewById(R.id.progress) as ProgressFrameLayout
//        val emptyDrawable = IconDrawable(context, Iconify.IconValue.zmdi_account)
//                .colorRes(android.R.color.darker_gray)
//        progressRelativeLayout.showEmpty(emptyDrawable,
//                "Empty Profile",
//                "There are no content to view now.")
        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @SuppressLint("WrongConstant")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_signout -> {
//                FirebaseAuth.getInstance().signOut()
                DefaultSettings.sharedInstance.setDefaults(UserSettings.isLogged(),"0",context)
                val db = context.database.writableDatabase
                db.close()
//                ProgressDialogUtils.sharedInstance.hideProgressDialog()
                val loginIntent = Intent(activity, LoginActivity::class.java)
                loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(loginIntent)
                return true
            }
        }

        return false
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_signout).isVisible = true
        val s = SpannableString("Sign Out")
        s.setSpan(ForegroundColorSpan(Color.BLACK), 0, s.length, 0)
        menu.findItem(R.id.action_signout).title = s
        super.onPrepareOptionsMenu(menu)

    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener
}
