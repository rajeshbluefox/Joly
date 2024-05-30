package com.bluefox.joly.clientModule.login

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bluefox.joly.clientModule.login.apiFunctions.LoginAPIFunctions
import com.bluefox.joly.clientModule.login.apiFunctions.LoginViewModel
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.clientModule.login.supportFunctions.RegisterActivityUI
import com.bluefox.joly.databinding.ActivityRegisterBinding
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bluefox.joly.zSharedPreference.UserDetails
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var registerActivityUI: RegisterActivityUI

    private lateinit var loginViewModel : LoginViewModel

    private lateinit var loginAPIFunctions: LoginAPIFunctions



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources,binding.appBarLt)


        initViews()
        control()
        onClickListeners()
    }

    private fun control()
    {
        when(UserDetails.getUserRoleStatus(this))
        {
            1 -> {
                binding.tvAppBarTitle.text="Service Seeker"
                binding.ltServiceProvider.visibility = View.GONE
            }
            2 -> {
                binding.tvAppBarTitle.text="Service Provider"
                binding.ltServiceProvider.visibility = View.VISIBLE
            }
        }

    }

    private fun initViews() {
        registerActivityUI = RegisterActivityUI(this, binding,::onSubmitClicked)
        loginAPIFunctions = LoginAPIFunctions(this,this,loginViewModel, onLoginResponse = {},::onRegisterResponse )

    }

    private fun onClickListeners() {
        binding.showSignIn.setOnClickListener {
            CallIntent.gotoLogin(this, true, this)
        }

        binding.etSelectDate.setOnClickListener {
            initDatePicker()
        }
    }

    private val cal = Calendar.getInstance()
    private var selectedDate = ""

    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)



            val myFormat = "dd/MM/yyyy" // mention the format you need
            val myFormat1 = "yyyy-MM-dd"
            val sdfCalc = SimpleDateFormat(myFormat1, Locale.US)

            val selectedDateForCalculation = sdfCalc.format(cal.time)

            val age = calculateAge(selectedDateForCalculation, myFormat1)


            binding.etDOB.setText(age.toString())

            binding.etSelectDate.text = "${selectedDateForCalculation} , Age - $age"
            selectedDate = selectedDateForCalculation
//            createVendorUIFunctions.setDateSelected(true)

        }

    fun calculateAge(dob: String, format: String): Int {
        val formatter = DateTimeFormatter.ofPattern(format, Locale.US)
        val birthDate = LocalDate.parse(dob, formatter)
        val currentDate = LocalDate.now()
        return Period.between(birthDate, currentDate).years
    }

    private fun initDatePicker() {

//        val minimumDate = convertStringToDate(minimumDateString)

        val calendar = Calendar.getInstance()
//        calendar.time = minimumDate

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePic = DatePickerDialog(
            this, dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )


//        datePic.datePicker.minDate = minimumDate.time
        datePic.show()


    }


    private fun onSubmitClicked(sSRegistrationDetailsData : SSRegistrationDetailsData)
    {
        Log.e("test","Name ${sSRegistrationDetailsData.name}")
    }

    private fun onRegisterResponse()
    {
        //Code after Login response is received
        // SAVE THE USER details to Shared Preference
        CallIntent.gotoLogin(this, true,this)

    }
}