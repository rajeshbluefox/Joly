package com.bluefox.joly.serviceProviderModule.modelClass

import com.google.gson.annotations.SerializedName


class AddServiceData(

    @field:SerializedName("PhoneNumber")
    var phoneNumber: String? = null,
    @field:SerializedName("CategoryId")
    var categoryId: String? = null,
    @field:SerializedName("JobId")
    var jobId: String? = null,
    @field:SerializedName("PriceRange")
    var priceRange: String? = null,
    @field:SerializedName("Status")
    var status: String? = null,

    )