package com.bluefox.joly.serviceProviderModule.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bluefox.joly.R
import com.bluefox.joly.databinding.ItemTestimonyBinding
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions


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

        holder.binding.tvJobTitle.text = testimonyItem.customerName

        holder.binding.tvJob.text = testimonyItem.testimony
//        holder.binding.tvCategoryName.text = categoryItemName?.categoryName

        holder.binding.tvDate.text= UtilFunctions.formatDate2(testimonyItem.createdDate!!)


        updateStarRating(testimonyItem.rating.toDouble(), holder)


    }

    override fun getItemCount(): Int {
        return testimoniesList.size
    }

    private fun updateStarRating(rating: Double, holder: TestimonyAdapterViewHolder) {
        val adjustedRating = adjustRating(rating)

        // Assuming you have 5 stars in a LinearLayout
        val stars = arrayOf(
            holder.binding.star1,
            holder.binding.star2,
            holder.binding.star3,
            holder.binding.star4,
            holder.binding.star5
        ) // Replace with your actual ImageView references

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