package com.bluefox.joly.jobModule.jobProviderModule.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.databinding.ItemViewApplicationBinding

class ViewApplicationAdapter(
    private var context: Context,
    private var applicationsList: List<LoginData>,
    private val onApplicationClicked: (loginData: LoginData) -> Unit,
) :
    RecyclerView.Adapter<ViewApplicationAdapter.ViewApplicationAdapterViewHolder>() {

    class ViewApplicationAdapterViewHolder(var binding: ItemViewApplicationBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewApplicationAdapterViewHolder {
        return ViewApplicationAdapterViewHolder(
            ItemViewApplicationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewApplicationAdapterViewHolder, position: Int) {

        val applicationItem = applicationsList[position]

        holder.binding.tvNameValue.text = applicationItem.name
        holder.binding.tvQualificationValue.text = applicationItem.qualification
        holder.binding.tvPhoneNumberValue.text=applicationItem.phoneNumber

//        holder.binding.cardJobItem.setOnClickListener {
//            onJobClicked.invoke(jobItem)
//        }

    }

    override fun getItemCount(): Int {
        return applicationsList.size
    }

}