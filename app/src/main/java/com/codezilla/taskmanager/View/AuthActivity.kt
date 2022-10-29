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

        supportActionBar!!.hide()

        Log.d("TAG", "HERE")

        authViewModel=ViewModelProvider(this).get(AuthViewModel::class.java)

        val fragmentLogin=LoginFragment()
        this.supportFragmentManager.beginTransaction().replace(R.id.fragContainer,fragmentLogin).commit()

    }

    override fun SignIn(email: String, pass: String) {
        authViewModel.validateLogin(email,pass)
        authViewModel.getValidityFlag().observe(this, Observer {
            if(it==0)
            {
                Toast.makeText(this,"Enter valid email and password ",Toast.LENGTH_SHORT).show()
            }
            else if(it==1)
            {
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
        var chk:Int=1
        authViewModel.validateSignup(email,pass)
        authViewModel.getValidityFlag().observe(this, Observer {
        if(it==0)
        {
            if(chk==1) {
                Toast.makeText(this, "Enter valid or unused email  ", Toast.LENGTH_SHORT).show()
                chk==0;
            }
        }
        else if(it==1)
        {
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