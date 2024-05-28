package com.bluefox.joly.clientModule.postJob.modalClass

import com.google.gson.annotations.SerializedName


data class GetJobsResponse(
    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: List<JobItem?>? = null,

    )