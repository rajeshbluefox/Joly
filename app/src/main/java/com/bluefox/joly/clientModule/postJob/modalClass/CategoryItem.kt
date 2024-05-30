package com.bluefox.joly.clientModule.postJob.modalClass

import com.google.gson.annotations.SerializedName



data class CategoryItem(

    @field:SerializedName("CategoryID")
    val categoryID: Int? = null,
    @field:SerializedName("CategoryName")
    val categoryName: String? = null,
    @field:SerializedName("CreatedDate")
    val createdDate: String? = null,
)