package com.bluefox.joly.clientModule.viewJob.modalClass

import com.google.gson.annotations.SerializedName

data class CategoryData(
    @field:SerializedName("CategoryId")
    val categoryId: Int? = 0,
    @field:SerializedName("CategoryName")
    var categoryName: String? = null,

)