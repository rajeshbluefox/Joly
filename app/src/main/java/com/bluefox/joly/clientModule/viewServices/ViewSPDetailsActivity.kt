package com.bluefox.joly.clientModule.viewServices

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSViewModel
import com.bluefox.joly.clientModule.viewJob.modalClass.SSSelected
import com.bluefox.joly.clientModule.viewServices.modelClass.SelSPData
import com.bluefox.joly.clientModule.viewServices.supportFuncation.AddRatingDialog
import com.bluefox.joly.clientModule.viewServices.supportFuncation.ViewSPAdapter.ViewSPAdapterViewHolder
import com.bluefox.joly.databinding.ActivityViewServiceProvidersBinding
import com.bluefox.joly.databinding.ActivityViewSpdetailsBinding
import com.bluefox.joly.serviceProviderModule.apiFunctions.SPViewModel
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bluefox.joly.zSharedPreference.UserDetails
import com.bumptech.glide.Glide
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewSPDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewSpdetailsBinding

    private lateinit var addRatingDialog: AddRatingDialog

    private lateinit var ssViewModel: SSViewModel
    private lateinit var spViewModel: SPViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewSpdetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ssViewModel = ViewModelProvider(this)[SSViewModel::class.java]
        spViewModel = ViewModelProvider(this)[SPViewModel::class.java]

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources, binding.appBarLt)

        setData()
        initFunctions()
        onClickListeners()
    }

    private fun setData() {

        val spDetails = SelSPData.serviceProviderDetailsData
        binding.tvCompanyName.text = spDetails.companyName
        updateStarRating(spDetails.currentRating.toDouble())
        binding.tvDescriptionValue.text = spDetails.description
        binding.tvPortfolioLinkValue.text = spDetails.portfolioLink
        binding.tvContactValue.text = "Contact : ${spDetails.phoneNumber}"
        binding.tvWorkingHours.text="Working Hours : ${spDetails.workingHours}"


        if(spDetails.alternativeNumber=="E")
            binding.titleBarAlternativeContactDetails.visibility=View.GONE
        else
            binding.tvAlterContactValue.text="Alternative Contact : ${spDetails.alternativeNumber}"


        if (spDetails.photo != null) {
            Glide.with(this)
                .load(spDetails.photo)
                .fitCenter()
                .into(binding.profilePic)
        }

        checkFBStatus()
    }


    fun onClickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btRate.setOnClickListener {
            addRatingDialog.showRatingDialog()
        }

        binding.btCallNow.setOnClickListener {
            openDialer(SelSPData.serviceProviderDetailsData.phoneNumber!!)
        }

        binding.btAlterCallNow.setOnClickListener {
            openDialer(SelSPData.serviceProviderDetailsData.alternativeNumber!!)
        }

        binding.btOpenSite.setOnClickListener {
            CallIntent.gotoViewWebPageActivity(this,false,this)
        }
    }

    private fun initFunctions() {
        addRatingDialog = AddRatingDialog(layoutInflater, this, ::onRatingSubmitClicked)
    }

    private fun onRatingSubmitClicked(spTestimonyData: SPTestimonyData) {
        spTestimonyData.customerName=SSProfileData.mLoginData.name
        spTestimonyData.phoneNumber=SSProfileData.mLoginData.phoneNumber
        spTestimonyData.serviceProviderId=SelSPData.serviceProviderDetailsData.serviceId!!
        spTestimonyData.status="1"
        spTestimonyData.feedbackProviderId=SSProfileData.mLoginData.id!!.toInt()

        postSPTestimony(spTestimonyData)
    }

    private fun checkFBStatus() {
        val fpId = SSProfileData.mLoginData.id!!.toInt()
        ssViewModel.resetCheckFBStatusResponse()
        ssViewModel.checkFBStatus(SelSPData.serviceProviderDetailsData.serviceId!!, fpId)

        checkFBStatusResponse()
    }

    private fun checkFBStatusResponse() {
        ssViewModel.checkFBStatusResponse().observe(this) {
            if (it.status != 195) {
                if (it.feedbackExists) {
                    binding.btRate.visibility = View.GONE
                } else {
                    if(SSSelected.workData.status==0 && SSSelected.workData.ratingAdded==0 && SSSelected.workData.closingFeedback==1) {
                        binding.btRate.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun postSPTestimony(sPTestimonyData: SPTestimonyData) {
        spViewModel.resetSPTestimonyResponse()
        spViewModel.postSPTestimony(sPTestimonyData)
        postSPTestimonyObserver()
    }

    private fun postSPTestimonyObserver() {
        spViewModel.getSPTestimonyResponse().observe(this) {
            if (it.status != 195) {
                if (it.status == 200) {
                    UtilFunctions.showToast(this, "Thanks for rating us!")
                    addRatingDialog.dismissRatingDialog()
                } else {
                    UtilFunctions.showToast(this, "Something went wrong")
                }
            } else {
                UtilFunctions.showToast(this, "Something went wrong")
            }
        }
    }

    private fun openDialer(phoneNumber: String) {
        // Create an intent to open the dialer
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(dialIntent)
    }

    fun openWebPage(url: String) {
        val webpage = Uri.parse(url)
        val defaultIntent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(defaultIntent)
    }

    private fun updateStarRating(rating: Double) {
        val adjustedRating = adjustRating(rating)

        // Assuming you have 5 stars in a LinearLayout
        val stars = arrayOf(binding.star1, binding.star2, binding.star3, binding.star4, binding.star5) // Replace with your actual ImageView references

        // Loop through the stars and set the image based on the adjusted rating
        for (i in stars.indices) {
            if (i < adjustedRating) {
                stars[i].setImageResource(R.drawable.star_filled) // Filled star
            } else {
                stars[i].setImageResource(R.drawable.star) // Empty star
            }
        }
    }

    private fun adjustRating(rating: Double): Int {
        return when {
            rating <= 0.0 -> 0 // If rating is 0 or less
            rating in 0.1..0.5 -> rating.toInt() // Floor for values between 0.1 and 0.5
            else -> Math.ceil(rating).toInt() // Ceiling for values above 0.5
        }
    }
}