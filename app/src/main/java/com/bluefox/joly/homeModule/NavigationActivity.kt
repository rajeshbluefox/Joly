package com.bluefox.joly.homeModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.databinding.ActivityHomeBinding
import com.bluefox.joly.databinding.ActivityNavigationBinding
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bluefox.joly.zSharedPreference.UserDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBar(this)

        onClickListeners()
    }

    private fun onClickListeners() {
        binding.btServiceSeeker.setOnClickListener {
            UserDetails.saveUserRoleStatus(this,1)
            SSProfileData.UserRole = 1
            CallIntent.gotoLogin(this,false,this)
        }

        binding.btServiceProvider.setOnClickListener {
            UserDetails.saveUserRoleStatus(this,2)
            SSProfileData.UserRole = 2
            CallIntent.gotoLogin(this,false,this)
        }

        binding.btJobProvider.setOnClickListener {
            UserDetails.saveUserRoleStatus(this,3)
            SSProfileData.UserRole = 3
            CallIntent.gotoLogin(this,false,this)
        }

        binding.btJobSeeker.setOnClickListener {
            UserDetails.saveUserRoleStatus(this,4)
            SSProfileData.UserRole = 4
            CallIntent.gotoLogin(this,false,this)
        }
    }
}