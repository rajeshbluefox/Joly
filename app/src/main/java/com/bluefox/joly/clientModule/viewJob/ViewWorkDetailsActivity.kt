package com.bluefox.joly.clientModule.viewJob

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.arthenica.mobileffmpeg.FFmpeg
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSViewModel
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSapiFunctions
import com.bluefox.joly.clientModule.postJob.modalClass.ServicesCatJob
import com.bluefox.joly.clientModule.viewJob.modalClass.SSSelected
import com.bluefox.joly.clientModule.viewJob.supportFunctions.DialogClosingFeedback
import com.bluefox.joly.databinding.ActivityViewJobDetailsBinding
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


@AndroidEntryPoint
class ViewWorkDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewJobDetailsBinding

    private lateinit var sSapiFunctions: SSapiFunctions
    private lateinit var ssViewModel: SSViewModel

    private lateinit var dialogClosingFeedback: DialogClosingFeedback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewJobDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources, binding.appBarLt)

        initViews()
        setDetails()
        onClickListeners()
    }

    private fun initViews() {
        dialogClosingFeedback= DialogClosingFeedback(layoutInflater,this,::onClosingFeedbackGiven)
        ssViewModel = ViewModelProvider(this)[SSViewModel::class.java]

        sSapiFunctions = SSapiFunctions(
            this,
            this,
            ssViewModel,
            onCategoriesResponse = {},
            onJobsResponse = {},
            onWorkSubmitted = {},
            onGetWorksResponse = {},
            ::onServiceClosed
        )

    }

    private fun onClickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btCloseService.setOnClickListener {
            dialogClosingFeedback.showClosingFeedback()
//            sSapiFunctions.getSSCloseWork(SSSelected.workData.workId.toString())

        }

        binding.btServiceProviders.setOnClickListener {
            CallIntent.gotoViewServiceProvidersActivity(this, false, this)
        }

        binding.btPlay.setOnClickListener {
//            playRecording()
//            playAudio(outputFilePath)
            downloadandPlay()
        }
    }

    private fun onClosingFeedbackGiven(closingFeedback: Int)
    {
        sSapiFunctions.getSSCloseWork(SSSelected.workData.workId.toString(),closingFeedback)
    }

    private fun setDetails() {
//        binding.tvJobTitle.text=JobSelected.jobsData.jobName
        loadSLideShowImages()

        Glide.with(this)
            .load(SSSelected.workData.profilePhoto)
            .fitCenter()
            .into(binding.profileImage)

        binding.tvWorkName.text = SSSelected.workData.workName

        binding.tvWageOfferedValue.text = "₹ ${SSSelected.workData.wageOffered}"
//        binding.tvJobLocation.text=SSSelected.workData.areaId.toString()

        if (SSSelected.workData.workDescription == "E")
            binding.tvDescription.visibility = View.GONE
        else
            binding.tvDescription.text = SSSelected.workData.workDescription

        //Set Category
        val categoryName =
            ServicesCatJob.categoriesList.find { it.categoryID == SSSelected.workData.categoryId }
        binding.tvCategoryValue.text = categoryName?.categoryName

        //Set Job
        val jobItem = ServicesCatJob.jobList.find { it.jobId == SSSelected.workData.jobId }
        binding.tvJobTypeValue.text = jobItem?.jobName

        binding.tvDateValue.text = UtilFunctions.formatDate(SSSelected.workData.workPostedDate!!)
        binding.tvDeadLineTimeValue.text = SSSelected.workData.deadlineTime

        binding.tvJobLocation.text = "${SSSelected.workData.city}, ${SSSelected.workData.district}"

        //Audio Description
        val audioUrl = SSSelected.workData.audioDescription

        if (audioUrl == "E") {
            binding.audioLt.visibility = View.GONE
        }

        if (SSProfileData.UserRole == 1) {
            hideClientDetailsLt()

            binding.tvWhatNext.visibility = View.GONE
            binding.tvWhatNextValue.visibility = View.GONE
        } else {
            binding.btCloseService.visibility = View.GONE

            binding.btServiceProviders.visibility = View.GONE

            binding.tvDeadLineTime.text = "Complete In :"
            binding.tvClientName.text = SSSelected.workData.name

            if(SSSelected.workData.alternativeNumber=="E")
            {
                binding.tvClientNumber.text = SSSelected.workData.phoneNumber
            }else{
                binding.tvClientNumber.text = "${SSSelected.workData.phoneNumber}, ${SSSelected.workData.alternativeNumber}"
            }
        }

        if (SSSelected.workData.status == 0) {
            binding.btCloseService.text = "Closed"
            binding.btCloseService.isEnabled = false
            binding.btCloseService.setBackgroundResource(R.drawable.button_diable)

            if(SSSelected.workData.ratingAdded==0&&SSSelected.workData.closingFeedback==1)
            {
                binding.suggestSP.visibility=View.VISIBLE
//                StatusBarUtils.setTopMargin(resources,binding.btServiceProviders)
            }
        }

//        outputFilePath = SSSelected.workData.audioDescription!!
//        if (outputFilePath.isNotEmpty())
//            setTheTrack()
    }

    private fun hideClientDetailsLt() {
        binding.clientDetaiilsLt.visibility = View.GONE
    }

    private fun loadSLideShowImages() {
        val imageListAdapter = ArrayList<SlideModel>() // Create image list

        val imagesList = SSSelected.workData.data!!

        for (image in imagesList) {
            imageListAdapter.add(SlideModel(image!!.url))
        }

        binding.imageSlider.setImageList(imageListAdapter)
        binding.imageSlider.setImageList(imageListAdapter, ScaleTypes.FIT) // for all images

    }

    private fun onServiceClosed() {
        finish()
    }

    //Play Audio

    private var mediaPlayer: MediaPlayer? = null
    private var outputFilePath = ""

    private var handler = Handler()
    private val updateWaveformProgress = object : Runnable {
        override fun run() {
            mediaPlayer?.let {

                // Get current position of the audio
                val currentPosition = mediaPlayer?.currentPosition ?: 0
                val percentage = (currentPosition.toFloat() / it.duration.toFloat()) * 100
                Log.e(
                    "Test",
                    "Progress : ${currentPosition.toFloat()} /${it.duration} = $percentage"
                )
                binding.audioTrackIndicator.progress = percentage
                // Continue updating every 100ms
                handler.postDelayed(this, 100)
            }
        }
    }

    private fun setTheTrack() {
        binding.audioLt.visibility = View.VISIBLE
        binding.audioTrackIndicator.setSampleFrom(outputFilePath) // Load waveform

        binding.btPlay.setOnClickListener { playRecording() }

    }

    private var isPaused = false
    private var currentPosition = 0

    private fun playRecording() {
        Log.e("Test", "play clicked")

        if (isPaused) {
            Log.e("Test", "Playing from Pause")

            // Resume playback from where it was paused
            mediaPlayer?.seekTo(currentPosition)
            mediaPlayer?.start()

            binding.btPlay.setImageResource(R.drawable.baseline_pause_circle_36)
            handler.post(updateWaveformProgress) // Continue updating waveform
            isPaused = false
        } else {
            Log.e("Test", "Playing audio")
            // Release the MediaPlayer if it's active to prevent conflicts
            mediaPlayer?.release()
            mediaPlayer = null

            // Start playing from the beginning
            mediaPlayer = MediaPlayer().apply {
                setDataSource(outputFilePath)
                prepare()
                start()

                binding.btPlay.setImageResource(R.drawable.baseline_pause_circle_36)

                // Start updating the waveform progress
                handler.post(updateWaveformProgress)

                setOnCompletionListener {
                    // Reset the button text and progress when the playback is completed
                    binding.btPlay.setImageResource(R.drawable.baseline_play_circle_filled_36)
//                    handler.removeCallbacks(updateWaveformProgress)
                    binding.audioTrackIndicator.progress = 0F
                    isPaused = false
                }
            }

            binding.btPlay.setOnClickListener { pauseRecording() }

        }

    }

    private fun pauseRecording() {
        mediaPlayer?.pause()
        currentPosition = mediaPlayer?.currentPosition ?: 0 // Save the current position
        isPaused = true

        Log.e("Test", "Recording Paused")
        binding.btPlay.setImageResource(R.drawable.baseline_play_circle_filled_36)
        binding.btPlay.setOnClickListener { playRecording() }
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
    }

    private fun downloadandPlay() {
        checkAndRequestPermissions()
    }


    // Release the MediaPlayer resources
    private fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun downloadAndPlayAudioNew(url: String) {
        val fileName = url.substring(url.lastIndexOf("/") + 1)
//        val fileName = "SampleAudio2.mp3"
        val filePath =
            "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}/$fileName"
        val file = File(filePath)

        Log.e("Test", "Download called")

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                // Handle the error
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                response.body?.let { responseBody ->
                    val inputStream = responseBody.byteStream()
                    val file = File(filePath)
                    val outputStream = FileOutputStream(file)

                    try {
                        inputStream.copyTo(outputStream)
                        // Play the audio after download
                        Log.e("Test", "URL - $url")
                        Log.e("Test", "FilePath - $filePath")
                        Log.e("Test", "FileAbsloute - ${file.absolutePath}")
                        Log.e("Test", "Downloaded")
//                        UtilFunctions.showToast(applicationContext,"Downloaded")
                        playAudioNew(file.absolutePath)
                    } catch (e: Exception) {
                        Log.e("Test", e.message.toString())
                        outputStream.close()
                        inputStream.close()
                    }
                }
            }
        })
    }

    fun playAudioNew(filePath: String) {
        outputFilePath = filePath
        setTheTrack()

        playRecording()

//        val mediaPlayer = MediaPlayer()
//        mediaPlayer.setDataSource(filePath)
//        mediaPlayer.prepare() // Prepare the media player
//        mediaPlayer.start() // Start playing
//
//        Log.e("Test", "Trying to play")
//
//        mediaPlayer.setOnCompletionListener {
//            mediaPlayer.release() // Release the resources after completion
//        }
    }

    private val PERMISSION_REQUEST_CODE = 101

    private fun checkAndRequestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (permissions.any {
                ContextCompat.checkSelfPermission(
                    this,
                    it
                ) != PackageManager.PERMISSION_GRANTED
            }) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE)
        } else {
            Log.e("Test", "Permission already granted")

            val url =
                "http://joly.bluefoxsystems.biz/Joly/uploads/audioFiles/file_example_MP3_700KB.mp3"
            downloadAndPlayAudioNew(SSSelected.workData.audioDescription!!)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // All permissions granted, proceed with your actions
                Log.e("Test", "Permission Granted")
                val url =
                    "http://joly.bluefoxsystems.biz/Joly/uploads/audioFiles/file_example_MP3_700KB.mp3"
                downloadAndPlayAudioNew(SSSelected.workData.audioDescription!!)
            } else {
                // Permission denied, handle appropriately
                Log.e("Test", "Permission Denied")

            }
        }
    }


}