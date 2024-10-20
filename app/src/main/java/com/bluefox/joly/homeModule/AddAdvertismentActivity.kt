package com.bluefox.joly.homeModule

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluefox.joly.R
import com.bluefox.joly.databinding.ActivityAddAdvertismentBinding
import com.bluefox.joly.databinding.ActivityAddServiceBinding
import com.bluefox.joly.homeModule.supprotFunctions.AdBackgroundAdapter
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.bluefox.joly.serviceProviderModule.supportFunctions.TestimonyAdapter
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAdvertismentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddAdvertismentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAdvertismentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources, binding.appBarLt)

        addBackgroundItem()
        editTextListeners()
    }

    fun onClickListeners()
    {

    }

    private fun addBackgroundItem()
    {
        val backgroundItem = ArrayList<Int>()
        backgroundItem.add(R.drawable.gradient1)
        backgroundItem.add(R.drawable.gradient2)
        backgroundItem.add(R.drawable.gradient3)

        initBackgroundAdapter(backgroundItem)
    }

    private fun initBackgroundAdapter(backgroundItem: List<Int>) {

        val adBackgroundAdapter = AdBackgroundAdapter(this, backgroundItem,::onBackgroundSelected)
        binding.rvAdBackground.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL, false
            )
            adapter = adBackgroundAdapter
        }
    }

    private fun onBackgroundSelected(backgroundId: Int)
    {
        binding.adPreview.setBackgroundResource(backgroundId)
    }

    fun editTextListeners()
    {
        binding.etBusinessName.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                val inputValue =text.toString()
                if (inputValue.isNotEmpty()) {
                    binding.ptvBusinessName.text=inputValue
                }
            }
        }

        binding.etDescription.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                val inputValue =text.toString()
                if (inputValue.isNotEmpty()) {
                    binding.ptvDescription.text=inputValue
                }
            }
        }
    }
}