package com.bluefox.joly.clientModule.login.apiFunctions

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions

class LoginAPIFunctions(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    loginViewModel : LoginViewModel,
    private val onLoginResponse: (loginData: LoginData) -> Unit,
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
}