package com.bluefox.joly.clientModule.postJob.supportFunctions

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bluefox.joly.R


class TermsDialog(
    layoutInflater: LayoutInflater,
    context: Context
) {

    private val mLayoutInflater: LayoutInflater = layoutInflater
    private val mContext: Context = context

    fun openTNCDialog(userRole: Int) {

        val view = mLayoutInflater.inflate(R.layout.alert_terms_condotions, null)
        val dialog = AlertDialog.Builder(mContext).setView(view)
        dialog.setCancelable(false)
        val messageBoxInstance = dialog.show()

        val btyes=view.findViewById<TextView>(R.id.btYes)

        setTerms(userRole,view)

        btyes.setOnClickListener {
            messageBoxInstance.dismiss()
        }

    }

    fun setTerms(userRole: Int,view: View)
    {
        val positionTitle = view.findViewById<TextView>(R.id.positionTitle)
        val term1 = view.findViewById<TextView>(R.id.term1)
        val term2 = view.findViewById<TextView>(R.id.term2)
        val term3 = view.findViewById<TextView>(R.id.term3)

        when(userRole)
        {
            1 -> {
                val terms = getTerms(1)
                positionTitle.text = "Service Seeker T&C"
                term1.text = terms[0]
                term2.text = terms[1]
                term3.text = terms[2]
                }
            2 -> {
                val terms = getTerms(2)
                positionTitle.text = "Service Provider T&C"
                term1.text = terms[0]
                term2.text = terms[1]
                term3.text = terms[2]
            }
            3 -> {
                val terms = getTerms(3)
                positionTitle.text = "Job Provider T&C"
                term1.text = terms[0]
                term2.text = terms[1]
                term3.text = terms[2]
            }
            4 -> {
                val terms = getTerms(4)
                positionTitle.text = "Job Seeker T&C"
                term1.text = terms[0]
                term2.text = terms[1]
                term3.text = terms[2]
            }
        }
    }

    fun getTerms(module: Int): List<String> {
        return when (module) {
            1 -> listOf(
                "1. Direct Contact: By posting a service request, you agree that your name and contact details will be shared with Service Providers.",
                "2. Responsibility Disclaimer: The app does not participate in any negotiations or agreements between you and the Service Provider. It solely provides a platform to connect users.",
                "3. No Liability: The app is not responsible for any failure to provide or receive services, payment issues, or disputes arising from service agreements."
            )
            2 -> listOf(
                "1. Direct Engagement: By viewing and responding to service posts, you agree to contact the Service Seeker directly using the provided contact information.",
                "2. Responsibility Disclaimer: The app merely facilitates connections and is not responsible for any service quality, fulfillment, or payment disputes between you and the Service Seeker.",
                "3. No Liability: The app is not liable for any issues or losses resulting from service agreements made outside the app."
            )
            3 -> listOf(
                "1. Posting Jobs: By posting jobs, you agree to provide accurate information about the position, including job details, requirements, and application deadlines.",
                "2. Direct Applications: Job applications will be received directly from Job Seekers, and it is your responsibility to manage communications and selection processes.",
                "3. No Liability: The app is not responsible for any disputes, hiring issues, or the quality of candidates. The app only serves as a platform to facilitate job postings and applications."
            )
            4 -> listOf(
                "1. Application Submission: By applying to jobs, you agree that your information will be shared directly with the Job Provider. The app does not control or influence hiring decisions.",
                "2. Responsibility Disclaimer: The app is not involved in the recruitment process or the final job offer. All negotiations and agreements are between the Job Seeker and Job Provider.",
                "3. No Liability: The app is not responsible for any disputes or failures in hiring, compensation, or employment conditions."
            )
            else -> emptyList() // In case of an invalid input
        }
    }


}