package com.bluefox.joly.clientModule.postJob.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.postJob.modalClass.JobItem


class JobsSpinnerAdapter(context: Context, resource: Int, private val items: List<JobItem>) :
    ArrayAdapter<JobItem>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_spinner, parent, false)

        val textView = view.findViewById<TextView>(R.id.itemName)
        textView.text = items[position].jobName

        return view
    }
}