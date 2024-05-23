package com.bluefox.joly.clientModule.viewJob.modalClass

import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.google.gson.annotations.SerializedName


data class GetWorkResponse(
    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: List<PostWorkData?>? = null,
)