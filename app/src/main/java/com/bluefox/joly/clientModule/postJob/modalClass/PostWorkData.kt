package com.bluefox.joly.clientModule.postJob.modalClass

import com.bluefox.joly.clientModule.viewJob.modalClass.WorkPhotoData
import com.google.gson.annotations.SerializedName


data class PostWorkData(
    @SerializedName("PhoneNumber")
    var phoneNumber: String? = null,
    @SerializedName("WorkName")
    var workName: String? = null,
    @SerializedName("CategoryId")
    var categoryId: Int? = null,
    @SerializedName("JobId")
    var jobId: Int? = null,
    @SerializedName("AreaId")
    var areaId: Int? = null,
    @SerializedName("WageOffered")
    var wageOffered: String? = null,
    @SerializedName("Photos")
    val data: List<WorkPhotoData?>? = null,
)