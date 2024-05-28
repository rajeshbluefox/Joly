package com.bluefox.joly.clientModule.login.supportFunctions

import android.content.Context
import android.view.View
import android.widget.EditText
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.databinding.ActivityLoginBinding
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions

class LoginUI(
    context: Context,
    binding: ActivityLoginBinding,
    private val onLoginSubmitted: (loginData: LoginData) -> Unit,
) {
    private val mBinding: ActivityLoginBinding
    private val mContext: Context

    init {
        mContext = context
        mBinding = binding

        onClickListeners()
    }

    //fun to get login details and give a call back to activity

    private fun onClickListeners() {
        mBinding.btLogin.setOnClickListener {

            getDetails()
        }
    }

    private var isValidationSuccessfull = true

    private fun getDetails() {
        val nPhoneNumber = getValue(mBinding.etUserName, "Phone Number")
        val nPassword = getValue(mBinding.etPassword, "Password")

        if(isValidationSuccessfull) {
            val loginData = LoginData()
            loginData.phoneNumber = nPhoneNumber
            loginData.password = nPassword

            onLoginSubmitted.invoke(loginData)
        }else{
            UtilFunctions.showToast(mContext,"Validation Failed")
        }
    }

    fun getValue(view: EditText, editTextName: String): String {
        val nValue = view.text.toString()

        if (nValue.isEmpty()) {
            isValidationSuccessfull = false
            UtilFunctions.showToast(mContext, "$editTextName is Missing")
        }

        return nValue
    }
}