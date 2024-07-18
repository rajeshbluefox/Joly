package com.bluefox.joly.clientModule.profile

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.LogoutDialog
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.profile.modalClass.SSProfileDetailsData
import com.bluefox.joly.clientModule.profile.supportFunctions.ProfileFragmentUI
import com.bluefox.joly.databinding.FragmentProfileBinding
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zSharedPreference.UserDetails
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var profileFragmentUI: ProfileFragmentUI

    private lateinit var logoutDialog: LogoutDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        activity?.window?.setSoftInputMode(
//            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
//        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViews()
        getKeyHeight(view)
    }

    private fun initViews() {
        profileFragmentUI = ProfileFragmentUI(requireContext(),requireActivity(), binding,::onSubmitClicked, ::onLogoutClicked)

        logoutDialog = LogoutDialog(layoutInflater, requireContext(), ::logoutLogic)

        Log.e("Test","Photo :${SSProfileData.mLoginData.photo}")
        Glide.with(this)
            .load(SSProfileData.mLoginData.photo)
            .fitCenter()
            .into(binding.profilePic)

    }

    private fun onLogoutClicked() {
        logoutDialog.openLogoutDialog()
    }

    private fun logoutLogic() {
        UserDetails.saveLoginStatus(requireContext(),false)
        CallIntent.gotoLogin(requireContext(), true, requireActivity())
    }

    private fun getKeyHeight(view: View) {
        view.getKeyboardHeight { keyboardHeight ->
            if (keyboardHeight > 0) {
                // Keyboard is visible
                // Handle the keyboard height
                println("Keyboard height: $keyboardHeight")

                profileFragmentUI.setBottomMargin(
                    binding.view1,
                    keyboardHeight + (keyboardHeight / 2)
                )
            } else {
                // Keyboard is hidden
                println("Keyboard hidden")

                profileFragmentUI.setBottomMargin(binding.view1, 16)

            }
        }
    }

    fun View.getKeyboardHeight(onKeyboardHeightChanged: (Int) -> Unit) {
        val rootView = this
        rootView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            private var previousKeyboardHeight = -1

            override fun onGlobalLayout() {
                val rect = Rect()
                rootView.getWindowVisibleDisplayFrame(rect)
                val screenHeight = rootView.height
                val keypadHeight = screenHeight - rect.bottom

                if (keypadHeight > screenHeight * 0.15) { // Keyboard is opened
                    if (keypadHeight != previousKeyboardHeight) {
                        onKeyboardHeightChanged(keypadHeight)
                        previousKeyboardHeight = keypadHeight
                    }
                } else {
                    if (previousKeyboardHeight != 0) {
                        onKeyboardHeightChanged(0)
                        previousKeyboardHeight = 0
                    }
                }
            }
        })
    }


    private fun onSubmitClicked(sSProfileDetailsData : SSProfileDetailsData)
    {
        Log.e("test","Name ${sSProfileDetailsData.name}")
    }



}