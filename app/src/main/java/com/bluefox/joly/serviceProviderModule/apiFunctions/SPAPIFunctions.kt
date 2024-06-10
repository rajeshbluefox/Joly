package com.bluefox.joly.serviceProviderModule.apiFunctions

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.bluefox.joly.clientModule.login.apiFunctions.LoginViewModel
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.serviceProviderModule.modelClass.AddServiceData
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.bluefox.joly.serviceProviderModule.modelClass.ServicesOfferedData
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions

class SPAPIFunctions(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    sPViewModel: SPViewModel,
    private val onGetServicesResponse: (servicesOfferedList: List<ServicesOfferedData>) -> Unit,
    private val onTestimonyPostedResponse: () -> Unit,
    private val onServiceAddedResponse: () -> Unit,
    private val onGetTestimonials: (testimonialsList: List<SPTestimonyData>) -> Unit,

    ) {

    private var mContext: Context
    private var mLifecycleOwner: LifecycleOwner
    private var mSPViewModel: SPViewModel

    init {
        mContext = context
        mLifecycleOwner = lifecycleOwner
        mSPViewModel = sPViewModel
    }

    fun postSPTestimony(sPTestimonyData: SPTestimonyData) {
        mSPViewModel.resetSPTestimonyResponse()
        mSPViewModel.postSPTestimony(sPTestimonyData)
        postSPTestimonyObserver()
    }

    private fun postSPTestimonyObserver() {
        mSPViewModel.getSPTestimonyResponse().observe(mLifecycleOwner) {

            if (it.status == 200) {
                onTestimonyPostedResponse.invoke()
                UtilFunctions.showToast(mContext, "Testimony Posted Successfully")
            } else {
                UtilFunctions.showToast(mContext, "Wrong Password")
                Log.e("Test", "Login Error")
            }
        }
    }

    fun getServicesOffered(mobileNo: String) {
        mSPViewModel.resetGetServiceOfferedSPResponse()
        mSPViewModel.getServiceOfferedSP(mobileNo)

        getServicesOfferedObserver()
    }

    private fun getServicesOfferedObserver() {
        mSPViewModel.getServiceOfferedSPResponse().observe(mLifecycleOwner) {
            if (it.status == 200) {
                if (it.data!!.isEmpty()) {
                    UtilFunctions.showToast(mContext, "No Service Found")
                } else {
                    onGetServicesResponse.invoke(it.data as List<ServicesOfferedData>)
                }
            } else {
                UtilFunctions.showToast(mContext, "Invalid Response")
            }
        }
    }

    fun addService(addServiceData: AddServiceData) {
        mSPViewModel.resetAddServiceSPResponse()
        mSPViewModel.addServiceSP(addServiceData)

        addServiceObserver()
    }

    private fun addServiceObserver() {
        mSPViewModel.getAddServiceSPResponse().observe(mLifecycleOwner) {
            if (it.status == 200) {

                UtilFunctions.showToast(mContext, "Service Added Sucessfully")
                onServiceAddedResponse.invoke()

            } else {
                UtilFunctions.showToast(mContext, "Invalid Response")
            }
        }
    }

    fun getSPTestimonies(mobileNo: String)
    {
        mSPViewModel.resetgetSPTestimoniesPResponse()
        mSPViewModel.getSPTestimonies(mobileNo)

        getSPTestimoniesObserver()
    }

    private fun getSPTestimoniesObserver()
    {
        mSPViewModel.getSPTestimoniesResponse().observe(mLifecycleOwner){

            if(it.status==200)
            {
                if(it.data!!.isEmpty())
                {
                    UtilFunctions.showToast(mContext,"No Testimonials Found")
                }else{
                    onGetTestimonials.invoke(it.data as List<SPTestimonyData>)
                }

            }else{
                UtilFunctions.showToast(mContext,"Invalid Response")
            }
        }
    }
}