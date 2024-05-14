package com.bluefox.joly.clientModule.viewJob.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluefox.joly.clientModule.viewJob.modalClass.CategoryData
import com.bluefox.joly.clientModule.viewServices.modelClass.ServiceProviderDetailsData
import com.bluefox.joly.databinding.ItemServiceCategoriesBinding
import com.bluefox.joly.databinding.ItemServiceProviderDetailsBinding



class ServiceProviderListAdapter(
    private var context: Context,
    private var serviceCategoryList: ArrayList<CategoryData>,
    private val onServiceClicked: () -> Unit,
) :
    RecyclerView.Adapter<ServiceProviderListAdapter.ServiceProviderListAdapterViewHolder>() {


    class ServiceProviderListAdapterViewHolder(var binding: ItemServiceCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceProviderListAdapterViewHolder {
        return ServiceProviderListAdapterViewHolder(
            ItemServiceCategoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ServiceProviderListAdapterViewHolder, position: Int) {
        val item= serviceCategoryList[position]
        holder.binding.tvServiceName.text=item.categoryName




    }

    override fun getItemCount(): Int {
        return serviceCategoryList.size
    }

}