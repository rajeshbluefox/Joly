package com.bluefox.joly.clientModule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.postJob.PostWorkFragment
import com.bluefox.joly.clientModule.profile.ProfileFragment
import com.bluefox.joly.clientModule.viewServices.FindServicesFragment
import com.bluefox.joly.databinding.ActivityHomeBinding
import com.bluefox.joly.zCommonFunctions.StatusBarUtils

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBar(this)
        StatusBarUtils.setTopPadding(resources,binding.appBarLt)

        fillViewServices()
        onClickListeners()
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
            .replace(R.id.containerFragment, FindServicesFragment())
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