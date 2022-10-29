package com.codezilla.taskmanager.Repository

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthRepository {
    lateinit var reference: Response
    lateinit var application: Application
    private lateinit var auth: FirebaseAuth

    constructor(reference:Response,application: Application)
    {
        auth = FirebaseAuth.getInstance()
        this.application=application
        this.reference=reference
    }

    fun doSignup(email:String,pass:String)
    {
        Log.d("TAG", "dosignup")
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                if (it.isSuccessful) {
                    reference.SignupResponse(1)
                } else {
                    reference.SignupResponse(0)
                }
            }

    }

    fun doLogin(email:String,pass:String)
    {
//        Log.d("TAG", "dosignup")
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
            if (it.isSuccessful) {
                reference.LoginResponse(1)
            } else {
                reference.LoginResponse(0)
                Log.d("TAG", "FAILED")
            }
        }

    }

    interface Response
    {
        fun SignupResponse(int: Int)
        fun LoginResponse(int:Int)
    }
}


