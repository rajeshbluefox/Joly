package com.bluefox.joly.serviceProviderModule.supportFunctions

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluefox.joly.clientModule.postJob.modalClass.JobItem
import com.bluefox.joly.databinding.ItemSelectJobBinding
import com.bluefox.joly.serviceProviderModule.modelClass.AddServicesSelectedJobs


class SelectJobAdapter(
    private var context: Context,
    private var jobItemList: List<JobItem>
) :
    RecyclerView.Adapter<SelectJobAdapter.SelectJobAdapterAdapterViewHolder>() {


    class SelectJobAdapterAdapterViewHolder(var binding: ItemSelectJobBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectJobAdapterAdapterViewHolder {
        return SelectJobAdapterAdapterViewHolder(
            ItemSelectJobBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: SelectJobAdapterAdapterViewHolder, position: Int) {

        val jobItem = jobItemList[position]

        holder.binding.tvJobName.text = jobItem.jobName


        if(AddServicesSelectedJobs.selectedPage==1) {
            holder.binding.btCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    Log.e("Test", "Add ${jobItem.jobId} ${jobItem.jobName}")
                    AddServicesSelectedJobs.selectedJobsList.add(jobItem.jobId!!)
                    //add
                } else {
                    Log.e("Test", "Remove ${jobItem.jobId} ${jobItem.jobName}")
                    AddServicesSelectedJobs.selectedJobsList.remove(jobItem.jobId!!)

                    //remove
                }
            }
        }else{
            holder.binding.btCheckBox.isChecked=true
            holder.binding.btCheckBox.isEnabled=false
        }

    }

    override fun getItemCount(): Int {
        return jobItemList.size
    }

}