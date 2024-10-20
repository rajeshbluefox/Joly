package com.bluefox.joly.clientModule.viewServices.supportFuncation

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.bluefox.joly.R
import com.bluefox.joly.databinding.DialogAddRatingBinding
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions


class AddRatingDialog(
    layoutInflater: LayoutInflater,
    context: Context,
    private val onSubmitClicked: (spTestimonyData: SPTestimonyData) -> Unit

) {
    private val mLayoutInflater: LayoutInflater
    private val mContext: Context

    private var binding: DialogAddRatingBinding
    private var dialog: AlertDialog.Builder
    private var messageBoxInstance: AlertDialog

    init {
        mLayoutInflater = layoutInflater
        mContext = context
        binding = DialogAddRatingBinding.inflate(mLayoutInflater)
        dialog = AlertDialog.Builder(mContext).setView(binding.root)
        dialog.setCancelable(true)
        messageBoxInstance = dialog.create()

        onClickListener()
        starsOnClickListeners()
    }

    private fun onClickListener() {
        binding.btSubmit.setOnClickListener {
            getValues()
        }
    }

    fun getValues() {
        val spTestimonyData = SPTestimonyData()

        if (userRating == 0) {
            UtilFunctions.showToast(context = mContext, "Please select rating")
            return
        }

        var testimony = binding.etTestimony.text.toString()

        if (testimony.isEmpty())
            testimony = "E"

        spTestimonyData.rating = userRating
        spTestimonyData.testimony = testimony

        onSubmitClicked(spTestimonyData)
    }

    private var userRating = 0

    private fun starsOnClickListeners() {
        // List of star ImageViews using binding
        val stars = listOf(
            binding.star1,
            binding.star2,
            binding.star3,
            binding.star4,
            binding.star5
        )

        // Function to update the stars based on the clicked position
        fun updateStars(rating: Int) {
            userRating = rating
            for (i in stars.indices) {
                if (i < rating) {
                    stars[i].setImageResource(R.drawable.star_filled) // Filled star
                } else {
                    stars[i].setImageResource(R.drawable.star) // Empty star
                }
            }
        }

        // Set click listeners for each star using binding
        binding.star1.setOnClickListener { updateStars(1) }
        binding.star2.setOnClickListener { updateStars(2) }
        binding.star3.setOnClickListener { updateStars(3) }
        binding.star4.setOnClickListener { updateStars(4) }
        binding.star5.setOnClickListener { updateStars(5) }

    }

    fun showRatingDialog() {
        messageBoxInstance.show()

    }

    fun dismissRatingDialog() {
        messageBoxInstance.dismiss()
    }


}