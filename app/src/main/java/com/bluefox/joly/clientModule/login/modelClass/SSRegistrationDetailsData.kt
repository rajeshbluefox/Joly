package com.bluefox.joly.clientModule.login.modelClass

import com.google.gson.annotations.SerializedName

class SSRegistrationDetailsData(

    @field:SerializedName("Name")
    var name: String? = null,
    @field:SerializedName("PhoneNumber")
    var phoneNumber: String? = null,
    @field:SerializedName("AlternativeNumber")
    var alternativeNumber: String? = null,
    @field:SerializedName("AadharNumber")
    var aadharNumber: String? = null,
    @field:SerializedName("DateOfBirth")
    var dateOfBirth: String? = null,
    @field:SerializedName("Gender")
    var gender: Int? = 0,
    @field:SerializedName("PinCode")
    var pinCode: String? = null,
    @field:SerializedName("Location")
    var location: String? = null,
    @field:SerializedName("City")
    var city: String? = null,
    @field:SerializedName("State")
    var state: String? = null,
    @field:SerializedName("Country")
    var country: String? = null,
    @field:SerializedName("Address")
    var address: String? = null,
    @field:SerializedName("Password")
    var password: String? = null,
    @field:SerializedName("ConfirmPassword")
    var confirmPassword: String? = null,

    @SerializedName("Skills")
    var skills: String? = null,

    @SerializedName("Qualification")
    var qualification: String? = null,
    @SerializedName("PreviousExperience")
    var previousExperience: String? = null,
    @SerializedName("PortfolioLink")
    var portfolioLink: String? = null,
    @SerializedName("Description")
    var description: String? = null,
    @SerializedName("WorkingHours")
    var workingHours: String? = null,

    @SerializedName("CompanyName")
    var companyName: String? = null,
    @SerializedName("CompanyLocation")
    var companyLocation: String? = null,
    @SerializedName("CompanyContact")
    var companyContact: String? = null,
    @SerializedName("CompanyMail")
    var companyMail: String? = null,
    @SerializedName("CompanyGSTNO")
    var companyGSTNO: String? = null


)