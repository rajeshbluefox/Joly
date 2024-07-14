package com.bluefox.joly.jobModule.apiFunctions

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSViewModel
import com.bluefox.joly.clientModule.postJob.modalClass.CategoryItem
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions


class JPapiFunctions(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    mJpViewModel: JPViewModel,
    private val onJobPostedResponse: () -> Unit
) {

    private var mContext: Context = context
    private var mLifecycleOwner: LifecycleOwner = lifecycleOwner
    private var jpViewModel: JPViewModel = mJpViewModel

    fun postJob(postJobData: PostJobData) {
        jpViewModel.resetPostJobResponse()
        jpViewModel.postJob(postJobData)

        postJobObservers()
    }

    private fun postJobObservers() {
        jpViewModel.getPostJobResponse().observe(mLifecycleOwner) {
            if (it.status == 200) {
                UtilFunctions.showToast(mContext, "Job Posted Successfully")
                onJobPostedResponse.invoke()
            }
        }
    }

}