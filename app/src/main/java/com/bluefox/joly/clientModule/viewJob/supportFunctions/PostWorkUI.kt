package com.bluefox.joly.clientModule.viewJob.supportFunctions

import android.R
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import com.bluefox.joly.clientModule.postJob.adapters.CategorySpinnerAdapter
import com.bluefox.joly.clientModule.postJob.adapters.JobsSpinnerAdapter
import com.bluefox.joly.clientModule.postJob.modalClass.CategoryItem
import com.bluefox.joly.clientModule.postJob.modalClass.JobItem
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.SSSelectedData
import com.bluefox.joly.databinding.FragmentPostWorkBinding
import com.bluefox.joly.zSharedPreference.UserDetails
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
import kotlin.time.Duration.Companion.milliseconds

class PostWorkUI(
    context: Context,
    binding: FragmentPostWorkBinding,
    private val onWorkSubmitClicked: (postWorkData: PostWorkData) -> Unit,
) {
    private val mBinding: FragmentPostWorkBinding
    private val mContext: Context

    init {
        mContext = context
        mBinding = binding

        onClickListeners()
    }

    fun showPB() {
        mBinding.progressBar.visibility = View.VISIBLE
        mBinding.contentLt.visibility = View.GONE
    }

    fun hidePB() {
        mBinding.progressBar.visibility = View.GONE
        mBinding.contentLt.visibility = View.VISIBLE
    }

    private fun onClickListeners() {

        mBinding.btSubmit.setOnClickListener {
            getDetails()
        }

    }

    private fun getDetails() {
        val nWorkName = getValue(mBinding.etWorkName)
        val nArea = getValue(mBinding.etSelectArea)
        val nWageOffered = getValue(mBinding.etWageOffered)

        if (nWorkName.isEmpty()) {
            UtilFunctions.showToast(mContext, "Enter WorkName")
            return
        }

        if (SSSelectedData.categoryItem.categoryID == "-1") {
            UtilFunctions.showToast(mContext, "Select Category")
            return
        }

        if (SSSelectedData.jobItem.jobId == "-1") {
            UtilFunctions.showToast(mContext, "Select Job")
            return
        }

        if (nArea.isEmpty()) {
            UtilFunctions.showToast(mContext, "Enter Area")
            return
        }

        if (nWageOffered.isEmpty()) {
            UtilFunctions.showToast(mContext, "Enter Wage Offered")
            return
        }

        val postWorkData = PostWorkData()
        postWorkData.workName = nWorkName
        postWorkData.categoryId = SSSelectedData.categoryItem.categoryID!!.toInt()
        postWorkData.jobId = SSSelectedData.jobItem.jobId!!.toInt()
        postWorkData.areaId = nArea.toInt()
        postWorkData.wageOffered = nWageOffered
        postWorkData.phoneNumber=UserDetails.getUserMobileNo(mContext)

        onWorkSubmitClicked.invoke(postWorkData)

    }

    private var isValidationSuccessfull = true
    fun getValue(view: EditText): String {
        val nValue = view.text.toString()
        return nValue
    }

    fun initCategoriesSpinner(
        categoryList: List<CategoryItem>
    ) {


        val newItem = CategoryItem(
            "-1",
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


                    SSSelectedData.categoryItem = categoryList[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle case when nothing is selected (optional)
                }
            }
    }

    fun initJobsSpinner(
        jobList: List<JobItem>
    ) {


        val newItem = JobItem(
            "-1",
            "Select",

            )

        val newAcList = listOf(newItem) + jobList
        val adapter = JobsSpinnerAdapter(
            mContext,
            R.layout.simple_spinner_item,
            jobList
        )

        mBinding.spSelectJobType.adapter = adapter
        mBinding.spSelectJobType.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    SSSelectedData.jobItem = jobList[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle case when nothing is selected (optional)
                }
            }
    }
}