package com.bluefox.joly.clientModule.profile.modalClass

import com.google.gson.annotations.SerializedName

class SSProfileDetailsData (

    @field:SerializedName("Name")
    var name: String? = null,
    @field:SerializedName("AadharNumber")
    var aadharNumber: String? = null,
    @field:SerializedName("DateOfBirth")
    var dateOfBirth: String? = null,
    @field:SerializedName("Gender")
    var gender: Int? = 0,
    @field:SerializedName("PinCode")
    var pinCode: String? = null,
    @field:SerializedName("Address")
    var address: String? = null,
)



