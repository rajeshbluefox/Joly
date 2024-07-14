package com.bluefox.joly.jobModule.jobProviderModule.supportFunctions

import android.app.DatePickerDialog
import android.content.Context
import com.bluefox.joly.databinding.FragmentPostJobBinding
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class PostJobUI(
    context: Context,
    mbinding: FragmentPostJobBinding,
    private val onJobPostClicked: (postJobData: PostJobData) -> Unit,
) {
    private val binding: FragmentPostJobBinding = mbinding
    private val mContext: Context = context

    init {
        onClickListeners()
    }

    fun onClickListeners() {
        binding.tvDeadLineToApplyValue.setOnClickListener {
            initDatePicker()
        }

        binding.btSubmit.setOnClickListener {
            getValues()
        }
    }

    private fun getValues() {
        //TODO GetValues from editTexts
        //set the values to PostJobData data class
        // call this callback : onJobPostClicked.invoke(postJobData)

        val postJobData = PostJobData()
        postJobData.phoneNumber = "1"
        postJobData.jobName = "WebDeveloper"
        postJobData.jobDetails = "WebDeveloperDetails"
        postJobData.jobDescription = "WebDeveloperDescription"
        postJobData.eligibility = "WebDeveloperEligibility"
        postJobData.deadLineToApply = "2024-01-01"
        postJobData.skills = "HTML,CSS,JS"

        onJobPostClicked.invoke(postJobData)
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