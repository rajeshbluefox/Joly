package com.familylocation.mobiletracker.zCommonFuntions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.bluefox.joly.clientModule.postJob.modalClass.City
import com.bluefox.joly.clientModule.postJob.modalClass.District
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

    fun formatDate2(input: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val outputFormatter = DateTimeFormatter.ofPattern("MMM dd")

        // Parse the input date string to a LocalDateTime
        val dateTime = LocalDateTime.parse(input, inputFormatter)

        // Format the LocalDateTime to the desired output format
        return dateTime.format(outputFormatter)
    }



    fun formatDate3(input: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("MMM dd")

        // Parse the input date string to a LocalDate
        val date = LocalDate.parse(input, inputFormatter)

        // Format the LocalDate to the desired output format
        return date.format(outputFormatter)
    }


    fun getDistricts(): ArrayList<District> {
        val keralaDistricts = arrayListOf(
            District(districtId = 1, districtName = "Thiruvananthapuram"),
            District(districtId = 2, districtName = "Kollam"),
            District(districtId = 3, districtName = "Pathanamthitta"),
            District(districtId = 4, districtName = "Alappuzha"),
            District(districtId = 5, districtName = "Kottayam"),
            District(districtId = 6, districtName = "Idukki"),
            District(districtId = 7, districtName = "Ernakulam"),
            District(districtId = 8, districtName = "Thrissur"),
            District(districtId = 9, districtName = "Palakkad"),
            District(districtId = 10, districtName = "Malappuram"),
            District(districtId = 11, districtName = "Kozhikode"),
            District(districtId = 12, districtName = "Wayanad"),
            District(districtId = 13, districtName = "Kannur"),
            District(districtId = 14, districtName = "Kasaragod")
        )

        return keralaDistricts
    }


    fun getThiruvananthapuramCities(): ArrayList<City> {
        val thiruvananthapuramCities = arrayListOf(
            City(districtId = 1, CityId = 1, CityName = "Athiyannoor"),
            City(districtId = 1, CityId = 2, CityName = "Chirayinkeezhu"),
            City(districtId = 1, CityId = 3, CityName = "Kilimanoor"),
            City(districtId = 1, CityId = 4, CityName = "Nedumangad"),
            City(districtId = 1, CityId = 5, CityName = "Nemom"),
            City(districtId = 1, CityId = 6, CityName = "Parassala"),
            City(districtId = 1, CityId = 7, CityName = "Perumkadavila"),
            City(districtId = 1, CityId = 8, CityName = "Pothencode"),
            City(districtId = 1, CityId = 9, CityName = "Vamanapuram"),
            City(districtId = 1, CityId = 10, CityName = "Varkala"),
            City(districtId = 1, CityId = 11, CityName = "Vellanad")
        )
        return thiruvananthapuramCities
    }

    fun getKollamCities(): ArrayList<City> {
        val kollamCities = arrayListOf(
            City(districtId = 2, CityId = 1, CityName = "Anchal"),
            City(districtId = 2, CityId = 2, CityName = "Chadayamangalam"),
            City(districtId = 2, CityId = 3, CityName = "Chavara"),
            City(districtId = 2, CityId = 4, CityName = "Chittumala"),
            City(districtId = 2, CityId = 5, CityName = "Ithikkara"),
            City(districtId = 2, CityId = 6, CityName = "Kottarakkara"),
            City(districtId = 2, CityId = 7, CityName = "Mukhathala"),
            City(districtId = 2, CityId = 8, CityName = "Oachira"),
            City(districtId = 2, CityId = 9, CityName = "Pathanapuram"),
            City(districtId = 2, CityId = 10, CityName = "Sasthamcottah"),
            City(districtId = 2, CityId = 11, CityName = "Vettikkavala")
        )
        return kollamCities
    }

    fun getPathanamthittaCities(): ArrayList<City> {
        val pathanamthittaCities = arrayListOf(
            City(districtId = 3, CityId = 1, CityName = "Elanthoor"),
            City(districtId = 3, CityId = 2, CityName = "Koipuram"),
            City(districtId = 3, CityId = 3, CityName = "Konni"),
            City(districtId = 3, CityId = 4, CityName = "Mallappally"),
            City(districtId = 3, CityId = 5, CityName = "Pandlam"),
            City(districtId = 3, CityId = 6, CityName = "Parakode"),
            City(districtId = 3, CityId = 7, CityName = "Pulikeezhu"),
            City(districtId = 3, CityId = 8, CityName = "Ranni")
        )
        return pathanamthittaCities
    }

    fun getAlappuzhaCities(): ArrayList<City> {
        val alappuzhaCities = arrayListOf(
            City(districtId = 4, CityId = 1, CityName = "Ambalappuzha"),
            City(districtId = 4, CityId = 2, CityName = "Aryad"),
            City(districtId = 4, CityId = 3, CityName = "Bharanicavu"),
            City(districtId = 4, CityId = 4, CityName = "Champakulam"),
            City(districtId = 4, CityId = 5, CityName = "Chengannur"),
            City(districtId = 4, CityId = 6, CityName = "Harippad"),
            City(districtId = 4, CityId = 7, CityName = "Kanjikkuzhy"),
            City(districtId = 4, CityId = 8, CityName = "Mavelikkara"),
            City(districtId = 4, CityId = 9, CityName = "Muthukulam"),
            City(districtId = 4, CityId = 10, CityName = "Pattanakkad"),
            City(districtId = 4, CityId = 11, CityName = "Thycattussery"),
            City(districtId = 4, CityId = 12, CityName = "Veliyanad")
        )
        return alappuzhaCities
    }

    fun getKottayamCities(): ArrayList<City> {
        val kottayamCities = arrayListOf(
            City(districtId = 5, CityId = 1, CityName = "Erattupetta"),
            City(districtId = 5, CityId = 2, CityName = "Ettumanoor"),
            City(districtId = 5, CityId = 3, CityName = "Kaduthuruthy"),
            City(districtId = 5, CityId = 4, CityName = "Kanjirappally"),
            City(districtId = 5, CityId = 5, CityName = "Lalam"),
            City(districtId = 5, CityId = 6, CityName = "Madappally"),
            City(districtId = 5, CityId = 7, CityName = "Pallom"),
            City(districtId = 5, CityId = 8, CityName = "Pampady"),
            City(districtId = 5, CityId = 9, CityName = "Uzhavoor"),
            City(districtId = 5, CityId = 10, CityName = "Vaikom"),
            City(districtId = 5, CityId = 11, CityName = "Vazhoor")
        )
        return kottayamCities
    }


    fun getIdukkiCities(): ArrayList<City> {
        val idukkiCities = arrayListOf(
            City(districtId = 6, CityId = 1, CityName = "Adimaly"),
            City(districtId = 6, CityId = 2, CityName = "Azhutha"),
            City(districtId = 6, CityId = 3, CityName = "Devikulam"),
            City(districtId = 6, CityId = 4, CityName = "Elemdesam"),
            City(districtId = 6, CityId = 5, CityName = "Idukki"),
            City(districtId = 6, CityId = 6, CityName = "Kattappana"),
            City(districtId = 6, CityId = 7, CityName = "Nedumkandom"),
            City(districtId = 6, CityId = 8, CityName = "Thodupuzha")
        )
        return idukkiCities
    }


    fun getErnakulamCities(): ArrayList<City> {
        val ernakulamCities = arrayListOf(
            City(districtId = 7, CityId = 1, CityName = "Alangad"),
            City(districtId = 7, CityId = 2, CityName = "Angamali"),
            City(districtId = 7, CityId = 3, CityName = "Edappally"),
            City(districtId = 7, CityId = 4, CityName = "Koovappady"),
            City(districtId = 7, CityId = 5, CityName = "Kothamangalam"),
            City(districtId = 7, CityId = 6, CityName = "Mulanthuruthy"),
            City(districtId = 7, CityId = 7, CityName = "Muvattupuzha"),
            City(districtId = 7, CityId = 8, CityName = "Palluruthy"),
            City(districtId = 7, CityId = 9, CityName = "Pampakuda"),
            City(districtId = 7, CityId = 10, CityName = "Parakkadav"),
            City(districtId = 7, CityId = 11, CityName = "Paravur"),
            City(districtId = 7, CityId = 12, CityName = "Vadavucode"),
            City(districtId = 7, CityId = 13, CityName = "Vazhakkulam"),
            City(districtId = 7, CityId = 14, CityName = "Vypeen")
        )
        return ernakulamCities
    }

    fun getThrissurCities(): ArrayList<City> {
        val thrissurCities = arrayListOf(
            City(districtId = 8, CityId = 1, CityName = "Anthikkad"),
            City(districtId = 8, CityId = 2, CityName = "Chalakkudy"),
            City(districtId = 8, CityId = 3, CityName = "Chavakkad"),
            City(districtId = 8, CityId = 4, CityName = "Cherpu"),
            City(districtId = 8, CityId = 5, CityName = "Chowannur"),
            City(districtId = 8, CityId = 6, CityName = "Irinjalakkuda"),
            City(districtId = 8, CityId = 7, CityName = "Kodakara"),
            City(districtId = 8, CityId = 8, CityName = "Mala"),
            City(districtId = 8, CityId = 9, CityName = "Mathilakam"),
            City(districtId = 8, CityId = 10, CityName = "Mullassery"),
            City(districtId = 8, CityId = 11, CityName = "Ollukkara"),
            City(districtId = 8, CityId = 12, CityName = "Pazhayannur"),
            City(districtId = 8, CityId = 13, CityName = "Puzhakkal"),
            City(districtId = 8, CityId = 14, CityName = "Thalikkulam"),
            City(districtId = 8, CityId = 15, CityName = "Vellangallur"),
            City(districtId = 8, CityId = 16, CityName = "Wadakkanchery")
        )
        return thrissurCities
    }

    fun getPalakkadCities(): ArrayList<City> {
        val palakkadCities = arrayListOf(
            City(districtId = 9, CityId = 1, CityName = "Alathur"),
            City(districtId = 9, CityId = 2, CityName = "Attappadi"),
            City(districtId = 9, CityId = 3, CityName = "Chittur"),
            City(districtId = 9, CityId = 4, CityName = "Kollengode"),
            City(districtId = 9, CityId = 5, CityName = "Kuzhalmannam"),
            City(districtId = 9, CityId = 6, CityName = "Malampuzha"),
            City(districtId = 9, CityId = 7, CityName = "Mannarkad"),
            City(districtId = 9, CityId = 8, CityName = "Nemmara"),
            City(districtId = 9, CityId = 9, CityName = "Ottappalam"),
            City(districtId = 9, CityId = 10, CityName = "Palakkad"),
            City(districtId = 9, CityId = 11, CityName = "Pattambi"),
            City(districtId = 9, CityId = 12, CityName = "Sreekrishnapuram"),
            City(districtId = 9, CityId = 13, CityName = "Trithala")
        )
        return palakkadCities
    }

    fun getMalappuramCities(): ArrayList<City> {
        val malappuramCities = arrayListOf(
            City(districtId = 10, CityId = 1, CityName = "Areakode"),
            City(districtId = 10, CityId = 2, CityName = "Kalikavu"),
            City(districtId = 10, CityId = 3, CityName = "Kondotty"),
            City(districtId = 10, CityId = 4, CityName = "Kuttippuram"),
            City(districtId = 10, CityId = 5, CityName = "Malappuram"),
            City(districtId = 10, CityId = 6, CityName = "Mankada"),
            City(districtId = 10, CityId = 7, CityName = "Nilambur"),
            City(districtId = 10, CityId = 8, CityName = "Perinthalmanna"),
            City(districtId = 10, CityId = 9, CityName = "Perumpadappu"),
            City(districtId = 10, CityId = 10, CityName = "Ponnani"),
            City(districtId = 10, CityId = 11, CityName = "Tanur"),
            City(districtId = 10, CityId = 12, CityName = "Tirur"),
            City(districtId = 10, CityId = 13, CityName = "Tirurangadi"),
            City(districtId = 10, CityId = 14, CityName = "Vengara"),
            City(districtId = 10, CityId = 15, CityName = "Wandoor")
        )
        return malappuramCities
    }

    fun getKozhikodeCities(): ArrayList<City> {
        val kozhikodeCities = arrayListOf(
            City(districtId = 11, CityId = 1, CityName = "Balusseri"),
            City(districtId = 11, CityId = 2, CityName = "Chelannur"),
            City(districtId = 11, CityId = 3, CityName = "Koduvally"),
            City(districtId = 11, CityId = 4, CityName = "Kozhikode"),
            City(districtId = 11, CityId = 5, CityName = "Kunnamangalam"),
            City(districtId = 11, CityId = 6, CityName = "Kunnummal"),
            City(districtId = 11, CityId = 7, CityName = "Melday"),
            City(districtId = 11, CityId = 8, CityName = "Panthalayani"),
            City(districtId = 11, CityId = 9, CityName = "Perambra"),
            City(districtId = 11, CityId = 10, CityName = "Thodannur"),
            City(districtId = 11, CityId = 11, CityName = "Thuneri"),
            City(districtId = 11, CityId = 12, CityName = "Vadakara")
        )
        return kozhikodeCities
    }

    fun getWayanadCities(): ArrayList<City> {
        val wayanadCities = arrayListOf(
            City(districtId = 12, CityId = 1, CityName = "Kalpetta"),
            City(districtId = 12, CityId = 2, CityName = "Mananthavady"),
            City(districtId = 12, CityId = 3, CityName = "Panamaram"),
            City(districtId = 12, CityId = 4, CityName = "Sulthan Bathery")
        )
        return wayanadCities
    }


    fun getKannurCities(): ArrayList<City> {
        val kannurCities = arrayListOf(
            City(districtId = 13, CityId = 1, CityName = "Edakkad"),
            City(districtId = 13, CityId = 2, CityName = "Irikkur"),
            City(districtId = 13, CityId = 3, CityName = "Iritty"),
            City(districtId = 13, CityId = 4, CityName = "Kalliasseri"),
            City(districtId = 13, CityId = 5, CityName = "Kannur"),
            City(districtId = 13, CityId = 6, CityName = "Kuthuparamba"),
            City(districtId = 13, CityId = 7, CityName = "Panoor"),
            City(districtId = 13, CityId = 8, CityName = "Payyannur"),
            City(districtId = 13, CityId = 9, CityName = "Peravoor"),
            City(districtId = 13, CityId = 10, CityName = "Taliparamba"),
            City(districtId = 13, CityId = 11, CityName = "Thalassery")
        )
        return kannurCities
    }

    fun getKasaragodCities(): ArrayList<City> {
        val kasaragodCities = arrayListOf(
            City(districtId = 14, CityId = 1, CityName = "Kanhangad"),
            City(districtId = 14, CityId = 2, CityName = "Karadka"),
            City(districtId = 14, CityId = 3, CityName = "Kasargod"),
            City(districtId = 14, CityId = 4, CityName = "Manjeshwar"),
            City(districtId = 14, CityId = 5, CityName = "Nileshwar"),
            City(districtId = 14, CityId = 6, CityName = "Parappa")
        )
        return kasaragodCities
    }


}