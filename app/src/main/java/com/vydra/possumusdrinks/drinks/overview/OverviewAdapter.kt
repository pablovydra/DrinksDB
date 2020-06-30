package com.vydra.possumusdrinks.drinks.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vydra.possumusdrinks.R
import com.vydra.possumusdrinks.drinks.network.DrinkList
import com.vydra.possumusdrinks.drinks.network.DrinksResponse
import kotlinx.android.synthetic.main.drink_item.view.*

class OverviewAdapter(val onClickListener: OnClickListener,private val exampleList: List<DrinkList>) : RecyclerView.Adapter<OverviewAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.drink_item,
            parent, false
        )
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.textView1.text = currentItem.strDrink.toString()
        holder.textView2.text = currentItem.idDrink.toString()
        val uri: String = currentItem.strDrinkThumb.toString()
        Glide.with(holder.itemView.context).load(uri).into(holder.imageUrl)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(currentItem)
        }
    }

    override fun getItemCount() = exampleList.size

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.text_title
        val textView2: TextView = itemView.text_id
        val imageUrl: ImageView = itemView.imageUrl
    }

    class OnClickListener(val clickListener: (drinksList: DrinkList) -> Unit) {
        fun onClick(drinksList: DrinkList) = clickListener(drinksList)
    }
}