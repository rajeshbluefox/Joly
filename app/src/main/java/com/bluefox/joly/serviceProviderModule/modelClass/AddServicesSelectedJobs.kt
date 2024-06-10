package com.bluefox.joly.serviceProviderModule.modelClass

import com.bluefox.joly.clientModule.postJob.modalClass.CategoryItem

object AddServicesSelectedJobs {

    //AddService Elements
    var categoryId = -1
    var jobsTypes = ""

    var providingCategories = ArrayList<ServicesOfferedData>()
    var filteredCategoriesList = ArrayList<CategoryItem>()

    var selectedJobsList = ArrayList<Int>()

    //View Service Elements
    var selService = ServicesOfferedData()

    var selectedPage = -1
    fun reset()
    {
        categoryId = -1
        jobsTypes = ""

        filteredCategoriesList = ArrayList<CategoryItem>()
        selectedJobsList = ArrayList<Int>()
    }

    fun resetViewServicesElements()
    {
        selService = ServicesOfferedData()

        selectedPage = -1
    }
}