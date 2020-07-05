package com.vydra.possumusdrinks.drinks.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vydra.possumusdrinks.R
import com.vydra.possumusdrinks.drinks.database.Drink
import com.vydra.possumusdrinks.drinks.network.DrinkList
import kotlinx.android.synthetic.main.drink_item.view.*

class FavoritesAdapter(private val exampleList: List<Drink>) :
    RecyclerView.Adapter<FavoritesAdapter.ExampleViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.drink_item,
            parent, false
        )
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.textView1.text = currentItem.title.toString()
        val uri: String = currentItem.img.toString()

        Glide.with(holder.itemView.context)
            .load(uri)
            .circleCrop()
            .into(holder.imageUrl)

        holder.itemView.setOnClickListener {
            //onClickListener.onClick(currentItem)
        }
    }

    override fun getItemCount() = exampleList.size

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.text_title
        val imageUrl: ImageView = itemView.imageUrl
    }

    class OnClickListener(val clickListener: (drinksList: DrinkList) -> Unit) {
        fun onClick(drinksList: DrinkList) = clickListener(drinksList)
    }

}