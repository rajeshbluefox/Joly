package com.bluefox.joly.jobModule.jobProviderModule.supportFunctions

import android.content.Context
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.databinding.FragmentPostJobBinding
import com.bluefox.joly.databinding.FragmentPostWorkBinding


class PostJobUI(
    context: Context,
    binding: FragmentPostJobBinding,
    private val onJobSubmitClicked: (postWorkData: PostWorkData) -> Unit,
) {
    private val mBinding: FragmentPostJobBinding
    private val mContext: Context

    init {
        mContext=context
        mBinding=binding
    }
}