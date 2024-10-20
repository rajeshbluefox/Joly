package com.bluefox.joly.jobModule.jobProviderModule.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluefox.joly.databinding.ItemJobBinding
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions


class JpJobsAdapter(
    private var context: Context,
    private var jobsList: List<PostJobData>,
    private val onJobClicked: (postJobData: PostJobData) -> Unit,
) :
    RecyclerView.Adapter<JpJobsAdapter.JpJobsAdapterViewHolder>() {

    class JpJobsAdapterViewHolder(var binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JpJobsAdapterViewHolder {
        return JpJobsAdapterViewHolder(
            ItemJobBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: JpJobsAdapterViewHolder, position: Int) {

        val jobItem = jobsList[position]

        holder.binding.tvJobTitle.text = jobItem.jobName
        holder.binding.tvCompanyLocation.text = "${jobItem.city}, ${jobItem.district}"
        holder.binding.tvDate.text=jobItem.postedDate
//            UtilFunctions.formatDate3(jobItem.postedDate!!)
        holder.binding.tvDeadLineToApplyValue.text = jobItem.deadLineToApply
//            UtilFunctions.formatDate2(jobItem.deadLineToApply!!)

        holder.binding.cardJobItem.setOnClickListener {
            onJobClicked.invoke(jobItem)
        }

    }

    override fun getItemCount(): Int {
        return jobsList.size
    }

}