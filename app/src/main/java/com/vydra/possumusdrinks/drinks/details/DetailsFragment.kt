package com.vydra.possumusdrinks.drinks.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.vydra.possumusdrinks.R
import com.vydra.possumusdrinks.databinding.FragmentDetailsBinding
import com.vydra.possumusdrinks.drinks.database.Drink
import com.vydra.possumusdrinks.drinks.overview.OverviewViewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentDetailsBinding>(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )

        // VIEWMODEL
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        // VARARGS
        val args = DetailsFragmentArgs.fromBundle(arguments!!)

        binding.textTitle.text = args.titulo
        binding.textUrl.text = args.url

        binding.buttonAddfav.setOnClickListener {
            args.titulo?.let { it1 -> args.url?.let { it2 -> Drink(it1, it2) } }
                ?.let { it2 -> viewModel.saveDrink(it2) }
        }

        return binding.root
    }

}