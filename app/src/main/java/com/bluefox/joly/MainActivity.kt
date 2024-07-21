package com.bluefox.joly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.clientModule.login.apiFunctions.LoginAPIFunctions
import com.bluefox.joly.clientModule.login.apiFunctions.LoginViewModel
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.login.supportFunctions.LoginUI
import com.bluefox.joly.databinding.ActivityMainBinding
import com.bluefox.joly.dummy.CallThemesViewModel
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bluefox.joly.zSharedPreference.UserDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var callThemesViewModel: CallThemesViewModel

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginAPIFunctions: LoginAPIFunctions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBar(this)


        callThemesViewModel = ViewModelProvider(this)[CallThemesViewModel::class.java]


        initViews()
        getUserLoginStatus()

//        getThemes()
//        observers()

    }

    private fun initViews() {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginAPIFunctions = LoginAPIFunctions(
            this,
            this,
            loginViewModel,
            ::onLoginResponse,
            onRegisterResponse = {})
    }

    private fun getUserLoginStatus() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (UserDetails.getLoginStatus(this)) {

                val loginData = LoginData()
                loginData.phoneNumber = UserDetails.getUserMobileNo(this)
                loginData.password = UserDetails.getUserPassword(this)
                SSProfileData.UserRole = UserDetails.getUserRoleStatus(this)
                postValidate(loginData)

            } else {
                CallIntent.gotoNavigationActivity(this, true, this)
            }
        }, 2000)
    }

    private fun observers() {
        callThemesViewModel.getThemesResponse().observe(this)
        {
            if (it.status == 200) {
                Log.e("Test", it.data.toString())
            } else {
                Log.e("Test", "No Response")
            }
        }
    }

    private fun getThemes() {
        callThemesViewModel.getThemes()
    }

    private fun postValidate(loginData: LoginData) {
        loginAPIFunctions.validateLogin(loginData)
    }

    private fun onLoginResponse(loginData: LoginData) {

        SSProfileData.openFrom = 1
        SSProfileData.mLoginData = loginData

        val userRole = UserDetails.getUserRoleStatus(this)
        SSProfileData.UserRole = userRole

        if (userRole <= 2) {
            CallIntent.gotoHomeActivity(this, true, this)
        } else {
            CallIntent.gotoJobHomeActivity(this, true, this)

        }

    }
}