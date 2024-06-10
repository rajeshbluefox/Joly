package com.bluefox.joly.serviceProviderModule.modelClass

import com.google.gson.annotations.SerializedName


class SpOfferedServiceResponse(
    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: List<ServicesOfferedData?>? = null,
)