package com.bluefox.joly.clientModule.viewServices.modelClass

import com.google.gson.annotations.SerializedName


data class CheckFBStatusResponse(
    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("feedbackExists")
    val feedbackExists: Boolean = false
)