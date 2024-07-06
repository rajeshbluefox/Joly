package com.bluefox.joly.jobModule.apiFunctions

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSViewModel
import com.bluefox.joly.clientModule.postJob.modalClass.CategoryItem



class JPapiFunctions(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    ssViewModel: SSViewModel,
    private val onCategoriesResponse: (categoriesList: List<CategoryItem>) -> Unit)
{

    private var mContext: Context
    private var mLifecycleOwner: LifecycleOwner
    private var mssViewModel: SSViewModel

    init {
        mContext = context
        mLifecycleOwner = lifecycleOwner
        mssViewModel = ssViewModel
    }

}