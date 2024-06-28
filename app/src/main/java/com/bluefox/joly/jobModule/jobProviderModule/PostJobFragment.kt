package com.bluefox.joly.jobModule.jobProviderModule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bluefox.joly.R
import com.bluefox.joly.databinding.FragmentPostJobBinding
import com.bluefox.joly.databinding.FragmentPostWorkBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostJobFragment : Fragment() {

    private lateinit var binding: FragmentPostJobBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_job, container, false)

        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_post_job, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}