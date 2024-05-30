package com.bluefox.joly.clientModule.login.modelClass

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("MobileNo")
    var phoneNumber: String? = null,
    @SerializedName("MobileNumber")
    var mobileNumber: String? = null,
    @SerializedName("Password")
    var password: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("Name")
    val name: String? = null,
    @SerializedName("Age")
    val age: String? = null,
    @SerializedName("Gender")
    val gender: String? = null,
    @SerializedName("Address")
    val address: String? = null,
    @SerializedName("Pincode")
    val pincode: String? = null,
    @SerializedName("Location")
    val location: String? = null,
    @SerializedName("AadharNumber")
    val aadharNumber: String? = null,
    @SerializedName("Photo")
    val photo: String? = null,
    @SerializedName("Qualification")
    val qualification: String? = null,
    @SerializedName("PreviousExperience")
    val previousExperience: String? = null,
    @SerializedName("PortfolioLink")
    val portfolioLink: String? = null,
    @SerializedName("Description")
    val description: String? = null,
)