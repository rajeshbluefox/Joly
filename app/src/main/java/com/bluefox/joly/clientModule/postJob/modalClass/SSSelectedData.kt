package com.bluefox.joly.clientModule.postJob.modalClass

import android.net.Uri
import okhttp3.MultipartBody

object SSSelectedData {
    var jobItem = JobItem()
    var categoryItem = CategoryItem()
    var selCity = City()
    var selDistrict = District()

    var isPhotoSelected = false

    var registerPhoto : MultipartBody.Part? = null

    var imagePart: MultipartBody.Part? = null

    var auido: MultipartBody.Part? = null

    var parts = mutableListOf<MultipartBody.Part>()

    var workPhotosUris: MutableList<Uri> = mutableListOf()


    fun reset()
    {
        parts = mutableListOf()
        workPhotosUris = mutableListOf()
    }

}