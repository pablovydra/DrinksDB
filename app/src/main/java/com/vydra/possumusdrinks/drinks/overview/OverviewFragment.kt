package com.vydra.possumusdrinks.drinks.overview

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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
    private var filter1_count = 0
    private var filter2_count  = 0

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

        // FILTER BUTTONS: SEARCH
        binding.imageFilterName.setOnClickListener {
            filter1_count += 1
            binding.imageFilterName.setBackgroundResource(R.drawable.icon_bg_selected)
            binding.imageFilterIngredient.setBackgroundResource(R.drawable.icon_bg)
        }
        binding.imageFilterIngredient.setOnClickListener {
            filter1_count += 1
            binding.imageFilterName.setBackgroundResource(R.drawable.icon_bg)
            binding.imageFilterIngredient.setBackgroundResource(R.drawable.icon_bg_selected)
        }

        // FILTER BUTTONS: ALCOHOL
        binding.alcoholYes.setOnClickListener {
            filter2_count += 1
            binding.alcoholYes.setBackgroundResource(R.drawable.icon_bg_selected)
            binding.alcoholNo.setBackgroundResource(R.drawable.icon_bg)
        }
        binding.alcoholNo.setOnClickListener {
            filter2_count += 1
            binding.alcoholYes.setBackgroundResource(R.drawable.icon_bg)
            binding.alcoholNo.setBackgroundResource(R.drawable.icon_bg_selected)
        }

        // SEARCH BUTTON
        binding.buttonSearchName.setOnClickListener {
            if (binding.inputName.text.toString().isNullOrEmpty() || binding.inputName.text.toString() == "") {
                Toast.makeText(context, "Empty search!", Toast.LENGTH_SHORT).show()
            } else {
                if (filter1_count % 2 == 0) {
                    if (filter2_count% 2 == 0){
                        viewModel.getDrinksByName(binding.inputName.text.toString(),"Alcoholic")
                    } else {
                        viewModel.getDrinksByName(binding.inputName.text.toString(), "Non_Alcoholic")
                    }
                } else {
                    if (filter2_count% 2 == 0){
                        viewModel.getDrinksByIngredient(binding.inputName.text.toString(),"Alcoholic")
                    } else {
                        viewModel.getDrinksByIngredient(binding.inputName.text.toString(),"Non_Alcoholic")
                    }
                }
            }
        }

        // RECYCLERVIEW
        viewModel.drinkByName.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter = OverviewAdapter(OverviewAdapter.OnClickListener {
                Toast.makeText(context, "Test", Toast.LENGTH_LONG).show()
            }, it)
        })
        viewModel.drinkByIngredient.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter = OverviewAdapter(OverviewAdapter.OnClickListener {
                Toast.makeText(context, "Test", Toast.LENGTH_LONG).show()
            }, it)
        })

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)

        // SCROLLVIEW ANIMATION
        val scrollViewAnimation =
            AnimationUtils.loadLayoutAnimation(this.context, R.anim.layout_animation_fall_down)
        binding.recyclerView.setLayoutAnimation(scrollViewAnimation)



        return binding.root
    }
}