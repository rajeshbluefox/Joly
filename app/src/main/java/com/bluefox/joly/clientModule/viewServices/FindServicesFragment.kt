package com.bluefox.joly.clientModule.viewServices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.viewServices.modelClass.ServiceProviderDetailsData
import com.bluefox.joly.clientModule.viewServices.supportFuncation.ServiceProviderDetailsAdapter
import com.bluefox.joly.databinding.FragmentFindServicesBinding
import com.bluefox.joly.databinding.FragmentPostWorkBinding


class FindServicesFragment : Fragment() {

    private lateinit var binding: FragmentFindServicesBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_services, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initServiceProviderDetails()
    }

    private fun fillDummyList()
    {
        var item1 = ServiceProviderDetailsData()
        item1.companyName = "Company1"

        var item2 = ServiceProviderDetailsData()
        item2.companyName="Company2"

        var item3 = ServiceProviderDetailsData()
        item3.companyName="Company3"

        var item4 = ServiceProviderDetailsData()
        item4.companyName="Company4"

        serviceProviderList.add(item1)
        serviceProviderList.add(item2)
        serviceProviderList.add(item3)
        serviceProviderList.add(item4)





    }

    private var serviceProviderList = ArrayList<ServiceProviderDetailsData>()

    private fun initServiceProviderDetails() {

        fillDummyList()
        val serviceProviderDetailsAdapter = ServiceProviderDetailsAdapter(
            requireContext(),
            serviceProviderList,
            ::onServiceProviderClick
        )

        binding.rvServiceProviders.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = serviceProviderDetailsAdapter
        }

    }

    private fun onServiceProviderClick()
    {

    }


}