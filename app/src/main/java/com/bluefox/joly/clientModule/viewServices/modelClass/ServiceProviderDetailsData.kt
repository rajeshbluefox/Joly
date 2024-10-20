package com.bluefox.joly.clientModule.viewServices.modelClass

import com.google.gson.annotations.SerializedName

data class ServiceProviderDetailsData(

    @field:SerializedName("ServiceId")
    val serviceId: Int? = 0,
    @field:SerializedName("CompanyName")
    var companyName: String? = null,
    @field:SerializedName("Address")
    val address: String? = null,
    @field:SerializedName("City")
    val city: String? = null,
    @field:SerializedName("State")
    val state: String? = null,
    @field:SerializedName("Description")
    val description: String? = null,
    @field:SerializedName("WorkingHours")
    val workingHours: String? = null,
    @field:SerializedName("Categories")
    val categories: String? = null,
    @field:SerializedName("Jobs")
    val jobs: String? = null,
    @field:SerializedName("PriceRange")
    val priceRange: String? = null,
    @field:SerializedName("Photo")
    val photo: String? = null,
    @field:SerializedName("PortfolioLink")
    val portfolioLink: String? = null,
    @field:SerializedName("PhoneNumber")
    val phoneNumber: String? = null,
    @field:SerializedName("AlternativeNumber")
    val alternativeNumber: String? = null,
    @field:SerializedName("CurrentRating")
    val currentRating: Float = 0f,
    @field:SerializedName("NoOfRating")
    val noOfRating: Int = 0

)