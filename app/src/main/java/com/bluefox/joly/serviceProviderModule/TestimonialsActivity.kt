package com.bluefox.joly.serviceProviderModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.clientModule.login.apiFunctions.LoginAPIFunctions
import com.bluefox.joly.clientModule.login.apiFunctions.LoginViewModel
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.bluefox.joly.databinding.ActivityTestimonialsBinding
import com.bluefox.joly.serviceProviderModule.apiFunctions.SPAPIFunctions
import com.bluefox.joly.serviceProviderModule.apiFunctions.SPViewModel
import com.bluefox.joly.zSharedPreference.UserDetails
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestimonialsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestimonialsBinding

    private lateinit var sPViewModel : SPViewModel
    private lateinit var sPAPIFunctions: SPAPIFunctions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestimonialsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        onClickListeners()

    }

    private fun initViews()
    {
        sPViewModel = ViewModelProvider(this)[SPViewModel::class.java]
        sPAPIFunctions = SPAPIFunctions(this,this,sPViewModel,::onTestimonyPostedResponse)
    }


    private fun onClickListeners() {
        binding.btSubmit.setOnClickListener {
            getValues()
        }
    }

    private fun getValues()
    {
        val nCustomerName = binding.etCustomerName.text.toString()
        val nTestimony = binding.etTestimony.text.toString()


        if(nCustomerName.isEmpty())
        {
            UtilFunctions.showToast(this, "Enter CustomerName")
            return
        }
        if (nTestimony.isEmpty())
        {
            UtilFunctions.showToast(this, "Enter nTestimony")
            return
        }

        val sPTestimonyData = SPTestimonyData()
        sPTestimonyData.customerName= nCustomerName
        sPTestimonyData.testimony=nTestimony

        sPTestimonyData.phoneNumber=UserDetails.getUserMobileNo(this)
        sPViewModel.postSPTestimony(sPTestimonyData)

    }

    private fun onTestimonyPostedResponse() {

    }







}