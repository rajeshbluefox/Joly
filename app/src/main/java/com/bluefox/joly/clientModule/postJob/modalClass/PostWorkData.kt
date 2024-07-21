package com.bluefox.joly.clientModule.postJob.modalClass

import com.bluefox.joly.clientModule.viewJob.modalClass.WorkPhotoData
import com.google.gson.annotations.SerializedName


data class PostWorkData(
    @SerializedName("PhoneNumber")
    var phoneNumber: String? = null,
    @SerializedName("SS_WorkId")
    var workId: String? = null,
    @SerializedName("WorkName")
    var workName: String? = null,
    @SerializedName("Description")
    var workDescription: String? = null,
    @SerializedName("ServiceSeeker_Works_date")
    var workPostedDate: String? = null,
    @SerializedName("CategoryID")
    var categoryId: Int? = null,
    @SerializedName("JobTypeID")
    var jobId: Int? = null,
    @SerializedName("AreaId")
    var areaId: Int? = null,
    @SerializedName("Wage")
    var wageOffered: String? = null,
    @SerializedName("Name")
    var name: String? = null,
    @SerializedName("Photo")
    var profilePhoto: String? = null,
    @SerializedName("Photos")
    val data: List<WorkPhotoData?>? = null,
)