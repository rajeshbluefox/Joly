package com.bluefox.joly.jobModule.jobProviderModule.modalClass

import com.google.gson.annotations.SerializedName


data class PostJobData(
    @SerializedName("UserId")
    var userId: Int = 0,
    @SerializedName("JobId")
    var jobId: Int = 0,
    @SerializedName("JobStatus")
    var jobStatus: Int? = 0,
    @SerializedName("JobName")
    var jobName: String? = null,
    @SerializedName("JobDetails")
    var jobDetails: String? = null,
    @SerializedName("JobDescription")
    var jobDescription: String? = null,
    @SerializedName("Eligibility")
    var eligibility: String? = null,
    @SerializedName("DeadLinetoApply")
    var deadLineToApply: String? = null,
    @SerializedName("Skills")
    var skills: String? = null,
    @SerializedName("JobLocation")
    var jobLocation: String? = "hyd",
    @SerializedName("PostedDate")
    var postedDate: String? = "5 Apr",


)