package com.bluefox.joly.clientModule.viewJob

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.viewJob.modalClass.CategoryData
import com.bluefox.joly.clientModule.viewJob.supportFunctions.ServiceProviderListAdapter
import com.bluefox.joly.clientModule.viewServices.modelClass.ServiceProviderDetailsData
import com.bluefox.joly.clientModule.viewServices.supportFuncation.ServiceProviderDetailsAdapter
import com.bluefox.joly.databinding.ActivityViewJobDetailsBinding
import com.bluefox.joly.databinding.ActivityViewServiceProviderDetailsBinding
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zCommonFunctions.StatusBarUtils

class ViewServiceProviderDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewServiceProviderDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewServiceProviderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBar(this)
        StatusBarUtils.setTopPadding(resources,binding.appBarLt)

        initServiceCategoryDataRV()
    }




    private fun fillDummyList()
    {
        var item1 = CategoryData()
        item1.categoryName = "Category1"

        var item2 = CategoryData()
        item2.categoryName="Category2"

        var item3 = CategoryData()
        item3.categoryName="Category3"

        var item4 = CategoryData()
        item4.categoryName="Category4"

        serviceCategoryList.add(item1)
        serviceCategoryList.add(item2)
        serviceCategoryList.add(item3)
        serviceCategoryList.add(item4)

    }

    private var serviceCategoryList = ArrayList<CategoryData>()
    private fun initServiceCategoryDataRV()
    {
        fillDummyList()
        val serviceProviderListAdapter = ServiceProviderListAdapter(
            this,
            serviceCategoryList,
            ::onServiceProviderClick
        )

        binding.rvServiceOffered.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = serviceProviderListAdapter
        }

    }

    private fun onServiceProviderClick()
    {


    }
}