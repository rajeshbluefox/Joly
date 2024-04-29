package com.bluefox.joly.zCommonFunctions

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.bluefox.joly.clientModule.HomeActivity
import com.bluefox.joly.clientModule.LoginActivity
import com.bluefox.joly.clientModule.RegisterActivity
import com.bluefox.joly.clientModule.viewJob.ViewJobDetailsActivity

object CallIntent {

    fun gotoViewJobDetails(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, ViewJobDetailsActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun gotoLogin(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun gotoRegister(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, RegisterActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun gotoHomeActivity(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }
}