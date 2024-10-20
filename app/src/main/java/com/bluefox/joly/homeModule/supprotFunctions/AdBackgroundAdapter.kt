package com.bluefox.joly.homeModule.supprotFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluefox.joly.databinding.ItemAdBackgroundBinding


class AdBackgroundAdapter(
    private var context: Context,
    private var backgroundDrawables: List<Int>,
    private val onBackgroundClicked: (backgroundItem : Int) -> Unit,
) :
    RecyclerView.Adapter<AdBackgroundAdapter.AdBackgroundAdapterViewHolder>() {


    class AdBackgroundAdapterViewHolder(var binding: ItemAdBackgroundBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdBackgroundAdapterViewHolder {
        return AdBackgroundAdapterViewHolder(
            ItemAdBackgroundBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: AdBackgroundAdapterViewHolder, position: Int) {

        val backgroundItem = backgroundDrawables[position]

        holder.binding.background.setBackgroundResource(backgroundItem)

        holder.binding.background.setOnClickListener {
            onBackgroundClicked.invoke(backgroundItem)
        }

    }

    override fun getItemCount(): Int {
        return backgroundDrawables.size
    }
}