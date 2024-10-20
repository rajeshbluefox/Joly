package com.bluefox.joly.clientModule.viewServices

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.viewServices.modelClass.SelSPData
import com.bluefox.joly.databinding.ActivityViewSpdetailsBinding
import com.bluefox.joly.databinding.ActivityWebPageBinding
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources,binding.appBarLt)

        openWebsite()
        onClickListeners()
    }

    private fun onClickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun openWebsite()
    {
        binding.webView.settings.javaScriptEnabled = true
        // Make sure URLs open inside the WebView and not in an external browser
        binding.webView.webViewClient = WebViewClient()
        val url = SelSPData.serviceProviderDetailsData.portfolioLink
        binding.webView.loadUrl(url!!)

    }
}