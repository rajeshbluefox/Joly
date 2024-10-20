package com.bluefox.joly.clientModule.viewJob.supportFunctions

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import com.bluefox.joly.clientModule.postJob.adapters.CityAdapter
import com.bluefox.joly.clientModule.postJob.adapters.DistrictAdapter
import com.bluefox.joly.clientModule.postJob.modalClass.City
import com.bluefox.joly.clientModule.postJob.modalClass.District
import com.bluefox.joly.clientModule.postJob.modalClass.SSSelectedData
import com.bluefox.joly.databinding.AlertAreaFilterBinding
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions


class DialogFilter(
    layoutInflater: LayoutInflater,
    context: Context,
    private val onSubmitClicked: (city: String, district: String) -> Unit

) {
    private val mLayoutInflater: LayoutInflater
    private val mContext: Context

    private var binding: AlertAreaFilterBinding
    private var dialog: AlertDialog.Builder
    private var messageBoxInstance: AlertDialog

    var citySelected = ""
    var districtSelected = ""

    init {
        mLayoutInflater = layoutInflater
        mContext = context
        binding = AlertAreaFilterBinding.inflate(mLayoutInflater)
        dialog = AlertDialog.Builder(mContext).setView(binding.root)
        dialog.setCancelable(true)
        messageBoxInstance = dialog.create()

        initDistrictsSpinner(UtilFunctions.getDistricts())
        onClickListener()
    }

    fun showFilterDialog()
    {
        messageBoxInstance.show()
    }

    private fun onClickListener() {
        binding.btFilter.setOnClickListener {
            onSubmitClicked(
                citySelected,
                districtSelected
            )
            messageBoxInstance.dismiss()
        }
    }

    private fun initDistrictsSpinner(
        districtsList: List<District>
    ) {

        val newItem = District(
            0,
            "All Districts"
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
                    districtSelected = if (position != 0) {
                        districtsList[position - 1].districtName
                    }else{
                        "All Districts"
                    }


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
            "All Cities"
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
                    citySelected = if (position != 0)
                        citiesList[position-1].CityName
                    else{
                        "All Cities"
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle case when nothing is selected (optional)
                }
            }
    }

}