package com.bluefox.joly.serviceProviderModule.modelClass

import com.google.gson.annotations.SerializedName

class SPTestimonyResponse(
    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("message")
    val message: String? = null
)