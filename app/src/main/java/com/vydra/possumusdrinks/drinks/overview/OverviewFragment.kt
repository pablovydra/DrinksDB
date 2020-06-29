package com.vydra.possumusdrinks.drinks.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.vydra.possumusdrinks.R
import com.vydra.possumusdrinks.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding
    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentOverviewBinding>(
            inflater,
            R.layout.fragment_overview,
            container,
            false
        )

        //
        viewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)

        return binding.root
    }
}