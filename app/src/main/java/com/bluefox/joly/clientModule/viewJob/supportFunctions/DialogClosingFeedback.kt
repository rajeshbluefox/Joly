package com.bluefox.joly.clientModule.viewJob.supportFunctions

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.bluefox.joly.databinding.DialogClosingFeedbackBinding
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions

class DialogClosingFeedback(
    layoutInflater: LayoutInflater,
    context: Context,
    private val onSubmitClicked: (closingFeedback: Int) -> Unit

) {
    private val mLayoutInflater: LayoutInflater
    private val mContext: Context

    private var binding: DialogClosingFeedbackBinding
    private var dialog: AlertDialog.Builder
    private var messageBoxInstance: AlertDialog


    init {
        mLayoutInflater = layoutInflater
        mContext = context
        binding = DialogClosingFeedbackBinding.inflate(mLayoutInflater)
        dialog = AlertDialog.Builder(mContext).setView(binding.root)
        dialog.setCancelable(true)
        messageBoxInstance = dialog.create()

        onClickListener()
        onRadioGroupListener()
    }

    fun showClosingFeedback()
    {
        messageBoxInstance.show()
    }

    private fun onClickListener() {
        binding.btCloseNow.setOnClickListener {

            if(closingFeedback==0)
            {
                UtilFunctions.showToast(mContext,"Please give closing feedback")
            }
            else{
                onSubmitClicked(closingFeedback)
            }
        }
    }

    private var closingFeedback = 0

    private fun onRadioGroupListener() {
        binding.rgSelect.setOnCheckedChangeListener { _, i ->
            when (i) {
                binding.rbSpApp.id -> {
                    closingFeedback = 1
                }

                binding.rbNotCompleted.id -> {
                    closingFeedback = 2
                }

                binding.rbSpPother.id -> {
                    closingFeedback = 3
                }
            }
        }
    }

}