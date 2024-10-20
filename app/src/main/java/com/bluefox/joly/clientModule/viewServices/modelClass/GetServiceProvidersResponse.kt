package com.bluefox.joly.clientModule.viewServices.modelClass

import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.google.gson.annotations.SerializedName


data class GetServiceProvidersResponse (
    @SerializedName("status")
    val code: Int = 195,
    @SerializedName("data")
    val serviceProviderDetailsData: ArrayList<ServiceProviderDetailsData> =ArrayList()
)