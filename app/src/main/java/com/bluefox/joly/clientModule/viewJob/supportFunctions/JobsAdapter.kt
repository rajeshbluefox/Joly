package com.bluefox.joly.clientModule.viewJob.supportFunctions

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.ServicesCatJob
import com.bluefox.joly.clientModule.viewJob.modalClass.JobSelected
import com.bluefox.joly.clientModule.viewJob.modalClass.JobsData
import com.bluefox.joly.databinding.ItemViewJobBinding
import com.bumptech.glide.Glide
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions


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

        if(jobItem.profilePhoto!=null) {
            Glide.with(context)
                .load(jobItem.profilePhoto)
                .fitCenter()
                .into(holder.binding.profileImage)
        }else{
            Glide.with(context)
                .load(SSProfileData.mLoginData.photo)
                .fitCenter()
                .into(holder.binding.profileImage)
        }


        //Setting Category
        val categoryItem = ServicesCatJob.categoriesList.find { it.categoryID == jobItem.categoryId }
        Log.e("Test","CategoryList ${jobItem.categoryId} ${ServicesCatJob.categoriesList.size}")
        holder.binding.tvCategory.text=categoryItem?.categoryName

        //Setting JobType
        val jobItemName = ServicesCatJob.jobList.find { it.jobId == jobItem.jobId }
        holder.binding.tvJob.text="${jobItem.city}, ${jobItem.district}"

        //Set Date
        holder.binding.tvDate.text=UtilFunctions.formatDate2(jobItem.workPostedDate!!)

        holder.binding.cardJobItem.setOnClickListener {
//            JobSelected.jobsData=jobItem
            onJobClicked.invoke(jobItem)
        }

    }

    override fun getItemCount(): Int {
        return jobsList.size
    }

}