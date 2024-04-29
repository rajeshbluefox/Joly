package com.bluefox.joly.clientModule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluefox.joly.databinding.ActivityRegisterBinding
import com.bluefox.joly.zCommonFunctions.CallIntent

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickListeners()
    }

    private fun onClickListeners() {
        binding.showSignIn.setOnClickListener {
            CallIntent.gotoLogin(this, true, this)
        }
    }
}