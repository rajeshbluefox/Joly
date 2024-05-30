package com.bluefox.joly.clientModule.login.modelClass

import com.google.gson.annotations.SerializedName

class SSRegistrationDetailsData(

    @field:SerializedName("Name")
    var name: String? = null,
    @field:SerializedName("PhoneNumber")
    var phoneNumber: String? = null,
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
    @field:SerializedName("Password")
    var password: String? = null,
    @field:SerializedName("ConfirmPassword")
    var confirmPassword: String? = null,


)