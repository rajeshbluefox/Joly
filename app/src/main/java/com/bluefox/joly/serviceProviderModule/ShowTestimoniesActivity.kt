package com.bluefox.joly.serviceProviderModule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
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
        StatusBarUtils.setTopPadding(resources, binding.appBarLt)

        initViews()
        onClickListeners()
    }

    override fun onResume() {
        super.onResume()

        callAPis()
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
            ::onGetTestimonialsResponse
        )

//        callAPis()
        val ratings = SSProfileData.mLoginData.noOfRatings - 1

        binding.totalRatingCount.text = "${ratings} Ratings"
        updateStarRating(SSProfileData.mLoginData.currentRating.toDouble())
    }

    private fun callAPis() {
//        val userMobileNo = UserDetails.getUserMobileNo(this)
        val spId = SSProfileData.mLoginData.id
        sPAPIFunctions.getSPTestimonies(spId.toString())
    }

    private fun onGetTestimonialsResponse(testimonialsList: List<SPTestimonyData>) {
        initTestimonies(testimonialsList)
    }


    private fun initTestimonies(testimonialsList: List<SPTestimonyData>) {

        if (testimonialsList.isEmpty()) {
            showEmptyContent()
        } else {
            hideEmptyContent()
        }

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
            CallIntent.gotoTestimonialsActivity(this, false, this)
        }

    }


    private fun updateStarRating(rating: Double) {
        val adjustedRating = adjustRating(rating)

        // Assuming you have 5 stars in a LinearLayout
        val stars = arrayOf(
            binding.star1,
            binding.star2,
            binding.star3,
            binding.star4,
            binding.star5
        ) // Replace with your actual ImageView references

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

    fun showEmptyContent() {
        binding.emptyContent.visibility = android.view.View.VISIBLE
        binding.rvTestimonies.visibility = android.view.View.GONE
    }

    fun hideEmptyContent() {
        binding.emptyContent.visibility = android.view.View.GONE
        binding.rvTestimonies.visibility = android.view.View.VISIBLE
    }


}