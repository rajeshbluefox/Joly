package com.bluefox.joly.clientModule.viewJob.supportFunctions

import android.content.Context
import android.view.View
import com.bluefox.joly.databinding.FragmentViewJobsBinding


class ViewJobsUI(
    context: Context,
    binding: FragmentViewJobsBinding,
) {
    private val mBinding: FragmentViewJobsBinding
    private val mContext: Context

    init {
        mContext = context
        mBinding = binding

    }

    fun showPB() {
        mBinding.progressBar.visibility = View.VISIBLE
        mBinding.rvJobs.visibility = View.GONE
    }

    fun hidePB() {
        mBinding.progressBar.visibility = View.GONE
        mBinding.rvJobs.visibility = View.VISIBLE
    }

}