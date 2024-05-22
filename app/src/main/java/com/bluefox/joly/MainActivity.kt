package com.bluefox.joly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.databinding.ActivityMainBinding
import com.bluefox.joly.dummy.CallThemesViewModel
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zSharedPreference.UserDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var callThemesViewModel: CallThemesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callThemesViewModel = ViewModelProvider(this)[CallThemesViewModel::class.java]


        getUserLoginStatus()

//        getThemes()
//        observers()

    }

    private fun getUserLoginStatus()
    {
        Handler(Looper.getMainLooper()).postDelayed({
               if(UserDetails.getLoginStatus(this))
               {
                   SSProfileData.openFrom=0
                   CallIntent.gotoHomeActivity(this,true,this)
               }else{
                   CallIntent.gotoLogin(this,true,this)
               }
        }, 2000)
    }

    private fun observers() {
        callThemesViewModel.getThemesResponse().observe(this)
        {
            if(it.status==200)
            {
                Log.e("Test",it.data.toString())
            }else{
                Log.e("Test","No Response")
            }
        }
    }

    private fun getThemes() {
        callThemesViewModel.getThemes()
    }
}