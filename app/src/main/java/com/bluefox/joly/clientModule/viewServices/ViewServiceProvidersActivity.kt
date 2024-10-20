package com.bluefox.joly.clientModule.viewServices

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSViewModel
import com.bluefox.joly.clientModule.viewJob.modalClass.SSSelected
import com.bluefox.joly.clientModule.viewServices.modelClass.SelSPData
import com.bluefox.joly.clientModule.viewServices.modelClass.ServiceProviderDetailsData
import com.bluefox.joly.clientModule.viewServices.supportFuncation.ViewSPAdapter
import com.bluefox.joly.databinding.ActivityViewServiceProvidersBinding
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewServiceProvidersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewServiceProvidersBinding

    private lateinit var ssViewModel: SSViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewServiceProvidersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ssViewModel = ViewModelProvider(this)[SSViewModel::class.java]

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources, binding.appBarLt)

        val categoryId = SSSelected.workData.categoryId

        getServiceProviders(categoryId!!)
        onClickListeners()
        editTextListener()
    }

    fun onClickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }

    }

    private fun getServiceProviders(categoryId: Int) {
        ssViewModel.resetGetServiceProviders()
        ssViewModel.getServiceProviders(categoryId)
        getServiceProvidersObserver()
    }

    private fun getServiceProvidersObserver() {
        ssViewModel.getServiceProvidersResponse().observe(this) {
            if (it.code != 195) {
                if (it.code == 200) {
                    serviceProviderList.clear()
                    serviceProviderList.addAll(it.serviceProviderDetailsData)
                    initServiceProviderDetailsRV(serviceProviderList)
                } else {
                    UtilFunctions.showToast(this, "Something Went Wrong")
                }

            } else {
                UtilFunctions.showToast(this, "Something Went Wrong")
            }
        }
    }

    private var serviceProviderList = ArrayList<ServiceProviderDetailsData>()

    private fun initServiceProviderDetailsRV(spList: ArrayList<ServiceProviderDetailsData>) {

        val viewSPAdapter = ViewSPAdapter(
            this,
            spList,
            ::onServiceProviderClick
        )

        binding.rvServiceProviders.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = viewSPAdapter
        }

    }

    private fun onServiceProviderClick(serviceProviderDetailsData: ServiceProviderDetailsData) {
        SelSPData.serviceProviderDetailsData = serviceProviderDetailsData
        CallIntent.gotoViewSPDetailsActivity(this, false, this)
    }

    private var isFilteredList = false

    private fun editTextListener() {

        binding.searchBar.doOnTextChanged { text, _, _, _ ->

            Log.e("Test", text.toString())

            if (text != null) {

                isFilteredList = if (text.isEmpty()) {
                    initServiceProviderDetailsRV(serviceProviderList)
                    false
                } else {
                    filterItems(text.toString())
                    true
                }
            }
        }

    }

    private var filteredSPList: ArrayList<ServiceProviderDetailsData> = ArrayList()

    private fun filterItems(text: String) {

        filteredSPList.clear()

        for (localItems in serviceProviderList) {

            val extractedName = localItems.companyName!!.lowercase()

            if (extractedName.startsWith(text.lowercase())) {
                filteredSPList.add(localItems)
            }
        }

        initServiceProviderDetailsRV(filteredSPList)
    }
}