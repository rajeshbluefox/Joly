package com.bluefox.joly.clientModule.viewServices.supportFuncation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.viewServices.modelClass.ServiceProviderDetailsData
import com.bluefox.joly.databinding.ItemServiceProvidersBinding
import com.bumptech.glide.Glide


class ViewSPAdapter(
    private var context: Context,
    private var serviceProviderList: ArrayList<ServiceProviderDetailsData>,
    private val onServiceClicked: (serviceProviderData: ServiceProviderDetailsData) -> Unit,
) :
    RecyclerView.Adapter<ViewSPAdapter.ViewSPAdapterViewHolder>() {


    class ViewSPAdapterViewHolder(var binding: ItemServiceProvidersBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewSPAdapterViewHolder {
        return ViewSPAdapterViewHolder(
            ItemServiceProvidersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewSPAdapterViewHolder, position: Int) {
        val item = serviceProviderList[position]

        holder.binding.tvCompanyName.text = item.companyName

        updateStarRating(item.currentRating.toDouble(),holder)

        if(item.photo!=null) {
            Glide.with(context)
                .load(item.photo)
                .fitCenter()
                .into(holder.binding.companyProfile)
        }

        holder.binding.cardServiceProviderDetails.setOnClickListener {
            onServiceClicked.invoke(item)

        }

    }

    override fun getItemCount(): Int {
        return serviceProviderList.size
    }

    private fun updateStarRating(rating: Double, holder: ViewSPAdapterViewHolder) {
        val adjustedRating = adjustRating(rating)

        // Assuming you have 5 stars in a LinearLayout
        val stars = arrayOf(holder.binding.star1, holder.binding.star2, holder.binding.star3, holder.binding.star4, holder.binding.star5) // Replace with your actual ImageView references

        // Loop through the stars and set the image based on the adjusted rating
        for (i in stars.indices) {
            if (i < adjustedRating) {
                stars[i].setImageResource(R.drawable.star_filled) // Filled star
            } else {
                stars[i].setImageResource(R.drawable.star) // Empty star
            }
        }
    }

    private fun adjustRating(rating: Double): Int {
        return when {
            rating <= 0.0 -> 0 // If rating is 0 or less
            rating in 0.1..0.5 -> rating.toInt() // Floor for values between 0.1 and 0.5
            else -> Math.ceil(rating).toInt() // Ceiling for values above 0.5
        }
    }


}