package com.bluefox.joly.serviceProviderModule.apiFunctions

import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.clientModule.login.modelClass.RegistrationResponse
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyResponse
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


}