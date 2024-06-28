package com.bluefox.joly.jobModule.jobProviderModule.modalClass

import com.google.gson.annotations.SerializedName


data class PostJobData(
    @SerializedName("PhoneNumber")
    var phoneNumber: String? = null,
    @SerializedName("WorkName")
    var workName: String? = null,
    )