package com.bluefox.joly.clientModule.login.modelClass

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("MobileNo")
    var mobileNo: String? = null,
    @SerializedName("AlternativeNumber")
    var alternativeNumber: String? = null,
    @SerializedName("PhoneNumber")
    var phoneNumber: String? = null,
    @SerializedName("Password")
    var password: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("UserId")
    val userId: Int = 0,
    @SerializedName("CompanyName")
    val companyName: String? = null,
    @SerializedName("Name")
    val name: String? = null,
    @SerializedName("DateOfBirth")
    val age: String? = null,
    @SerializedName("Gender")
    val gender: String? = null,
    @SerializedName("Address")
    val address: String? = null,
    @SerializedName("Pincode")
    val pincode: String? = null,
    @SerializedName("Location")
    val location: String? = null,
    @SerializedName("City")
    val city: String? = null,
    @SerializedName("State")
    val state: String? = null,
    @SerializedName("Country")
    val country: String? = null,
    @SerializedName("AadharNumber")
    val aadharNumber: String? = null,
    @SerializedName("Photo")
    val photo: String? = null,
    @SerializedName("Skills")
    val skills: String? = null,
    @SerializedName("Qualification")
    val qualification: String? = null,
    @SerializedName("PreviousExperience")
    val previousExperience: String? = null,
    @SerializedName("PortfolioLink")
    val portfolioLink: String? = null,
    @SerializedName("Description")
    val description: String? = null,
    @SerializedName("WorkingHours")
    val workingHours: String? = null,
    @SerializedName("CompanyDescription")
    val companyDescription: String? = null,
    @SerializedName("CompanyWebsiteLink")
    val companyWebsiteLink: String? = null,
    @SerializedName("CompanyContactNumber")
    val companyContactNumber: String? = null,
    @SerializedName("CompanyMail")
    val companyMail: String? = null,
    @SerializedName("CompanyGSTNo")
    val companyGSTNo: String? = null,
    @SerializedName("CurrentRating")
    val currentRating: Double = 0.0,
    @SerializedName("NoOfRating")
    val noOfRatings: Int = 0,
    @SerializedName("PastExperience")
    val pastExperience: String? = null,


    )