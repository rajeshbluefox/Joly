package com.bluefox.joly.clientModule.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.clientModule.login.apiFunctions.LoginAPIFunctions
import com.bluefox.joly.clientModule.login.apiFunctions.LoginViewModel
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.login.supportFunctions.LoginUI
import com.bluefox.joly.databinding.ActivityLoginBinding
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zSharedPreference.UserDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var loginUI: LoginUI

    private lateinit var loginViewModel : LoginViewModel
    private lateinit var loginAPIFunctions: LoginAPIFunctions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        onClickListeners()
    }

    private fun initViews()
    {
        loginUI = LoginUI(this,binding,::postValidate)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginAPIFunctions = LoginAPIFunctions(this,this,loginViewModel,::onLoginResponse)
    }

    private fun onClickListeners() {
        binding.showRegister.setOnClickListener {
            CallIntent.gotoRegister(this,true,this)
        }

//        binding.btLogin.setOnClickListener {
//            CallIntent.gotoHomeActivity(this,true,this)
//        }
    }

    private fun postValidate(loginData: LoginData)
    {
        loginAPIFunctions.validateLogin(loginData)
    }

    private fun onLoginResponse(loginData: LoginData)
    {
        //Code after Login response is received
        // SAVE THE USER details to Shared Preference
        UserDetails.saveLoginStatus(this,true)
        UserDetails.saveUserMobileNo(this,loginData.phoneNumber!!)
        UserDetails.saveUserPassword(this,loginData.password!!)

        SSProfileData.openFrom=1
        SSProfileData.mLoginData=loginData
        CallIntent.gotoHomeActivity(this,true,this)

    }

}