package com.bluefox.joly.clientModule.postJob.modalClass

import android.net.Uri
import okhttp3.MultipartBody

object SSSelectedData {
    var jobItem = JobItem()
    var categoryItem = CategoryItem()

    var imagePart: MultipartBody.Part? = null

    var parts = mutableListOf<MultipartBody.Part>()

    var workPhotosUris: MutableList<Uri> = mutableListOf()


    fun reset()
    {
        parts = mutableListOf()
        workPhotosUris = mutableListOf()
    }

}