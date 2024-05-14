package com.bluefox.joly.clientModule.profile.supportFunctions

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.bluefox.joly.databinding.FragmentProfileBinding
import com.bluefox.joly.zCommonFunctions.StatusBarUtils


class ProfileFragmentUI(
    val context: Context,
    private val binding: FragmentProfileBinding
) {

    init {

        stopEditing()
        onClickListeners()
    }

    private fun onClickListeners() {
        binding.ivEdit.setOnClickListener {
            startEditing()
        }

        binding.btSubmit.setOnClickListener {

            stopEditing()
        }
    }

    private fun stopEditing() {
        binding.ivEdit.visibility = View.VISIBLE
        binding.btSubmit.visibility = View.GONE

        disableET(binding.ltName)
        disableET(binding.ltAadharNum)
        disableET(binding.ltDOB)
        disableET(binding.ltGender)
        disableET(binding.ltPinCode)
        disableET(binding.ltAddress)

    }

    private fun startEditing() {
        binding.ivEdit.visibility = View.GONE
        binding.btSubmit.visibility = View.VISIBLE

        enableET(binding.ltName)
        enableET(binding.ltAadharNum)
        enableET(binding.ltDOB)
        enableET(binding.ltGender)
        enableET(binding.ltPinCode)
        enableET(binding.ltAddress)
    }

    private fun disableET(view: View) {
//        view.isFocusable = false;
        view.isClickable = false;
        view.isEnabled = false
    }

    private fun enableET(view: View) {
//        view.isFocusable = true;
        view.isClickable = true;
        view.isEnabled = true;
    }

    fun setBottomMargin(view: View,margin : Int) {
        // Assuming tabLayout is your TabLayout instance
        val params = view.layoutParams as ConstraintLayout.LayoutParams

        params.topMargin = margin
        view.layoutParams = params
    }




}
