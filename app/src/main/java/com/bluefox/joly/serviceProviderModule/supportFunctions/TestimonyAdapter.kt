package com.bluefox.joly.serviceProviderModule.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluefox.joly.databinding.ItemTestimonyBinding
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.bluefox.joly.serviceProviderModule.modelClass.ServicesOfferedData


class TestimonyAdapter(
    private var context: Context,
    private var testimoniesList: List<SPTestimonyData>
) :
    RecyclerView.Adapter<TestimonyAdapter.TestimonyAdapterViewHolder>() {


    class TestimonyAdapterViewHolder(var binding: ItemTestimonyBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestimonyAdapterViewHolder {
        return TestimonyAdapterViewHolder(
            ItemTestimonyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: TestimonyAdapterViewHolder, position: Int) {

        val testimonyItem = testimoniesList[position]

        holder.binding.tvJobTitle.text=testimonyItem.customerName

        holder.binding.tvJob.text=testimonyItem.testimony
//        holder.binding.tvCategoryName.text = categoryItemName?.categoryName




    }

    override fun getItemCount(): Int {
        return testimoniesList.size
    }

}