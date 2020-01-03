package com.example.expansemanager.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.expansemanager.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private  var mUser: FirebaseUser? = null
    private lateinit var mEditTextUsername: EditText
    private lateinit var mEditTextPassword: EditText
    private lateinit var mStrUsername:String
    private lateinit var mStrPassword:String
    private lateinit var mBtnSignIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (supportActionBar!= null)
            supportActionBar?.hide()

        initViews()
    }

    private fun initViews() {

        mAuth = FirebaseAuth.getInstance()
        mEditTextUsername = findViewById(R.id.user_id_login)
        mEditTextPassword = findViewById(R.id.password_login)

        mBtnSignIn = findViewById(R.id.btn_sign_in)
        mBtnSignIn.setOnClickListener(View.OnClickListener {

            mStrUsername = mEditTextUsername.text.toString()
            mStrPassword = mEditTextPassword.text.toString()

            mAuth.signInWithEmailAndPassword(mStrUsername, mStrPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Tag", "signInWithEmail:success")
                        val user = mAuth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }

                    // ...
                }
        })
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        isLoggedIn()
    }


    private fun isLoggedIn() {
        mUser = FirebaseAuth.getInstance().currentUser
        if (mUser != null) {
            // User is signed in
            updateUI(mUser)
        }
    }
    private fun updateUI(user: FirebaseUser? )
    {
            val intent = Intent(this@LoginActivity,HomeActivity::class.java);
            var userName = user?.displayName
            var email = user?.email

            intent.putExtra("Username", userName)
            intent.putExtra("UserEmail", email)
            startActivity(intent);
    }
}
