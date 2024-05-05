package com.bluefox.joly.clientModule.postJob

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.postJob.adapters.CategorySpinnerAdapter
import com.bluefox.joly.clientModule.postJob.modalClass.CategoryListItem
import com.bluefox.joly.databinding.FragmentPostWorkBinding


class PostWorkFragment : Fragment() {

    private lateinit var binding: FragmentPostWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_work, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    public fun initACSpinner(
        acList: List<CategoryListItem>,
        onAcSelected: () -> Unit
    ) {


        val newItem = CategoryListItem(
            "Select",
            "-1",
        )

        val newAcList = listOf(newItem) + acList
        val adapter = CategorySpinnerAdapter(requireContext(), android.R.layout.simple_spinner_item, newAcList)

        binding.spSelectCategory.adapter = adapter
        binding.spSelectCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (optional)
            }
        }
    }

}