package com.bluefox.joly.clientModule.postJob.modalClass

import com.google.gson.annotations.SerializedName


data class CategoryListItem(

    @field:SerializedName("CategoryId")
    val categoryId: String? = null,

    @field:SerializedName("CategoryName")
    val categoryName: String? = null
)