package com.bluefox.joly.jobModule.jobProviderModule.supportFunctions

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.databinding.ActivityViewPostedJobBinding
import com.bluefox.joly.databinding.FragmentPostJobBinding
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.SelJobDetails
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class ViewPostedJobUI(
    context: Context,
    mbinding: ActivityViewPostedJobBinding,
    private val onUpdateJobClicked: (postJobData: PostJobData) -> Unit,
    private val onUpdateJobStatus: (jobId: String, jobStatus: String) -> Unit
) {
    private val binding: ActivityViewPostedJobBinding = mbinding
    private val mContext: Context = context

    init {
        setDetails()
        onClickListeners()
    }

    private fun setDetails() {
        val postJobData = SelJobDetails.postJobData

        binding.etJobName.setText(postJobData.jobName)
        binding.etJobDetails.setText(postJobData.jobDetails)
        binding.etJobDescription.setText(postJobData.jobDescription)
        binding.etEligibility.setText(postJobData.eligibility)
        binding.etSkillsRequired.setText(postJobData.skills)
        binding.tvDeadLineToApplyValue.text = postJobData.deadLineToApply
    }

    fun onClickListeners() {
        binding.tvDeadLineToApplyValue.setOnClickListener {
            initDatePicker()
        }

        binding.btUpdateJob.setOnClickListener {
            getValues()
        }

        binding.btCloseJob.setOnClickListener {
            if (SelJobDetails.postJobData.jobStatus == 1) {
                onUpdateJobStatus.invoke(SelJobDetails.postJobData.jobId.toString(), "0")
            } else {
                onUpdateJobStatus.invoke(SelJobDetails.postJobData.jobId.toString(), "1")
            }
        }

        binding.btViewApplication.setOnClickListener {
            CallIntent.gotoViewApplicationsActivity(mContext, false, mContext as Activity)
        }
    }

    private fun getValues() {
        //TODO GetValues from editTexts
        //set the values to PostJobData data class
        // call this callback : onJobPostClicked.invoke(postJobData)


        val nJobName = binding.etJobName.text.toString()
        val nJobDetails = binding.etJobDetails.text.toString()
        val nJobDescription = binding.etJobDescription.text.toString()
        val nEligibility = binding.etEligibility.text.toString()
        val nDeadLineToApply = binding.tvDeadLineToApplyValue.text.toString()
        val nSkills = binding.etSkillsRequired.text.toString()

        if (nJobName.isEmpty()) {
            UtilFunctions.showToast(mContext, "Enter JobName")
            return
        }
        if (nJobDetails.isEmpty()) {
            UtilFunctions.showToast(mContext, "Enter JobDetails")
            return
        }
        if (nJobDescription.isEmpty()) {
            UtilFunctions.showToast(mContext, "Enter JobDescription")
            return

        }
        if (nEligibility.isEmpty()) {
            UtilFunctions.showToast(mContext, "Enter Eligibility")
            return

        }
        if (nDeadLineToApply.isEmpty()) {
            UtilFunctions.showToast(mContext, "Enter DeadLineToApply")
            return
        }
        if (nSkills.isEmpty()) {
            UtilFunctions.showToast(mContext, "Enter Skills")
            return
        }

        val postJobData = PostJobData()
        postJobData.userId = SSProfileData.mLoginData.userId
        postJobData.jobName = nJobName
        postJobData.jobDetails = nJobDetails
        postJobData.jobDescription = nJobDescription
        postJobData.eligibility = nEligibility
        postJobData.deadLineToApply = nDeadLineToApply
        postJobData.skills = nSkills

        onUpdateJobClicked.invoke(postJobData)
    }


    private val cal = Calendar.getInstance()
    private var selectedDate = ""

    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat1 = "yyyy-MM-dd"
            val sdfCalc = SimpleDateFormat(myFormat1, Locale.US)

            val selectedDateForCalculation = sdfCalc.format(cal.time)

            binding.tvDeadLineToApplyValue.text = "${selectedDateForCalculation}"
            selectedDate = selectedDateForCalculation

        }

    private fun initDatePicker() {

        val datePic = DatePickerDialog(
            mContext, dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )

        datePic.show()
    }


}