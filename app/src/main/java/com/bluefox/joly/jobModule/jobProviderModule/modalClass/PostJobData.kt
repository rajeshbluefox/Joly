package com.bluefox.joly.jobModule.jobProviderModule.modalClass

import com.google.gson.annotations.SerializedName


data class PostJobData(
    @SerializedName("PhoneNumber")
    var phoneNumber: String? = null,
    @SerializedName("JobName")
    var jobName: String? = null,
    @SerializedName("JobDetails")
    var jobDetails: String? = null,
    @SerializedName("JobDescription")
    var jobDescription: String? = null,
    @SerializedName("Eligibility")
    var eligibility: String? = null,
    @SerializedName("DeadLineToApply")
    var deadLineToApply: String? = null,
    @SerializedName("Skills")
    var skills: String? = null,
)