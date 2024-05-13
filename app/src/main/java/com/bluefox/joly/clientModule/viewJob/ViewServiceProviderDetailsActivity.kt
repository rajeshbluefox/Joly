package com.bluefox.joly.clientModule.viewJob

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bluefox.joly.R
import com.bluefox.joly.databinding.ActivityViewJobDetailsBinding
import com.bluefox.joly.databinding.ActivityViewServiceProviderDetailsBinding
import com.bluefox.joly.zCommonFunctions.StatusBarUtils

class ViewServiceProviderDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewServiceProviderDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewServiceProviderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBar(this)
        StatusBarUtils.setTopPadding(resources,binding.appBarLt)
    }
}