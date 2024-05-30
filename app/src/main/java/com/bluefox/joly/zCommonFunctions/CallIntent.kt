package com.bluefox.joly.zCommonFunctions

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.bluefox.joly.clientModule.HomeActivity
import com.bluefox.joly.clientModule.login.LoginActivity
import com.bluefox.joly.clientModule.login.RegisterActivity
import com.bluefox.joly.clientModule.profile.ProfileImageActivity
import com.bluefox.joly.clientModule.viewJob.ViewWorkDetailsActivity
import com.bluefox.joly.clientModule.viewJob.ViewServiceProviderDetailsActivity
import com.bluefox.joly.homeModule.NavigationActivity

object CallIntent {

    fun gotoViewJobDetails(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, ViewWorkDetailsActivity::class.java)
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

    fun gotoNavigationActivity(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, NavigationActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }
    fun gotoViewServiceProviderActivity(context: Context, killMe: Boolean, activity: Activity)
    {
        val intent = Intent(context, ViewServiceProviderDetailsActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun gotoProfileImageActivity(context: Context, killMe: Boolean, activity: Activity)
    {
        val intent = Intent(context, ProfileImageActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }
}