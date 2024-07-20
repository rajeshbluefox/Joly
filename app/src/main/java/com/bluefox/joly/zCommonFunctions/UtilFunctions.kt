package com.familylocation.mobiletracker.zCommonFuntions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

object UtilFunctions {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showToastLong(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }

    fun getCurrentDateTimeSeconds(): String {

        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }

    fun setStatusBarIconColor(window: Window, isLight: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor = window.decorView
            if (isLight) {
                // For light status bar icons
                decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                // For dark status bar icons
                decor.systemUiVisibility = 0
            }
        }
    }

    fun callNumber(context: Context, phoneNumber: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phoneNumber") // Replace with the actual phone number
        context.startActivity(callIntent)
    }

    fun normalizePhoneNumber(phoneNumber: String): String {
        // Remove non-numeric characters from the phone number
        return phoneNumber.replace("\\s".toRegex(), "")
    }

    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun makePhoneNumber10(phoneNumber: String): String {
        var normalizedNumber = phoneNumber
        // Remove any non-numeric characters
        normalizedNumber = normalizedNumber.replace("[^0-9]".toRegex(), "")

        // If the length is greater than 10, remove leading characters to make it 10
        if (normalizedNumber.length > 10) {
            normalizedNumber = normalizedNumber.substring(normalizedNumber.length - 10)
        }
        return normalizedNumber
    }

    fun formatDate(input: String): String {
        //"2024-05-29 16:19:37" to May 29 converter

        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val outputFormatter = DateTimeFormatter.ofPattern("MMMM dd")

        // Parse the input date string to a LocalDateTime
        val dateTime = LocalDateTime.parse(input, inputFormatter)

        // Format the LocalDateTime to the desired output format
        return dateTime.format(outputFormatter)
    }

    fun formatDate2(dateString: String): String {
        val dateFormatter = DateTimeFormatter.ofPattern("MMM dd")

        val date = LocalDate.parse(dateString)
        return  date.format(dateFormatter)
    }
}