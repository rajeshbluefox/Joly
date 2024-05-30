package com.bluefox.joly.clientModule.postJob.modalClass

object ServicesCatJob {

    var categoriesList = ArrayList<CategoryItem>()
    var jobList = ArrayList<JobItem>()

    var isDataFetched = false
    fun reset()
    {
        categoriesList = ArrayList<CategoryItem>()
        jobList = ArrayList<JobItem>()

        isDataFetched = false

    }


}