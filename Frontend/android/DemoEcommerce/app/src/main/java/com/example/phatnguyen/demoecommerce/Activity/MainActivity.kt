package com.example.phatnguyen.demoecommerce.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.example.phatnguyen.demoecommerce.Fragment.AboutFragment
import com.example.phatnguyen.demoecommerce.Fragment.CartFragment
import com.example.phatnguyen.demoecommerce.Fragment.ProfileFragment
import com.example.phatnguyen.demoecommerce.Fragment.ProductFragment
import com.example.phatnguyen.demoecommerce.R
import com.example.phatnguyen.demoecommerce.Utils.ProgressDialogUtils
import com.malinskiy.materialicons.IconDrawable
import com.malinskiy.materialicons.Iconify
import me.riddhimanadib.library.BottomBarHolderActivity
import me.riddhimanadib.library.NavigationPage
import java.util.ArrayList

/**
 * Created by phatnguyen on 11/9/17.
 */
class MainActivity : BottomBarHolderActivity(), ProfileFragment.OnFragmentInteractionListener, ProductFragment.OnFragmentInteractionListener, CartFragment.OnFragmentInteractionListener, AboutFragment.OnFragmentInteractionListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val page1 = NavigationPage("Home", ContextCompat.getDrawable(this, R.drawable.ic_home_black_24dp), ProductFragment.sharedInstance)
        val page2 = NavigationPage("Cart", ContextCompat.getDrawable(this, R.drawable.ic_assessment_black_24dp), CartFragment.sharedInstance)
        val page3 = NavigationPage("Profile", ContextCompat.getDrawable(this, R.drawable.ic_person_black_24dp), ProfileFragment.sharedInstance)
        val page4 = NavigationPage("About", ContextCompat.getDrawable(this, R.drawable.ic_mail_black_24dp), AboutFragment.sharedInstance)

        val navigationPages = ArrayList<NavigationPage>()
        navigationPages.add(page1)
        navigationPages.add(page2)
        navigationPages.add(page3)
        navigationPages.add(page4)

        super.setupBottomBarHolderActivity(navigationPages)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        ProgressDialogUtils.sharedInstance.cancelProgressDialog()
    }

}