package com.bluefox.joly.clientModule.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.clientModule.login.supportFuncations.RegisterActivityUI
import com.bluefox.joly.clientModule.profile.modalClass.SSProfileDetailsData
import com.bluefox.joly.clientModule.profile.supportFunctions.ProfileFragmentUI
import com.bluefox.joly.databinding.ActivityRegisterBinding
import com.bluefox.joly.zCommonFunctions.CallIntent

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var registerActivityUI: RegisterActivityUI


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickListeners()
    }

    private fun initViews() {
        registerActivityUI = RegisterActivityUI(this, binding,::onSubmitClicked)
    }

    private fun onClickListeners() {
        binding.showSignIn.setOnClickListener {
            CallIntent.gotoLogin(this, true, this)
        }
    }

    private fun onSubmitClicked(sSRegistrationDetailsData : SSRegistrationDetailsData)
    {
        Log.e("test","Name ${sSRegistrationDetailsData.name}")
    }
}