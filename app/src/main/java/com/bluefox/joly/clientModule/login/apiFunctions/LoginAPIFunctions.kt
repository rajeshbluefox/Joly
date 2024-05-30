package com.bluefox.joly.clientModule.login.apiFunctions

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions

class LoginAPIFunctions(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    loginViewModel : LoginViewModel,
    private val onLoginResponse: (loginData: LoginData) -> Unit,
    private val onRegisterResponse: () -> Unit,
    ) {

    private var mContext : Context
    private var mLifecycleOwner :  LifecycleOwner
    private var mLoginViewModel : LoginViewModel

    init {
        mContext = context
        mLifecycleOwner=lifecycleOwner
        mLoginViewModel=loginViewModel
    }
     fun validateLogin(loginData: LoginData)
    {
        mLoginViewModel.resetLoginResponse()
        mLoginViewModel.validateLogin(loginData)
        getValidateObserver()
    }

    private fun getValidateObserver()
    {
        mLoginViewModel.getLoginResponse().observe(mLifecycleOwner){

            if(it.status==200)
            {
                onLoginResponse.invoke(it.data!!)
            }else{
                UtilFunctions.showToast(mContext,"Wrong Password")
                Log.e("Test","Login Error")
            }
        }

    }

    fun ssRegister(ssRegistrationDetailsData: SSRegistrationDetailsData)
    {
        mLoginViewModel.resetSSRegisterResponse()
        mLoginViewModel.ssRegister(ssRegistrationDetailsData)
        getssRegisterObserver()
    }

    private fun getssRegisterObserver()
    {
        mLoginViewModel.getSSRegisterResponse().observe(mLifecycleOwner){

            if(it.status==200)
            {
                onRegisterResponse.invoke()
                UtilFunctions.showToast(mContext,"Registration Sucessfull")

            }else{
                UtilFunctions.showToast(mContext,"Wrong Password")
                Log.e("Test","Login Error")
            }
        }

    }
}