package com.bluefox.joly.jobModule.jobProviderModule.modalClass

import com.google.gson.annotations.SerializedName


data class PostJobResponse(
    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("message")
    val message: String? = null,
)