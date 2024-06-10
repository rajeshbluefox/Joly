package com.bluefox.joly.serviceProviderModule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluefox.joly.databinding.ActivityShowTestimoniesBinding
import com.bluefox.joly.serviceProviderModule.apiFunctions.SPAPIFunctions
import com.bluefox.joly.serviceProviderModule.apiFunctions.SPViewModel
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.bluefox.joly.serviceProviderModule.supportFunctions.ServicesOfferedAdapter
import com.bluefox.joly.serviceProviderModule.supportFunctions.TestimonyAdapter
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bluefox.joly.zSharedPreference.UserDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowTestimoniesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowTestimoniesBinding

    private lateinit var sPViewModel: SPViewModel
    private lateinit var sPAPIFunctions: SPAPIFunctions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowTestimoniesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources,binding.appBarLt)

        initViews()
        onClickListeners()
    }

    private fun initViews() {
        sPViewModel = ViewModelProvider(this)[SPViewModel::class.java]
        sPAPIFunctions = SPAPIFunctions(
            this,
            this,
            sPViewModel,
            onGetServicesResponse = {},
            onTestimonyPostedResponse = {},
            onServiceAddedResponse = {},
            ::onGetTestimonialsResponse)

        callAPis()
    }

    private fun callAPis()
    {
        val userMobileNo = UserDetails.getUserMobileNo(this)
        sPAPIFunctions.getSPTestimonies(userMobileNo)
    }
    private fun onGetTestimonialsResponse(testimonialsList: List<SPTestimonyData>)
    {
        initTestimonies(testimonialsList)
    }


    private fun initTestimonies(testimonialsList: List<SPTestimonyData>)
    {
        val testimonyAdapter = TestimonyAdapter(this, testimonialsList)
        binding.rvTestimonies.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = testimonyAdapter
        }
    }
    private fun onClickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btMyTestimonials.setOnClickListener {
            CallIntent.gotoTestimonialsActivity(this,false,this)
        }

    }


}