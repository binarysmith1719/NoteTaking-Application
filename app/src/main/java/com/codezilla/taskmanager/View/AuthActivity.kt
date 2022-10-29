package com.codezilla.taskmanager.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codezilla.taskmanager.R
import com.codezilla.taskmanager.ViewModel.AuthViewModel

//import com.google.firebase.auth.FirebaseAuth


class AuthActivity : AppCompatActivity(),CommunicateAuth {
    lateinit var authViewModel:AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
//        val getshpf = getSharedPreferences(MAIN_KEY, MODE_PRIVATE)
//        val value = getshpf.getBoolean("loggeddk", false)
//        if (value) {
//            val intent = Intent(this@loginAct, MainActivity::class.java)
//            finish()
//            startActivity(intent)
//        }
        supportActionBar!!.hide()
//        signupbutton = findViewById(R.id.btn_signup)
//        loginbutton = findViewById(R.id.btn_login)
//        editTextEmail = findViewById(R.id.edt_eml)
//        editTextPass = findViewById(R.id.edt_pass)
//        mAuth = FirebaseAuth.getInstance()
//        signupbutton.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this@loginAct, SignupAct::class.java)
//            startActivity(intent)
//        })
//        loginbutton!!.setOnClickListener(View.OnClickListener {
//            val email = editTextEmail?.getText().toString()
//            val password = editTextPass?.getText().toString()
//            loginfunc(email, password)
//        })
        Log.d("TAG", "HERE")

        authViewModel=ViewModelProvider(this).get(AuthViewModel::class.java)

//        authViewModel.validateSignup("subham1719@gmail.com","subham1719")
        val fragmentLogin=LoginFragment()
        this.supportFragmentManager.beginTransaction().replace(R.id.fragContainer,fragmentLogin).commit()


    }

    override fun SignIn(email: String, pass: String) {
        authViewModel.validateLogin(email,pass)
        authViewModel.getValidityFlag().observe(this, Observer {
            if(it==0)
            {
                Log.d("TAG", "sup it -> ${it}")

                Toast.makeText(this,"Enter valid email and password ",Toast.LENGTH_SHORT).show()
            }
            else if(it==1)
            {
                Log.d("TAG", "sup it -> ${it}")

                var shpf=getSharedPreferences("MAIN_KEY", MODE_PRIVATE)
                var editor=shpf.edit()
                editor.putBoolean("Logged_In",true)
                editor.apply()
                var intent= Intent(this@AuthActivity,MainActivity::class.java)
                finish()
                startActivity(intent)
            }
        })
    }

    override fun SignUp(email: String, pass: String) {
        Log.d("TAG", "signing in new")

        authViewModel.validateSignup(email,pass)
        authViewModel.getValidityFlag().observe(this, Observer {
        if(it==0)
        {
            Log.d("TAG", "sup it -> ${it}")

            Toast.makeText(this,"Enter valid email ",Toast.LENGTH_SHORT).show()
        }
        else if(it==1)
        {
            Log.d("TAG", "sup it -> ${it}")

            var shpf=getSharedPreferences("MAIN_KEY", MODE_PRIVATE)
            var editor=shpf.edit()
            editor.putBoolean("Logged_In",true)
            editor.apply()
            var intent= Intent(this@AuthActivity,MainActivity::class.java)
            finish()
            startActivity(intent)
        }
        })
    }


}