package com.bluefox.joly.clientModule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.apiFunctions.LoginAPIFunctions
import com.bluefox.joly.clientModule.login.apiFunctions.LoginViewModel
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.login.supportFunctions.LoginUI
import com.bluefox.joly.clientModule.postJob.PostWorkFragment
import com.bluefox.joly.clientModule.profile.ProfileFragment
import com.bluefox.joly.clientModule.viewJob.ViewJobsFragment
import com.bluefox.joly.clientModule.viewServices.FindServicesFragment
import com.bluefox.joly.databinding.ActivityHomeBinding
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bluefox.joly.zSharedPreference.UserDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    private lateinit var loginViewModel : LoginViewModel
    private lateinit var loginAPIFunctions: LoginAPIFunctions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBar(this)
        StatusBarUtils.setTopPadding(resources,binding.appBarLt)

        initViews()

        fillViewServices()
        onClickListeners()
    }

    private fun initViews()
    {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginAPIFunctions = LoginAPIFunctions(this,this,loginViewModel,::onLoginResponse)

        if(SSProfileData.openFrom==0)
            postValidate()
    }

    private fun postValidate()
    {
        val loginData = LoginData()
        loginData.phoneNumber=UserDetails.getUserMobileNo(this)
        loginData.password=UserDetails.getUserMobileNo(this)

        loginAPIFunctions.validateLogin(loginData)
    }
    private fun onLoginResponse(loginData: LoginData)
    {
        SSProfileData.mLoginData=loginData
    }

    private fun onClickListeners() {
        binding.myJobBT.setOnClickListener {
            fillViewServices()
        }

        binding.postJobBT.setOnClickListener {
            fillPostWork()
        }

        binding.profileBT.setOnClickListener {
            fillProfile()
        }
    }

    private fun fillViewServices() {

        binding.tvAppBarTitle.text="Service Providers"

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, ViewJobsFragment())
            .commit()
    }

    private fun fillPostWork() {

        binding.tvAppBarTitle.text="Post Work"

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, PostWorkFragment())
            .commit()
    }


    private fun fillProfile() {

        binding.tvAppBarTitle.text="Profile"

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, ProfileFragment())
            .commit()
    }



}