package com.vydra.possumusdrinks.drinks.overview

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vydra.possumusdrinks.R
import com.vydra.possumusdrinks.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding
    private lateinit var viewModel: OverviewViewModel
    private var filter1_count = 0

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

        // OVERFLOW MENU
        setHasOptionsMenu(true)

        // VIEWMODEL
        viewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)

        // FILTER BUTTONS: SEARCH
        binding.imageFilterName.setOnClickListener {
            filter1_count += 1
            binding.imageFilterName.setBackgroundResource(R.drawable.icon_bg_selected)
            binding.imageFilterIngredient.setBackgroundResource(R.drawable.icon_bg)
            binding.inputName.setHint("Search by name")
        }
        binding.imageFilterIngredient.setOnClickListener {
            filter1_count += 1
            binding.imageFilterName.setBackgroundResource(R.drawable.icon_bg)
            binding.imageFilterIngredient.setBackgroundResource(R.drawable.icon_bg_selected)
            binding.inputName.setHint("Search by Ingredient")
        }

        // SEARCH BUTTON
        binding.buttonSearchName.setOnClickListener {
            if (binding.inputName.text.toString()
                    .isNullOrEmpty() || binding.inputName.text.toString() == ""
            ) {
                Toast.makeText(context, "Empty search!", Toast.LENGTH_SHORT).show()
            } else {
                if (filter1_count % 2 == 0) {
                    viewModel.getDrinksByName(binding.inputName.text.toString())
                } else {
                    viewModel.getDrinksByIngredient(binding.inputName.text.toString())
                }
            }

            binding.appbar.setExpanded(false)
        }

        // RECYCLERVIEW
        viewModel.drinkByName.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter = OverviewAdapter(OverviewAdapter.OnClickListener {
                view?.findNavController()?.navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailsFragment(it.strDrink,it.strDrinkThumb))
            }, it)
        })
        viewModel.drinkByIngredient.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter = OverviewAdapter(OverviewAdapter.OnClickListener {
                view?.findNavController()?.navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailsFragment(it.strDrink,it.strDrinkThumb))
            }, it)
        })

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerView.setHasFixedSize(true)

        // PROGRESS BAR
        binding.progressBar.visibility = View.VISIBLE

        viewModel.internet.observe(viewLifecycleOwner, Observer {
            if (it != true){
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        // SCROLLVIEW ANIMATION
        val scrollViewAnimation =
            AnimationUtils.loadLayoutAnimation(this.context, R.anim.layout_animation_fall_down)
        binding.recyclerView.setLayoutAnimation(scrollViewAnimation)



        return binding.root
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!,
            view!!.findNavController()
        )
                || super.onOptionsItemSelected(item)
    }
}