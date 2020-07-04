package com.vydra.possumusdrinks.drinks.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vydra.possumusdrinks.R
import com.vydra.possumusdrinks.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding
    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentOverviewBinding>(
            inflater,
            R.layout.fragment_overview,
            container,
            false
        )

        binding.setLifecycleOwner(this)

        // TOOLBAR
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
            (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            (activity as AppCompatActivity).supportActionBar!!.setHomeButtonEnabled(false)
            (activity as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(true);
            (activity as AppCompatActivity).supportActionBar!!.setTitle(" ")
        }

        // VIEWMODEL
        viewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)

        // CLICK FUNCTIONS
        binding.buttonSearchName.setOnClickListener {
            viewModel.getDrinksByName(binding.inputName.text.toString())
        }

        // RECYCLERVIEW
        viewModel.drinkByName.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter = OverviewAdapter(OverviewAdapter.OnClickListener {
                Toast.makeText(context, "Test", Toast.LENGTH_LONG).show()
            }, it)
        })

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)

        return binding.root
    }
}