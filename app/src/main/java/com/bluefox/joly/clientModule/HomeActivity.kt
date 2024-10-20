package com.bluefox.joly.clientModule

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
import com.bluefox.joly.clientModule.postJob.PostWorkFragment
import com.bluefox.joly.clientModule.postJob.modalClass.HomeTitleUpdater
import com.bluefox.joly.clientModule.profile.ProfileFragment
import com.bluefox.joly.clientModule.viewJob.ViewWorksFragment
import com.bluefox.joly.databinding.ActivityHomeBinding
import com.bluefox.joly.serviceProviderModule.SPAddServicesFragment
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bluefox.joly.zSharedPreference.UserDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), HomeTitleUpdater {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginAPIFunctions: LoginAPIFunctions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources, binding.appBarLt)

        setBottomNavigationBarText()
        initViews()

        fillViewServices()
        onClickListeners()
        getCurrentObserver()
    }

    private fun setBottomNavigationBarText() {
        if (SSProfileData.UserRole == 1) {
            binding.tvPostedWork.text = "Posted Works"
            binding.tvPostWork.text = "Post Work"
            binding.tvProfile.text = "Profile"
        } else {
            binding.tvPostedWork.text = "Works"
            binding.tvPostWork.text = "My Services"
            binding.tvProfile.text = "Profile"
        }
    }

    private fun getCurrentObserver() {
        loginViewModel.getFragment().observe(this) {
            Log.e("Test", "Fragment $it")
            if (it == 1) {
                fillViewServices()
            }
        }
    }

    private fun initViews() {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginAPIFunctions = LoginAPIFunctions(
            this,
            this,
            loginViewModel,
            ::onLoginResponse,
            onRegisterResponse = {})

//        if (SSProfileData.openFrom == 0)
//            postValidate()
    }

    private fun postValidate() {
        val loginData = LoginData()
        loginData.phoneNumber = UserDetails.getUserMobileNo(this)
        loginData.password = UserDetails.getUserPassword(this)

        loginAPIFunctions.validateLogin(loginData)
    }

    private fun onLoginResponse(loginData: LoginData) {
        SSProfileData.mLoginData = loginData
    }

    private fun onClickListeners() {
        binding.myJobBT.setOnClickListener {
            fillViewServices()
            onChangeBackGround(1)
        }

        binding.postJobBT.setOnClickListener {
            onChangeBackGround(2)

            if (binding.tvPostWork.text.toString() == "My Services") {
                fillServicesOffered()
            } else {
                fillPostWork()
            }
        }

        binding.profileBT.setOnClickListener {
            fillProfile()
            onChangeBackGround(3)
        }
    }

    private fun fillViewServices() {

        onChangeBackGround(1)

        binding.tvAppBarTitle.text = "Posted Works"

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, ViewWorksFragment())
            .commit()
    }

    private fun fillPostWork() {

        binding.tvAppBarTitle.text = "Post Work"

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, PostWorkFragment())
            .commit()
    }


    private fun fillProfile() {

        binding.tvAppBarTitle.text = "Profile"

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, ProfileFragment())
            .commit()
    }


    //Service Provider

    private fun fillServicesOffered() {

        binding.tvAppBarTitle.text = "Services Offered"

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, SPAddServicesFragment())
            .commit()
    }

    override fun updateTitle(newTitle: String,fragment: Int) {
        binding.tvAppBarTitle.text = newTitle

        when(fragment)
        {
            1 -> {
                fillViewServices()
            }
        }

    }

    private fun onChangeBackGround(mSelectedItem: Int) {
        when (mSelectedItem) {
            1 -> {
                binding.myJobBT.setBackgroundResource(R.drawable.navi_bar_selected_bg)
                binding.icPostedWork.setImageResource(R.drawable.ic_posted_work_colored)
                val textColor1 = ContextCompat.getColor(this, R.color.bttm_nav_sel_text_color)
                binding.tvPostedWork.setTextColor(textColor1)

                binding.postJobBT.background = null
                binding.icPostWork.setImageResource(R.drawable.ic_post_work)
                val textColor2 = ContextCompat.getColor(this, R.color.bttm_nav_text_color)
                binding.tvPostWork.setTextColor(textColor2)

                binding.profileBT.background = null
                binding.icProfile.setImageResource(R.drawable.ic_profile)
                val textColor3 = ContextCompat.getColor(this, R.color.bttm_nav_text_color)
                binding.tvProfile.setTextColor(textColor3)
            }

            2 -> {
                binding.myJobBT.background = null
                binding.icPostedWork.setImageResource(R.drawable.ic_posted_work)
                val textColor1 = ContextCompat.getColor(this, R.color.bttm_nav_text_color)
                binding.tvPostedWork.setTextColor(textColor1)

                binding.postJobBT.setBackgroundResource(R.drawable.navi_bar_selected_bg)
                binding.icPostWork.setImageResource(R.drawable.ic_post_work_colored)
                val textColor2 = ContextCompat.getColor(this, R.color.bttm_nav_sel_text_color)
                binding.tvPostWork.setTextColor(textColor2)

                binding.profileBT.background = null
                binding.icProfile.setImageResource(R.drawable.ic_profile)
                val textColor3 = ContextCompat.getColor(this, R.color.bttm_nav_text_color)
                binding.tvProfile.setTextColor(textColor3)
            }

            3 -> {
                binding.myJobBT.background = null
                binding.icPostedWork.setImageResource(R.drawable.ic_posted_work)
                val textColor1 = ContextCompat.getColor(this, R.color.bttm_nav_text_color)
                binding.tvPostedWork.setTextColor(textColor1)

                binding.postJobBT.background = null
                binding.icPostWork.setImageResource(R.drawable.ic_post_work)
                val textColor2 = ContextCompat.getColor(this, R.color.bttm_nav_text_color)
                binding.tvPostWork.setTextColor(textColor2)

                binding.profileBT.setBackgroundResource(R.drawable.navi_bar_selected_bg)
                binding.icProfile.setImageResource(R.drawable.ic_profile_colored)
                val textColor3 = ContextCompat.getColor(this, R.color.bttm_nav_sel_text_color)
                binding.tvProfile.setTextColor(textColor3)
            }
        }

    }

}