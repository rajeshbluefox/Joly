package com.bluefox.joly.jobModule

import android.os.Bundle
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
import com.bluefox.joly.zSharedPreference.UserDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobHomeBinding

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginAPIFunctions: LoginAPIFunctions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initViews()
        fillPostJob()
        onClickListeners()

        val loginData = LoginData()
        loginData.phoneNumber = UserDetails.getUserMobileNo(this)
        loginData.password = UserDetails.getUserPassword(this)
//        postValidate(loginData)

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

    private fun onClickListeners() {

        binding.myJobBT.setOnClickListener {

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

        binding.tvAppBarTitle.text = "Post Job"

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, PostJobFragment())
            .commit()
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

    private fun postValidate(loginData: LoginData) {
        loginAPIFunctions.validateLogin(loginData)
    }

    private fun onLoginResponse(loginData: LoginData) {

        SSProfileData.openFrom = 1
        SSProfileData.mLoginData = loginData

        val userRole = UserDetails.getUserRoleStatus(this)
        SSProfileData.UserRole = userRole

        hidePB()
        fillPostJob()

    }

    fun showPB() {
        binding.progressBar.visibility = android.view.View.VISIBLE
        binding.containerFragment.visibility = android.view.View.GONE
    }

    private fun hidePB() {
        binding.progressBar.visibility = android.view.View.GONE
        binding.containerFragment.visibility = android.view.View.VISIBLE
    }

}