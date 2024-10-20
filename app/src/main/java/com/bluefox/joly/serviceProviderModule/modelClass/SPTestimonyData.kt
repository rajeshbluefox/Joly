package com.bluefox.joly.serviceProviderModule.modelClass

import com.google.gson.annotations.SerializedName

class SPTestimonyData(

    @field:SerializedName("CustomerName")
    var customerName: String? = null,
    @field:SerializedName("Testimony")
    var testimony: String? = null,
    @field:SerializedName("PhoneNumber")
    var phoneNumber: String? = null,
    @field:SerializedName("Status")
    var status: String? = null,
    @field:SerializedName("ServiceProviderId")
    var serviceProviderId: Int = 0,
    @field:SerializedName("Rating")
    var rating: Int = 0,
    @field:SerializedName("FeedbackProviderId")
    var feedbackProviderId: Int = 0,
    @field:SerializedName("CreatedDate")
    var createdDate: String? = null,

)
