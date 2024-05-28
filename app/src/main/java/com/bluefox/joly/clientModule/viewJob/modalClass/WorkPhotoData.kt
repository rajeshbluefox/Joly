package com.bluefox.joly.clientModule.viewJob.modalClass

import com.google.gson.annotations.SerializedName

data class WorkPhotoData(

    @field:SerializedName("PhotoID")
    val photoID: Int? = null,
    @field:SerializedName("Url")
    val url: String? = null,
    @field:SerializedName("CreatedDate")
    val createdDate: String? = null,
)