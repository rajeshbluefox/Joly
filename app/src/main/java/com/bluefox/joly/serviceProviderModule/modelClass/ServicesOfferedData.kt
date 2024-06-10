package com.bluefox.joly.serviceProviderModule.modelClass

import com.google.gson.annotations.SerializedName


data class ServicesOfferedData(
    @SerializedName("CategoryId")
    var categoryId: Int? = null,
    @SerializedName("JobId")
    var jobId: String? = null,
    @SerializedName("PriceRange")
    var priceRange: String? = null,

    )