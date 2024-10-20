package com.bluefox.joly.clientModule.postJob.supportFunctions

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.postJob.adapters.CategorySpinnerAdapter
import com.bluefox.joly.clientModule.postJob.adapters.CityAdapter
import com.bluefox.joly.clientModule.postJob.adapters.DistrictAdapter
import com.bluefox.joly.clientModule.postJob.adapters.JobsSpinnerAdapter
import com.bluefox.joly.clientModule.postJob.modalClass.CategoryItem
import com.bluefox.joly.clientModule.postJob.modalClass.City
import com.bluefox.joly.clientModule.postJob.modalClass.District
import com.bluefox.joly.clientModule.postJob.modalClass.JobItem
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.SSSelectedData
import com.bluefox.joly.clientModule.postJob.modalClass.ServicesCatJob
import com.bluefox.joly.databinding.FragmentPostWorkBinding
import com.bluefox.joly.zSharedPreference.UserDetails
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
import com.google.android.material.chip.Chip

class PostWorkUI(
    context: Context,
    binding: FragmentPostWorkBinding,
    private val onWorkSubmitClicked: (postWorkData: PostWorkData) -> Unit,
    private val onTCClicked: () -> Unit,
) {
    private val mBinding: FragmentPostWorkBinding
    private val mContext: Context

    init {
        mContext = context
        mBinding = binding

        onClickListeners()
        initDistrictsSpinner(UtilFunctions.getDistricts())
        chipSelectionListener()
    }

    fun showPB() {
        mBinding.progressBar.visibility = View.VISIBLE
        mBinding.contentLt.visibility = View.GONE
    }

    fun hidePB() {
        mBinding.progressBar.visibility = View.GONE
        mBinding.contentLt.visibility = View.VISIBLE
    }

    private var isTCAccepted = false

    private fun onClickListeners() {

        mBinding.btSubmit.setOnClickListener {
            getDetails()
        }
        mBinding.cbAgree.setOnCheckedChangeListener { _, b ->
            isTCAccepted = b
        }

        mBinding.btTNC.setOnClickListener {
            onTCClicked.invoke()
        }

    }

    private var isAudioAttached = false

    fun setAudioAttached(status: Boolean) {
        isAudioAttached = status
    }

    private fun getDetails() {
        val nWorkName = getValue(mBinding.etWorkName)
        var nWorkDescription = getValue(mBinding.etWorkDescription)
//        val nArea = getValue(mBinding.etSelectArea)
        val nArea = "5"
        val nWageOffered = getValue(mBinding.etWageOffered)

        if (nWorkName.isEmpty()) {
            UtilFunctions.showToast(mContext, "Enter WorkName")
            return
        }

        if (!isAudioAttached) {
            if (nWorkDescription.isEmpty()) {
                UtilFunctions.showToast(mContext, "Enter WorkDescription")
                return
            }
        } else {
            if (nWorkDescription.isEmpty()) {
                nWorkDescription = "E"
            }
        }

        if (SSSelectedData.categoryItem.categoryID == null) {
            UtilFunctions.showToast(mContext, "Select Category")
            return
        }

//        if (SSSelectedData.jobItem.jobId == null) {
//            UtilFunctions.showToast(mContext, "Select Job")
//            return
//        }

        if (SSSelectedData.selDistrict.districtId == 0) {
            UtilFunctions.showToast(mContext, "Select District")
            return
        }

        if (SSSelectedData.selCity.CityId == 0) {
            UtilFunctions.showToast(mContext, "Select City")
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

        val nSelectedTime = mBinding.etTimeOther.text.toString()

        if (nSelectedTime.isEmpty()) {
            UtilFunctions.showToast(mContext, "Select Time")
            return
        }

        if (!isTCAccepted) {
            UtilFunctions.showToast(mContext, "Please Accept Terms and Conditions")
            return
        }

        val postWorkData = PostWorkData()
        postWorkData.workName = nWorkName
        postWorkData.workDescription = nWorkDescription
        postWorkData.categoryId = SSSelectedData.categoryItem.categoryID!!.toInt()
        postWorkData.jobId = 1
        postWorkData.district = SSSelectedData.selDistrict.districtName
        postWorkData.city = SSSelectedData.selCity.CityName
        postWorkData.areaId = nArea.toInt()
        postWorkData.wageOffered = nWageOffered
        postWorkData.phoneNumber = UserDetails.getUserMobileNo(mContext)
        postWorkData.deadlineTime = selectedTime

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
            null,
            "Select",

            )

        val newAcList = listOf(newItem) + categoryList

        val adapter = CategorySpinnerAdapter(
            mContext,
            android.R.layout.simple_spinner_item,
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

                    filterJobsByCatId(categoryList[position].categoryID!!)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle case when nothing is selected (optional)
                }
            }
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

        mBinding.spSelectDistrict.adapter = adapter
        mBinding.spSelectDistrict.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0)
                        SSSelectedData.selDistrict = districtsList[position - 1]

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

        mBinding.spSelectCity.adapter = adapter
        mBinding.spSelectCity.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0)
                        SSSelectedData.selCity = citiesList[position - 1]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle case when nothing is selected (optional)
                }
            }
    }

    fun filterJobsByCatId(categoryId: Int) {
        val filteredJobs =
            ServicesCatJob.jobList.filter { it.categoryId == SSSelectedData.categoryItem.categoryID }
        initJobsSpinner(filteredJobs)
    }

    fun initJobsSpinner(
        jobList: List<JobItem>
    ) {


        val newItem = JobItem(
            null,
            null,
        )

        val newAcList = listOf(newItem) + jobList
        val adapter = JobsSpinnerAdapter(
            mContext,
            android.R.layout.simple_spinner_item,
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

    var selectedTime = ""

    fun chipSelectionListener() {
        mBinding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                // Loop through all the chips to reset their background color to default
                for (i in 0 until mBinding.chipGroup.childCount) {
                    val chip = mBinding.chipGroup.getChildAt(i) as Chip
                    chip.setChipBackgroundColorResource(R.color.default_chip_color) // Default color for unselected chips
                }

                val selectedChip = mBinding.chipGroup.findViewById<Chip>(checkedId)
                // Change the background color of the selected chip
                selectedChip.setChipBackgroundColorResource(R.color.selected_chip_color)

                // Handle the selection
                when (checkedId) {
                    R.id.chip_3_hours -> handleTimeSelection("3 Hours")
                    R.id.chip_6_hours -> handleTimeSelection("6 Hours")
                    R.id.chip_12_hours -> handleTimeSelection("12 Hours")
                    R.id.chip_1_day -> handleTimeSelection("1 day")
                    R.id.chip_2_days -> handleTimeSelection("2 days")
                    R.id.chip_3_days -> handleTimeSelection("3 days")
                    R.id.chip_Other -> handleMoreTimeSelection()
                }
            }


        }
    }

    private fun handleTimeSelection(hours: String) {
        selectedTime = hours
        mBinding.etTimeOther.setText(hours)
        mBinding.etTimeOther.visibility = View.GONE
    }

    private fun handleMoreTimeSelection() {
        // Handle the case when "More" is selected
        selectedTime = ""
        mBinding.etTimeOther.setText("More then 3 days")
        mBinding.etTimeOther.visibility = View.VISIBLE
    }

}