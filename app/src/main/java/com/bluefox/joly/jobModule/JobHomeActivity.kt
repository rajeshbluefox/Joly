package com.bluefox.joly.jobModule

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.apiFunctions.LoginAPIFunctions
import com.bluefox.joly.clientModule.login.apiFunctions.LoginViewModel
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.profile.ProfileFragment
import com.bluefox.joly.databinding.ActivityJobHomeBinding
import com.bluefox.joly.jobModule.jobProviderModule.PostJobFragment
import com.bluefox.joly.jobModule.jobProviderModule.PostedJobsFragment
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.SelJobDetails
import com.bluefox.joly.jobModule.jobSeekerModule.ViewAllJobsFragment
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bluefox.joly.zSharedPreference.UserDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobHomeBinding

    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources, binding.appBarLt)

        initViews()
        fillPostJob()
        onClickListeners()
        getCurrentObserver()
    }

    fun initViews()
    {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        if(SSProfileData.UserRole==4)
        {
            binding.tvPostedWork.text="View Jobs"
            binding.tvPostWork.text="Applied Jobs"
        }
    }

    private fun getCurrentObserver() {
        loginViewModel.getFragment().observe(this) {
            Log.e("Test", "Fragment $it")
            if (it == 1) {
                fillPostedJobs()
            }
        }
    }


    private fun onClickListeners() {

        binding.myJobBT.setOnClickListener {
            fillPostedJobs()
        }

        binding.postJobBT.setOnClickListener {
            fillPostJob()
        }

        binding.profileBT.setOnClickListener {
            fillProfile()
        }
    }

    private fun fillProfile() {

        onChangeBackGround(3)

        binding.tvAppBarTitle.text = "Profile"

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, ProfileFragment())
            .commit()
    }

    private fun fillPostJob() {

        onChangeBackGround(2)

        if(SSProfileData.UserRole==3) {
            binding.tvAppBarTitle.text = "Post Job"

            supportFragmentManager.beginTransaction()
                .replace(R.id.containerFragment, PostJobFragment())
                .commit()
        }else{
            SelJobDetails.selFragmentJobSeeker=1
            binding.tvAppBarTitle.text = "Applied Jobs"

            supportFragmentManager.beginTransaction()
                .replace(R.id.containerFragment, ViewAllJobsFragment())
                .commit()
        }
    }

    private fun fillPostedJobs() {

        onChangeBackGround(1)

        if(SSProfileData.UserRole==3) {
            binding.tvAppBarTitle.text = "Posted Job"

            supportFragmentManager.beginTransaction()
                .replace(R.id.containerFragment, PostedJobsFragment())
                .commit()
        }else{
            SelJobDetails.selFragmentJobSeeker=0
            binding.tvAppBarTitle.text = "View Jobs"

            supportFragmentManager.beginTransaction()
                .replace(R.id.containerFragment, ViewAllJobsFragment())
                .commit()
        }
    }


    private fun onChangeBackGround(mSelectedItem: Int) {
        when (mSelectedItem) {
            1 -> {
                binding.myJobBT.setBackgroundResource(R.drawable.navi_bar_selected_bg)
                binding.icPostedWork.setImageResource(R.drawable.ic_posted_work_colored)
                val textColor1 = ContextCompat.getColor(this, R.color.navi_bar_text)
                binding.tvPostedWork.setTextColor(textColor1)

                binding.postJobBT.background = null
                binding.icPostWork.setImageResource(R.drawable.ic_post_work)
                val textColor2 = ContextCompat.getColor(this, R.color.black)
                binding.tvPostWork.setTextColor(textColor2)

                binding.profileBT.background = null
                binding.icProfile.setImageResource(R.drawable.ic_profile)
                val textColor3 = ContextCompat.getColor(this, R.color.black)
                binding.tvPostedWork.setTextColor(textColor3)
            }

            2 -> {
                binding.myJobBT.background = null
                binding.icPostedWork.setImageResource(R.drawable.ic_posted_work)
                val textColor1 = ContextCompat.getColor(this, R.color.black)
                binding.tvPostedWork.setTextColor(textColor1)

                binding.postJobBT.setBackgroundResource(R.drawable.navi_bar_selected_bg)
                binding.icPostWork.setImageResource(R.drawable.ic_post_work_colored)
                val textColor2 = ContextCompat.getColor(this, R.color.navi_bar_text)
                binding.tvPostWork.setTextColor(textColor2)

                binding.profileBT.background = null
                binding.icProfile.setImageResource(R.drawable.ic_profile)
                val textColor3 = ContextCompat.getColor(this, R.color.black)
                binding.tvPostedWork.setTextColor(textColor3)
            }

            3 -> {
                binding.myJobBT.background = null
                binding.icPostedWork.setImageResource(R.drawable.ic_posted_work)
                val textColor1 = ContextCompat.getColor(this, R.color.black)
                binding.tvPostedWork.setTextColor(textColor1)

                binding.postJobBT.background = null
                binding.icPostWork.setImageResource(R.drawable.ic_post_work)
                val textColor2 = ContextCompat.getColor(this, R.color.black)
                binding.tvPostWork.setTextColor(textColor2)

                binding.profileBT.setBackgroundResource(R.drawable.navi_bar_selected_bg)
                binding.icProfile.setImageResource(R.drawable.ic_profile_colored)
                val textColor3 = ContextCompat.getColor(this, R.color.navi_bar_text)
                binding.tvPostedWork.setTextColor(textColor3)
            }
        }

    }


}