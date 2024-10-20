package com.bluefox.joly.homeModule

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
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
        StatusBarUtils.setTopPadding(resources,binding.mainLt)

        onClickListeners()
        onRoleClickListeners()
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

    fun onRoleClickListeners()
    {
        binding.btService.setOnClickListener {
            onMainRoleSelected(1)
        }

        binding.btJob.setOnClickListener {
            onMainRoleSelected(2)
        }

        binding.btNext.setOnClickListener {
            enableDetailsRole(selectedDomain)
        }
    }

    var selectedDomain = 1
    var selectedRole = 1

    private fun onMainRoleSelected(userDomain: Int)
    {
        selectedDomain=userDomain

        when(userDomain)
        {
            1 -> {
                binding.btService.setBackgroundResource(R.drawable.nav_b1_bg)
                binding.btServiceImage.setImageResource(R.drawable.iv_service_white)
                binding.btServiceText.setTextColor(resources.getColor(R.color.white))

                binding.btJob.setBackgroundResource(R.drawable.nav_b2_bg)
                binding.btJobImage.setImageResource(R.drawable.iv_job_filled)
                binding.btJobText.setTextColor(resources.getColor(R.color.app_bar_c1))

                setRoleDetails(R.drawable.iv_service_filled,"Services",getString(R.string.service_description))
            }
            2 -> {
                binding.btService.setBackgroundResource(R.drawable.nav_b2_bg)
                binding.btServiceImage.setImageResource(R.drawable.iv_service_filled)
                binding.btServiceText.setTextColor(resources.getColor(R.color.app_bar_c1))

                binding.btJob.setBackgroundResource(R.drawable.nav_b1_bg)
                binding.btJobImage.setImageResource(R.drawable.iv_job_white)
                binding.btJobText.setTextColor(resources.getColor(R.color.white))

                setRoleDetails(R.drawable.iv_job_filled,"Jobs",getString(R.string.job_description))

            }
        }
    }

    private fun setRoleDetails(roleImage: Int, roleName: String, roleDescription: String)
    {
        binding.ivRoleIcon.setImageResource(roleImage)
        binding.tvRoleTitle.text = roleName
        binding.tvRoleDescription.text = roleDescription
    }

    //Role Code

    private fun enableDetailsRole(userDomain: Int)
    {
        binding.btService.visibility=android.view.View.GONE
        binding.btJob.visibility=android.view.View.GONE

        binding.btProvider.visibility=android.view.View.VISIBLE
        binding.btSeeker.visibility=android.view.View.VISIBLE

//        setDetailsRole(userDomain)

        roleOnClickListeners()
        onRoleSelected(userDomain,1)
    }

    private fun roleOnClickListeners()
    {
        binding.btProvider.setOnClickListener {
            onRoleSelected(selectedDomain,1)
        }

        binding.btSeeker.setOnClickListener {
            onRoleSelected(selectedDomain,2)
        }

        binding.btNext.setOnClickListener {
            saveUserRoleAndNavigate(finalUserRole)
        }
    }

    private var finalUserRole = 0

    private fun onRoleSelected(selectedDomain: Int, roleType: Int)
    {
//        var finalUserRole = 0

        if(selectedDomain==1 && roleType==1)
        {
            finalUserRole = 2
        }else if(selectedDomain==1 && roleType==2)
        {
            finalUserRole = 1
        }else if(selectedDomain==2 && roleType==1)
        {
            finalUserRole = 3
        }else if(selectedDomain==2 && roleType==2)
        {
            finalUserRole = 4
        }

        when(finalUserRole)
        {
            1 -> {
                setSelected(binding.btProvider,R.drawable.nav_b3_bg,binding.btProviderImage,R.drawable.iv_service_filled,binding.btProviderText,"Service Provider", R.color.app_bar_c1)
                setSelected(binding.btSeeker,R.drawable.nav_b4_bg,binding.btSeekerImage,R.drawable.iv_service_white,binding.btSeekerText,"Service Seeker", R.color.white)

                setRoleDetails(R.drawable.iv_service_filled,"Service Seeker",getString(R.string.ss_description))
            }
            2 -> {
                setSelected(binding.btProvider,R.drawable.nav_b4_bg,binding.btProviderImage,R.drawable.iv_service_white,binding.btProviderText,"Service Provider", R.color.white)
                setSelected(binding.btSeeker,R.drawable.nav_b3_bg,binding.btSeekerImage,R.drawable.iv_service_filled,binding.btSeekerText,"Service Seeker", R.color.app_bar_c1)

                setRoleDetails(R.drawable.iv_service_filled,"Service Provider",getString(R.string.sp_description))
            }
            3 -> {
                setSelected(binding.btProvider,R.drawable.nav_b4_bg,binding.btProviderImage,R.drawable.iv_job_white,binding.btProviderText,"Job Provider", R.color.white)
                setSelected(binding.btSeeker,R.drawable.nav_b3_bg,binding.btSeekerImage,R.drawable.iv_job_filled,binding.btSeekerText,"Job Seeker", R.color.app_bar_c1)

                setRoleDetails(R.drawable.iv_job_filled,"Job Provider",getString(R.string.jp_description))
            }
            4 -> {
                setSelected(binding.btProvider,R.drawable.nav_b3_bg,binding.btProviderImage,R.drawable.iv_job_filled,binding.btProviderText,"Job Provider", R.color.app_bar_c1)
                setSelected(binding.btSeeker,R.drawable.nav_b4_bg,binding.btSeekerImage,R.drawable.iv_job_white,binding.btSeekerText,"Job Seeker", R.color.white)

                setRoleDetails(R.drawable.iv_job_filled,"Job Seeker",getString(R.string.js_description))
            }
        }

    }

    private fun setSelected(bgLt: LinearLayout, background: Int, imageIcon: ImageView, image: Int, roleTitle: TextView, text: String,color: Int)
    {
        bgLt.setBackgroundResource(background)
        imageIcon.setImageResource(image)
        roleTitle.setText(text)
        roleTitle.setTextColor(resources.getColor(color))
    }

    private fun saveUserRoleAndNavigate(userRole: Int)
    {
        UserDetails.saveUserRoleStatus(this,userRole)
        SSProfileData.UserRole = userRole
        CallIntent.gotoLogin(this,false,this)
    }

    private fun setDetailsRole(domainRole: Int)
    {
        when(domainRole)
        {
            1 -> {
                binding.btProvider.setBackgroundResource(R.drawable.nav_b3_bg)
                binding.btProviderImage.setImageResource(R.drawable.iv_service_filled)
                binding.btProviderText.setText("Service Provider")
                binding.btProviderText.setTextColor(resources.getColor(R.color.app_bar_c1))

                binding.btSeeker.setBackgroundResource(R.drawable.nav_b4_bg)
                binding.btSeekerImage.setImageResource(R.drawable.iv_service_white)
                binding.btSeekerText.setText("Service Seeker")
                binding.btSeekerText.setTextColor(resources.getColor(R.color.white))
            }
            2 ->{
                binding.btProvider.setBackgroundResource(R.drawable.nav_b3_bg)
                binding.btProviderImage.setImageResource(R.drawable.iv_job_filled)
                binding.btProviderText.setText("Job Provider")
                binding.btProviderText.setTextColor(resources.getColor(R.color.app_bar_c1))

                binding.btSeeker.setBackgroundResource(R.drawable.nav_b4_bg)
                binding.btSeekerImage.setImageResource(R.drawable.iv_job_white)
                binding.btSeekerText.setText("Job Seeker")
                binding.btSeekerText.setTextColor(resources.getColor(R.color.white))
            }
        }
    }

}