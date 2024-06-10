package com.bluefox.joly.serviceProviderModule.modelClass

import com.google.gson.annotations.SerializedName



class GetTestimoniesResponse(
    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: List<SPTestimonyData?>? = null
)