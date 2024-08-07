package com.bluefox.joly.jobModule.jobProviderModule.modalClass

import com.google.gson.annotations.SerializedName


data class GetPostedJobsResponse (
    @SerializedName("status")
    val code: Int = 195,
    @SerializedName("PostedJobsList")
    val postedJobsList: ArrayList<PostJobData> =ArrayList()
)