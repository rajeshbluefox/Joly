package com.bluefox.joly.jobModule.jobProviderModule.supportFunctions

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.AdapterView
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.postJob.adapters.CityAdapter
import com.bluefox.joly.clientModule.postJob.adapters.DistrictAdapter
import com.bluefox.joly.clientModule.postJob.modalClass.City
import com.bluefox.joly.clientModule.postJob.modalClass.District
import com.bluefox.joly.clientModule.postJob.modalClass.SSSelectedData
import com.bluefox.joly.databinding.FragmentPostJobBinding
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
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
        initDistrictsSpinner(UtilFunctions.getDistricts())
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


        val nJobName = binding.etJobName.text.toString()
        val nJobDetails = binding.etJobDetails.text.toString()
        val nJobDescription = binding.etJobDescription.text.toString()
        val nEligibility = binding.etEligibility.text.toString()
        val nDeadLineToApply = binding.tvDeadLineToApplyValue.text.toString()
        val nSkills = binding.etSkillsRequired.text.toString()

        if(nJobName.isEmpty())
        {
            UtilFunctions.showToast(mContext , "Enter JobName")
            return
        }
        if (nJobDetails.isEmpty())
        {
            UtilFunctions.showToast(mContext, "Enter JobDetails")
            return
        }
        if (nJobDescription.isEmpty())
        {
            UtilFunctions.showToast(mContext, "Enter JobDescription")
            return

        }
        if (nEligibility.isEmpty())
        {
            UtilFunctions.showToast(mContext, "Enter Eligibility")
            return

        }
        if (nDeadLineToApply.isEmpty())
        {
            UtilFunctions.showToast(mContext, "Enter DeadLineToApply")
            return
        }
        if (nSkills.isEmpty())
        {
            UtilFunctions.showToast(mContext, "Enter Skills")
            return
        }

        if (SSSelectedData.selDistrict.districtId == 0) {
            UtilFunctions.showToast(mContext, "Select District")
            return
        }

        if (SSSelectedData.selCity.CityId == 0) {
            UtilFunctions.showToast(mContext, "Select City")
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
        postJobData.city= SSSelectedData.selCity.CityName
        postJobData.district=SSSelectedData.selDistrict.districtName

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

    fun initDistrictsSpinner(
        districtsList: List<District>
    ) {

        val newItem = District(
            0,
            "Select"
        )

        val newDistrictsList = listOf(newItem) + districtsList

        val adapter = DistrictAdapter(
            mContext,
            android.R.layout.simple_spinner_item,
            newDistrictsList
        )

        binding.spSelectDistrict.adapter = adapter
        binding.spSelectDistrict.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0)
                        SSSelectedData.selDistrict = districtsList[position-1]

                    when (position) {
                        0 -> {
                            initCitiesSpinner(emptyList())
                        }

                        1 -> {
                            initCitiesSpinner(UtilFunctions.getThiruvananthapuramCities())
                        }

                        2 -> {
                            initCitiesSpinner(UtilFunctions.getKollamCities())
                        }

                        3 -> {
                            initCitiesSpinner(UtilFunctions.getPathanamthittaCities())
                        }

                        4 -> {
                            initCitiesSpinner(UtilFunctions.getAlappuzhaCities())
                        }

                        5 -> {
                            initCitiesSpinner(UtilFunctions.getKottayamCities())
                        }

                        6 -> {
                            initCitiesSpinner(UtilFunctions.getIdukkiCities())
                        }

                        7 -> {
                            initCitiesSpinner(UtilFunctions.getErnakulamCities())
                        }

                        8 -> {
                            initCitiesSpinner(UtilFunctions.getThrissurCities())
                        }

                        9 -> {
                            initCitiesSpinner(UtilFunctions.getPalakkadCities())
                        }

                        10 -> {
                            initCitiesSpinner(UtilFunctions.getMalappuramCities())
                        }

                        11 -> {
                            initCitiesSpinner(UtilFunctions.getKozhikodeCities())
                        }

                        12 -> {
                            initCitiesSpinner(UtilFunctions.getWayanadCities())
                        }

                        13 -> {
                            initCitiesSpinner(UtilFunctions.getKannurCities())
                        }

                        14 -> {
                            initCitiesSpinner(UtilFunctions.getKasaragodCities())
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle case when nothing is selected (optional)
                }
            }
    }

    private fun initCitiesSpinner(
        citiesList: List<City>
    ) {

        val newItem = City(
            0, 0,
            "Select"
        )

        val newCityList = listOf(newItem) + citiesList

        val adapter = CityAdapter(
            mContext,
            android.R.layout.simple_spinner_item,
            newCityList
        )

        binding.spSelectCity.adapter = adapter
        binding.spSelectCity.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0)
                        SSSelectedData.selCity = citiesList[position-1]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle case when nothing is selected (optional)
                }
            }
    }
}