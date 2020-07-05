package com.vydra.possumusdrinks.drinks.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
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
        val binding = DataBindingUtil.inflate<FragmentDetailsBinding>(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )

        // VARARGS
        val args = DetailsFragmentArgs.fromBundle(arguments!!)

        // TOOLBAR
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
            (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar!!.setHomeButtonEnabled(true)
            (activity as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(true);
            (activity as AppCompatActivity).supportActionBar!!.setTitle("")
        }

        binding.toolbar.setNavigationOnClickListener(View.OnClickListener {
            this.findNavController()
                .navigate(R.id.action_detailsFragment_to_overviewFragment)
        })

        // VIEWMODEL
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        binding.textTitle.text = args.titulo
        binding.textUrl.text = args.url

        binding.buttonAddfav.setOnClickListener {
            Toast.makeText(context, "Added to favorites!", Toast.LENGTH_LONG).show()
            args.titulo?.let { it1 -> args.url?.let { it2 -> Drink(it1, it2) } }
                ?.let { it2 -> viewModel.saveDrink(it2) }
        }

        return binding.root
    }

}