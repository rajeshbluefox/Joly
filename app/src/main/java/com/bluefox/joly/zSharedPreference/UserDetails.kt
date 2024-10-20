package com.bluefox.joly.zSharedPreference

import android.content.Context
import com.bluefox.joly.zConstants.Constants

object UserDetails {

    private const val PREFS_NAME = "JolyApp"

    fun saveUserRoleStatus(context: Context, value: Int) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(Constants.USER_ROLE, value).apply()
    }

    fun getUserRoleStatus(context: Context): Int {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getInt(Constants.USER_ROLE, 0)
    }

    fun saveLoginStatus(context: Context, value: Boolean) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(Constants.LOGIN_STATUS, value).apply()
    }

    fun getLoginStatus(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(Constants.LOGIN_STATUS, false)
    }

    fun saveUserId(context: Context, value: Int) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(Constants.USER_ID, value).apply()
    }

    fun getUserId(context: Context): Int {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getInt(Constants.USER_ID, 0)
    }

    fun saveUserMobileNo(context: Context, value: String) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(Constants.USER_MOBILE_NO, value).apply()
    }

    fun getUserMobileNo(context: Context): String {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(Constants.USER_MOBILE_NO, "EMPTY")?:"EMPTY"
    }

    fun saveUserPassword(context: Context, value: String) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(Constants.USER_PASSWORD, value).apply()
    }

    fun getUserPassword(context: Context): String {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(Constants.USER_PASSWORD, "EMPTY")?:"EMPTY"
    }

}