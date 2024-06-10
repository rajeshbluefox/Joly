package com.bluefox.joly.serviceProviderModule.apiFunctions

import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.clientModule.login.modelClass.RegistrationResponse
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.serviceProviderModule.modelClass.AddServiceData
import com.bluefox.joly.serviceProviderModule.modelClass.AddServiceResponse
import com.bluefox.joly.serviceProviderModule.modelClass.GetTestimoniesResponse
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyResponse
import com.bluefox.joly.serviceProviderModule.modelClass.SpOfferedServiceResponse
import com.bluefox.joly.zAPIEndPoints.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SPRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    private var sPTestimonyResponse = SPTestimonyResponse()

    suspend fun postSPTestimony(sPTestimonyData: SPTestimonyData): SPTestimonyResponse {
        try {
            withContext(Dispatchers.IO) {
                sPTestimonyResponse = apiHelper.postSPTestimony(sPTestimonyData)
            }
        } catch (_: Exception) {
        }
        return sPTestimonyResponse
    }

    private var spOfferedServiceResponse = SpOfferedServiceResponse()

    suspend fun getServiceOfferedSP(mobileNo: String): SpOfferedServiceResponse {
        try {
            withContext(Dispatchers.IO) {
                spOfferedServiceResponse = apiHelper.getServiceOfferedSP(mobileNo)
            }
        } catch (_: Exception) {
        }
        return spOfferedServiceResponse
    }

    private var addServiceResponse = AddServiceResponse()

    suspend fun addServiceSP(addServiceData: AddServiceData): AddServiceResponse {
        try {
            withContext(Dispatchers.IO) {
                addServiceResponse = apiHelper.addServiceSP(addServiceData)
            }
        } catch (_: Exception) {
        }
        return addServiceResponse
    }

    private var getTestimoniesResponse = GetTestimoniesResponse()

    suspend fun getSPTestimonies(mobileNo: String): GetTestimoniesResponse {
        try {
            withContext(Dispatchers.IO) {
                getTestimoniesResponse = apiHelper.getSPTestimonies(mobileNo)
            }
        } catch (_: Exception) {
        }
        return getTestimoniesResponse
    }



}