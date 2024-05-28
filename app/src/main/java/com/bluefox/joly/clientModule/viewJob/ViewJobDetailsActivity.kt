package com.bluefox.joly.clientModule.viewJob

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluefox.joly.clientModule.postJob.modalClass.SSSelectedData
import com.bluefox.joly.clientModule.viewJob.modalClass.JobSelected
import com.bluefox.joly.clientModule.viewJob.modalClass.SSSelected
import com.bluefox.joly.databinding.ActivityViewJobDetailsBinding
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class ViewJobDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewJobDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewJobDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDetails()
        onClickListeners()
    }

    private fun onClickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun setDetails() {
        binding.tvJobTitle.text=JobSelected.jobsData.jobName

        loadSLideShowImages()
    }

    private fun loadSLideShowImages()
    {
        val imageListAdapter = ArrayList<SlideModel>() // Create image list

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

        val imagesList = SSSelected.workData.data!!

        for(image in imagesList)
        {
            imageListAdapter.add(SlideModel(image!!.url))
        }

        binding.imageSlider.setImageList(imageListAdapter)
        binding.imageSlider.setImageList(imageListAdapter, ScaleTypes.FIT) // for all images

    }
}