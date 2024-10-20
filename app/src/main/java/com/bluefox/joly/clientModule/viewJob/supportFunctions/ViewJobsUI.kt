package com.bluefox.joly.clientModule.viewJob.supportFunctions

import android.R
import android.content.Context
import android.view.View
import android.widget.AdapterView
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.postJob.adapters.CategorySpinnerAdapter
import com.bluefox.joly.clientModule.postJob.modalClass.CategoryItem
import com.bluefox.joly.databinding.FragmentViewJobsBinding


class ViewJobsUI(
    context: Context,
    binding: FragmentViewJobsBinding,
    private val onCategorySelectedClicked: (categoryItem: CategoryItem) -> Unit,

    ) {
    private val mBinding: FragmentViewJobsBinding
    private val mContext: Context

    init {
        mContext = context
        mBinding = binding

        if(SSProfileData.UserRole==1)
        {
            hideFilter()
        }else{
            showfilter()
        }

    }

    fun showPB() {
        mBinding.progressBar.visibility = View.VISIBLE
        mBinding.rvJobs.visibility = View.GONE
    }

    fun hidePB() {
        mBinding.progressBar.visibility = View.GONE
        mBinding.rvJobs.visibility = View.VISIBLE
    }

   fun showfilter()
   {
       mBinding.filterLt.visibility=View.VISIBLE
   }

    fun hideFilter()
    {
        mBinding.filterLt.visibility=View.GONE
    }

    fun initCategoriesSpinner(
        categoryList: List<CategoryItem>
    ) {


        val newItem = CategoryItem(
            null,
            "Select",

            )

        val newAcList = listOf(newItem) + categoryList

        val adapter = CategorySpinnerAdapter(
            mContext,
            R.layout.simple_spinner_item,
            categoryList
        )

        mBinding.spSelectCategory.adapter = adapter
        mBinding.spSelectCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    onCategorySelectedClicked.invoke(categoryList[position])
//                    SSSelectedData.categoryItem = categoryList[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle case when nothing is selected (optional)
                }
            }
    }

    fun onClickListeners()
    {
        mBinding.btFilter.setOnClickListener {
        }

    }

}