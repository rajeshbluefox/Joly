package com.bluefox.joly.jobModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.profile.ProfileFragment
import com.bluefox.joly.clientModule.viewJob.ViewWorksFragment
import com.bluefox.joly.databinding.ActivityHomeBinding
import com.bluefox.joly.databinding.ActivityJobHomeBinding
import com.bluefox.joly.jobModule.jobProviderModule.PostJobFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillPostJob()
        onClickListeners()

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
}