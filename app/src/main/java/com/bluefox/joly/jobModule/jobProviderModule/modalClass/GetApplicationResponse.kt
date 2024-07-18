package com.bluefox.joly.jobModule.jobProviderModule.modalClass

import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.google.gson.annotations.SerializedName


data class GetApplicationResponse (
    @SerializedName("status")
    val code: Int = 195,
    @SerializedName("ApplicationsList")
    val applicationsList: ArrayList<LoginData> =ArrayList()
)