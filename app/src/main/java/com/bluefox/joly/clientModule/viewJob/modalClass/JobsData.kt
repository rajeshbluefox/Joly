package com.bluefox.joly.clientModule.viewJob.modalClass

import com.google.gson.annotations.SerializedName


data class JobsData(

    @field:SerializedName("Id")
    var id: String? = null,
    @field:SerializedName("JobName")
    var jobName: String? = null,
)