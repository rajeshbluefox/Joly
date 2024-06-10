package com.bluefox.joly.serviceProviderModule.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluefox.joly.clientModule.postJob.modalClass.ServicesCatJob
import com.bluefox.joly.databinding.ItemServicesOfferedBinding
import com.bluefox.joly.serviceProviderModule.modelClass.ServicesOfferedData


class ServicesOfferedAdapter(
    private var context: Context,
    private var servicesOfferedList: List<ServicesOfferedData>,
    private val onCategoryClicked: (servicesOfferedData: ServicesOfferedData) -> Unit,
) :
    RecyclerView.Adapter<ServicesOfferedAdapter.ServicesOfferedAdapterViewHolder>() {


    class ServicesOfferedAdapterViewHolder(var binding: ItemServicesOfferedBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServicesOfferedAdapterViewHolder {
        return ServicesOfferedAdapterViewHolder(
            ItemServicesOfferedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ServicesOfferedAdapterViewHolder, position: Int) {

        val categoryItem = servicesOfferedList[position]

        val categoryItemName =
            ServicesCatJob.categoriesList.find { it.categoryID == categoryItem.categoryId }

        holder.binding.tvCategoryName.text = categoryItemName?.categoryName


        holder.binding.btService.setOnClickListener {
//            JobSelected.jobsData=jobItem
            onCategoryClicked.invoke(categoryItem)
        }


    }

    override fun getItemCount(): Int {
        return servicesOfferedList.size
    }

}