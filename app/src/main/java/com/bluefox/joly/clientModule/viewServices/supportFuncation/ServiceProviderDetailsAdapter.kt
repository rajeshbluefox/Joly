package com.bluefox.joly.clientModule.viewServices.supportFuncation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluefox.joly.clientModule.viewServices.modelClass.ServiceProviderDetailsData
import com.bluefox.joly.databinding.ItemServiceProviderDetailsBinding
import com.bumptech.glide.Glide


class ServiceProviderDetailsAdapter(
    private var context: Context,
    private var serviceProviderList: ArrayList<ServiceProviderDetailsData>,
    private val onServiceClicked: () -> Unit,
) :
    RecyclerView.Adapter<ServiceProviderDetailsAdapter.ServiceProviderDetailsAdapterViewHolder>() {


    class ServiceProviderDetailsAdapterViewHolder(var binding: ItemServiceProviderDetailsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceProviderDetailsAdapterViewHolder {
        return ServiceProviderDetailsAdapterViewHolder(
            ItemServiceProviderDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ServiceProviderDetailsAdapterViewHolder, position: Int) {
        val item= serviceProviderList.get(position)
        holder.binding.tvCompanyName.text=item.companyName

        onServiceClicked.invoke()

    }

    override fun getItemCount(): Int {
        return serviceProviderList.size
    }

}