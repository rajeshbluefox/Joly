package com.bluefox.joly.clientModule.profile.supportFunctions

import android.content.Context
import android.view.View
import com.bluefox.joly.databinding.FragmentProfileBinding


class ProfileFragmentUI(
    val context: Context,
    private val binding: FragmentProfileBinding
) {

    init {

        stopEditing()
        onClickListeners()
    }

    fun onClickListeners()
    {
        binding.ivEdit.setOnClickListener {
            startEditing()
        }
    }

    fun stopEditing()
    {
        disableET(binding.etName)
        disableET(binding.etAadharNum)
        disableET(binding.etDOB)
        disableET(binding.etGender)
        disableET(binding.etPinCode)
        disableET(binding.etAddress)

    }

    fun startEditing()
    {
        enableET(binding.etName)
        enableET(binding.etAadharNum)
        enableET(binding.etDOB)
        enableET(binding.etGender)
        enableET(binding.etPinCode)
        enableET(binding.etAddress)
    }

    fun disableET(view: View)
    {
        view.isFocusable = false;
        view.isClickable = false;
    }

    fun enableET(view: View)
    {
        view.isFocusable = false;
        view.isClickable = false;
    }

}
