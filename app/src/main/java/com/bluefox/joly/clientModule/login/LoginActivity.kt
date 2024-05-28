package com.bluefox.joly.clientModule.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluefox.joly.databinding.ActivityLoginBinding
import com.bluefox.joly.zCommonFunctions.CallIntent

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickListeners()
    }

    private fun onClickListeners() {
        binding.showRegister.setOnClickListener {
            CallIntent.gotoRegister(this,true,this)
        }

        binding.btLogin.setOnClickListener {
            CallIntent.gotoHomeActivity(this,true,this)
        }
    }
}