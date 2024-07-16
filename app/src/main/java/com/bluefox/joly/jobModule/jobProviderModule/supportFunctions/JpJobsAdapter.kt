package com.bluefox.joly.jobModule.jobProviderModule.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluefox.joly.databinding.ItemJobBinding
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData


class JpJobsAdapter(
    private var context: Context,
    private var jobsList: List<PostJobData>,
    private val onJobClicked: (jobItem: PostJobData) -> Unit,
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

        holder.binding.tvJobTitle.text=jobItem.jobName
        holder.binding.tvCompanyLocation.text=jobItem.jobLocation
        holder.binding.tvDeadLineToApplyValue.text=jobItem.postedDate

        holder.binding.cardJobItem.setOnClickListener{
            onJobClicked.invoke(jobItem)
        }


//
//        if(jobItem.profilePhoto!=null) {
//            Glide.with(context)
//                .load(jobItem.profilePhoto)
//                .fitCenter()
//                .into(holder.binding.profileImage)
//        }else{
//            Glide.with(context)
//                .load(SSProfileData.mLoginData.photo)
//                .fitCenter()
//                .into(holder.binding.profileImage)
//        }
//
//
//        //Setting Category
//        val categoryItem = ServicesCatJob.categoriesList.find { it.categoryID == jobItem.categoryId }
//        Log.e("Test","CategoryList ${jobItem.categoryId} ${ServicesCatJob.categoriesList.size}")
//        holder.binding.tvCategory.text=categoryItem?.categoryName
//
//        //Setting JobType
//        val jobItemName = ServicesCatJob.jobList.find { it.jobId == jobItem.jobId }
//        holder.binding.tvJob.text=jobItemName?.jobName
//
//        //Set Date
//        holder.binding.tvDate.text= UtilFunctions.formatDate(jobItem.workPostedDate!!)
//
//        holder.binding.cardJobItem.setOnClickListener {
////            JobSelected.jobsData=jobItem
//            onJobClicked.invoke(jobItem)
//        }



    }

    override fun getItemCount(): Int {
        return jobsList.size
    }

}