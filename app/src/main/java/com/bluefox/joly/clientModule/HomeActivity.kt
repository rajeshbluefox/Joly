package com.bluefox.joly.clientModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.postJob.PostJobFragment
import com.bluefox.joly.clientModule.profile.ProfileFragment
import com.bluefox.joly.clientModule.viewJob.ViewJobsFragment
import com.bluefox.joly.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillViewJob()
        onClickListeners()
    }

    private fun onClickListeners() {
        binding.myJobBT.setOnClickListener {
            fillViewJob()
        }

        binding.postJobBT.setOnClickListener {
            fillPostJob()
        }

        binding.profileBT.setOnClickListener {
            fillProfile()
        }
    }

    private fun fillViewJob() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, ViewJobsFragment())
            .commit()
    }

    private fun fillPostJob() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, PostJobFragment())
            .commit()
    }

    private fun fillProfile() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, ProfileFragment())
            .commit()
    }



}