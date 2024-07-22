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
import com.bluefox.joly.jobModule.JobHomeActivity
import com.bluefox.joly.jobModule.jobProviderModule.ApplicantProfileActivity
import com.bluefox.joly.jobModule.jobProviderModule.ViewApplicationsActivity
import com.bluefox.joly.jobModule.jobProviderModule.ViewPostedJobActivity
import com.bluefox.joly.jobModule.jobSeekerModule.viewJobs.ViewJobsJSActivity
import com.bluefox.joly.serviceProviderModule.AddServiceActivity
import com.bluefox.joly.serviceProviderModule.ShowTestimoniesActivity
import com.bluefox.joly.serviceProviderModule.TestimonialsActivity

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

    fun gotoJobHomeActivity(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, JobHomeActivity::class.java)
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

    fun gotoAddServiceActivity(context: Context, killMe: Boolean, activity: Activity)
    {
        val intent = Intent(context, AddServiceActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun gotoTestimonialsActivity(context: Context, killMe: Boolean, activity: Activity)
    {
        val intent = Intent(context, TestimonialsActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun gotoShowTestimonialsActivity(context: Context, killMe: Boolean, activity: Activity)
    {
        val intent = Intent(context, ShowTestimoniesActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }
    fun gotoViewPostedJobActivity(context: Context,killMe: Boolean, activity: Activity)
    {
        val intent = Intent(context, ViewPostedJobActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun gotoJSViewJobActivity(context: Context,killMe: Boolean, activity: Activity)
    {
        val intent = Intent(context, ViewJobsJSActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun gotoViewApplicationsActivity(context: Context,killMe: Boolean, activity: Activity)
    {
        val intent = Intent(context, ViewApplicationsActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun gotoApplicantProfileActivity(context: Context,killMe: Boolean, activity: Activity)
    {
        val intent = Intent(context, ApplicantProfileActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }


}