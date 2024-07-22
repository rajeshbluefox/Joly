package com.bluefox.joly.jobModule.jobProviderModule.modalClass

import com.bluefox.joly.clientModule.login.modelClass.LoginData

object SelJobDetails {
    var postJobData = PostJobData()
    var applicantProfileData = LoginData()

    var selFragmentJobSeeker = 0

    var appliedJobs = ArrayList<PostJobData>()
    var allJobs = ArrayList<PostJobData>()
}