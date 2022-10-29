package com.codezilla.taskmanager.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codezilla.taskmanager.Model.TaskModel
import com.codezilla.taskmanager.Repository.AuthRepository
import com.codezilla.taskmanager.Repository.Repository

class AuthViewModel(application: Application) : AndroidViewModel(application), AuthRepository.Response{
    //USING LIVEDATA
    private var validityflag = MutableLiveData<Int>()
    private var repository: AuthRepository = AuthRepository(this,application)

    fun getValidityFlag():MutableLiveData<Int>
    {
        return validityflag
    }

    fun validateLogin(email:String,pass:String)
    {
        validityflag.value=-1
        repository.doLogin(email,pass)
    }
    fun validateSignup(email:String,pass:String)
    {
        validityflag.value=-1
        repository.doSignup(email,pass)
    }
    override fun SignupResponse(int: Int) {
        validityflag.setValue(int)
    }
    override fun LoginResponse(int: Int) {
        validityflag.setValue(int)
    }


}