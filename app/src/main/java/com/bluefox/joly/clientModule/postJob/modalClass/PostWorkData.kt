package com.bluefox.joly.clientModule.postJob.modalClass

import com.google.gson.annotations.SerializedName


data class PostWorkData(
    @SerializedName("PhoneNumber")
    var phoneNumber: String? = null,
    @SerializedName("WorkName")
    var workName: String? = null,
    @SerializedName("CategoryId")
    var categoryId: Int? = null,
    @SerializedName("JobId")
    var jobId: String? = null,
    @SerializedName("AreaId")
    val areaId: String? = null,
    @SerializedName("WageOffered")
    val wageOffered: String? = null
)