package com.bluefox.joly.clientModule.postJob.supportFunctions

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.bluefox.joly.R


class SubmittedDialog(
    layoutInflater: LayoutInflater,
    context: Context
) {

    private val mLayoutInflater: LayoutInflater
    private val mContext: Context

    private lateinit var dialog: AlertDialog.Builder
    private lateinit var messageBoxInstance: AlertDialog

    private lateinit var anim : LottieAnimationView

    private lateinit var btClose : TextView

    init {
        mLayoutInflater = layoutInflater
        mContext = context

        initViews()
    }

    private fun initViews() {
        val view = mLayoutInflater.inflate(R.layout.item_submitted, null)
        dialog = AlertDialog.Builder(mContext,R.style.CurvedDialog).setView(view)
        dialog.setCancelable(false)
        messageBoxInstance = dialog.create()

        anim = view.findViewById(R.id.submittedAnim)
        btClose=view.findViewById(R.id.btClose)
    }

    fun showLoading()
    {
        anim.setAnimation(R.raw.anim_loading)
        anim.playAnimation()
        anim.repeatCount = LottieDrawable.INFINITE

        messageBoxInstance.show()

        onClickListeners()
    }

    fun showSubmitted()
    {
        anim.setAnimation(R.raw.ic_work_submitted)
        anim.repeatCount=0
        anim.playAnimation()

        btClose.visibility=View.VISIBLE
    }

    private fun onClickListeners()
    {
        btClose.setOnClickListener {
            messageBoxInstance.dismiss()
        }
    }

}