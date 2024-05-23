package com.bluefox.joly.clientModule.viewJob.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.viewJob.modalClass.JobSelected
import com.bluefox.joly.clientModule.viewJob.modalClass.JobsData
import com.bluefox.joly.databinding.ItemViewJobBinding


class JobsAdapter(
    private var context: Context,
    private var jobsList: List<PostWorkData>,
    private val onJobClicked: (jobItem: PostWorkData) -> Unit,
) :
    RecyclerView.Adapter<JobsAdapter.JobsAdapterViewHolder>() {


    class JobsAdapterViewHolder(var binding: ItemViewJobBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JobsAdapterViewHolder {
        return JobsAdapterViewHolder(
            ItemViewJobBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: JobsAdapterViewHolder, position: Int) {

        val jobItem = jobsList[position]

        holder.binding.tvJobTitle.text=jobItem.workName

        holder.binding.cardJobItem.setOnClickListener {
//            JobSelected.jobsData=jobItem
            onJobClicked.invoke(jobItem)
        }



    }

    override fun getItemCount(): Int {
        return jobsList.size
    }

}