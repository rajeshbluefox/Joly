package com.bluefox.joly.serviceProviderModule.modelClass

import com.google.gson.annotations.SerializedName

class SPTestimonyData(

    @field:SerializedName("CustomerName")
    var customerName: String? = null,
    @field:SerializedName("Testimony")
    var testimony: String? = null,
    @field:SerializedName("PhoneNumber")
    var phoneNumber: String? = null,

)
