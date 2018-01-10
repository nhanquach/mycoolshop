package com.example.phatnguyen.demoecommerce.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatButton
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.phatnguyen.demoecommerce.DataModel.UserDataModel
import com.example.phatnguyen.demoecommerce.R
import com.example.phatnguyen.demoecommerce.R.id.*
import com.example.phatnguyen.demoecommerce.Utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson

/**
 * Created by phatnguyen on 11/14/17.
 */
class LoginActivity: AppCompatActivity(), View.OnClickListener {
    val TAG = "LoginActivity"
    val REQUEST_SIGNUP: Int = 0
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mEmail: EditText
    private lateinit var mPassWord: EditText
    private lateinit var mLoginBtn: AppCompatButton
    private var mUser: ArrayList<UserDataModel>? = null

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val actionBar = supportActionBar
        actionBar!!.hide()
        mUser = ArrayList<UserDataModel>()
        mEmail = findViewById(input_email) as EditText
        mPassWord = findViewById(input_password) as EditText
        mLoginBtn = findViewById(btn_login) as AppCompatButton
//        mAuth = FirebaseAuth.getInstance()
        mLoginBtn?.setOnClickListener(this)
        //Turn on auto login
//        DefaultSettings.sharedInstance.setDefaults(UserSettings.isLogged(),"1",applicationContext)
        if (DefaultSettings.sharedInstance.getDefaults(UserSettings.isLogged(),applicationContext).equals("1")) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onClick(p0: View?) {
        var i = p0?.id
        if (i == R.id.btn_login) {
            signIn(mEmail.text.toString(),mPassWord.text.toString())
        }
    }

    public override fun onStart() {
        super.onStart()
//        val currentUser = mAuth.currentUser
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:" + email)
        if (!validate()) {
            return
        }
        val randomThread = Thread({
            this.runOnUiThread {
                ProgressDialogUtils.sharedInstance.showProgressDialog(this, "Signing...")
            }

        })

        randomThread.start()

        val loginThread = Thread({
            Log.d(TAG, "runThread(): ${Thread.currentThread().name}")

            val json = NetworkUtils.Companion.sharedInstance.readUrl(Constant.SERVER_URL + "login")
            val user = Gson().fromJson(json, Array<UserDataModel>::class.java)

            for (item in user) {
                mUser!!.add(item)
                if (email == item.email && password == item.password) {
                        DefaultSettings.sharedInstance.setDefaults(UserSettings.type(),item.userType.toString(),applicationContext)
                        DefaultSettings.sharedInstance.setDefaults(UserSettings.name(),item.username.toString(),applicationContext)
                        DefaultSettings.sharedInstance.setDefaults(UserSettings.isLogged(),"1",applicationContext)
                        DefaultSettings.sharedInstance.setIntDefaults(UserSettings.id(),item._id,applicationContext)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                }
            }
            ProgressDialogUtils.sharedInstance.hideProgressDialog()
            Log.w(TAG, "signInWithEmail:failure")
            if (DefaultSettings.sharedInstance.getDefaults(UserSettings.isLogged(),applicationContext).equals("0")) {
                this.runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                }
            }

        })

        loginThread.start()

//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        Log.d(TAG, "signInWithEmail:success")
//                        val user = mAuth.currentUser
//                        DefaultSettings.sharedInstance.setDefaults(UserSettings.isLogged(),"1",applicationContext)
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                    } else {
//                        Log.w(TAG, "signInWithEmail:failure", task.exception)
//                        Toast.makeText(this@LoginActivity, "Authentication failed.",
//                                Toast.LENGTH_SHORT).show()
//                    }
//
//                    if (!task.isSuccessful) {
//                        Toast.makeText(this@LoginActivity, "Authentication failed.",
//                                Toast.LENGTH_SHORT).show()
//                    }
//                    ProgressDialogUtils.sharedInstance.hideProgressDialog()
//                }
    }
    public override fun onStop() {
        super.onStop()
        ProgressDialogUtils.sharedInstance.hideProgressDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        ProgressDialogUtils.sharedInstance.cancelProgressDialog()
    }

//    fun signOut() {
//        mAuth.signOut()
//
//    }

//    private fun sendEmailVerification() {
//        findViewById(R.id.btn_login).isEnabled = false
//
//        val user = mAuth.currentUser
//        user!!.sendEmailVerification()
//                .addOnCompleteListener(this) { task ->
//                    // [START_EXCLUDE]
//                    // Re-enable button
//                    findViewById(R.id.btn_login).isEnabled = true
//
//                    if (task.isSuccessful) {
//                        Toast.makeText(this@LoginActivity,
//                                "Verification email sent to " + user.email!!,
//                                Toast.LENGTH_SHORT).show()
//                    } else {
//                        Log.e(TAG, "sendEmailVerification", task.exception)
//                        Toast.makeText(this@LoginActivity,
//                                "Failed to send verification email.",
//                                Toast.LENGTH_SHORT).show()
//                    }
//                }
//    }

//    private fun createAccount(email: String, password: String) {
//        Log.d(TAG, "createAccount:" + email)
//        if (!validate()) {
//            return
//        }
//
//        showProgressDialog()
//
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(FragmentActivity.TAG, "createUserWithEmail:success")
//                        val user = mAuth.currentUser
//                        updateUI(user)
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(FragmentActivity.TAG, "createUserWithEmail:failure", task.exception)
//                        Toast.makeText(this@EmailPasswordActivity, "Authentication failed.",
//                                Toast.LENGTH_SHORT).show()
//                        updateUI(null)
//                    }
//
//                    hideProgressDialog()
//                }
//    }

//        override fun onClick(v: View) {
//            val i = v.id
//            if (i == R.id.btn_login) {
//                signIn(mEmail?.text.toString(), mPassWord?.text.toString())
//            }
//        }

    fun validate(): Boolean {
        var valid = true

        val email = mEmail?.text.toString()
        if (TextUtils.isEmpty(email)) {
            mEmail?.error = "Required."
            valid = false
        } else {
            mEmail?.error = null
        }

        val password = mPassWord?.text.toString()
        if (TextUtils.isEmpty(password)) {
            mPassWord?.error = "Required."
            valid = false
        } else {
            mPassWord?.error = null
        }

        return valid
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == Activity.RESULT_OK) {

                // TODO: Implement successful signup logic here
                this.finish()
            }
        }
    }



    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }

}