package com.vydra.possumusdrinks.drinks.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vydra.possumusdrinks.R
import com.vydra.possumusdrinks.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFavoritesBinding>(
            inflater,
            R.layout.fragment_favorites,
            container,
            false
        )

        // VIEWMODEL
        viewModel = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)

        viewModel.drinks.observe(viewLifecycleOwner, Observer{
            binding.recyclerView.adapter = FavoritesAdapter(it)
        })
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerView.setHasFixedSize(true)

        return binding.root
    }
}