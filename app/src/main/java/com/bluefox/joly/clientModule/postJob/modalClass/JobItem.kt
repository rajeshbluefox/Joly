package com.bluefox.joly.clientModule.postJob.modalClass

import com.google.gson.annotations.SerializedName

data class JobItem(

    @field:SerializedName("JobId")
    val jobId: String? = null,
    @field:SerializedName("CategoryId")
    val categoryId: String? = null,
    @field:SerializedName("JobName")
    val jobName: String? = null,
    @field:SerializedName("CreatedDate")
    val createdDate: String? = null,
)