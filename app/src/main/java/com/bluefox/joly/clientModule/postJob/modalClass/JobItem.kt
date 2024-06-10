package com.bluefox.joly.clientModule.postJob.modalClass

import com.bluefox.joly.clientModule.viewJob.modalClass.WorkPhotoData
import com.google.gson.annotations.SerializedName

data class JobItem(

    @field:SerializedName("JobId")
    val jobId: Int? = null,
    @field:SerializedName("CategoryId")
    val categoryId: Int? = null,
    @field:SerializedName("JobName")
    val jobName: String? = null,
    @field:SerializedName("CreatedDate")
    val createdDate: String? = null
)