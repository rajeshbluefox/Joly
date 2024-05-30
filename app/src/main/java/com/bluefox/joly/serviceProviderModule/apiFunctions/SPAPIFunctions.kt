package com.bluefox.joly.serviceProviderModule.apiFunctions

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.bluefox.joly.clientModule.login.apiFunctions.LoginViewModel
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions

class SPAPIFunctions(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    sPViewModel : SPViewModel,
    private val onTestimonyPostedResponse: () -> Unit,
) {

    private var mContext: Context
    private var mLifecycleOwner: LifecycleOwner
    private var mSPViewModel: SPViewModel

    init {
        mContext = context
        mLifecycleOwner = lifecycleOwner
        mSPViewModel = sPViewModel
    }

    fun postSPTestimony(sPTestimonyData: SPTestimonyData)
    {
        mSPViewModel.resetSPTestimonyResponse()
        mSPViewModel.postSPTestimony(sPTestimonyData)
        getValidateObserver()
    }

    private fun getValidateObserver()
    {
        mSPViewModel.getSPTestimonyResponse().observe(mLifecycleOwner){

            if(it.status==200)
            {
                onTestimonyPostedResponse.invoke()
                UtilFunctions.showToast(mContext,"Posted Successfully")
            }else{
                UtilFunctions.showToast(mContext,"Wrong Password")
                Log.e("Test","Login Error")
            }
        }

    }
}