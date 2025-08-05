package com.example.decide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddecider.Restaurant
import com.squareup.picasso.Picasso

class RestaurantAdapter(private val restaurants: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvRestaurantName)
        val ivImage: ImageView = view.findViewById(R.id.ivRestaurantImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.tvName.text = restaurant.name
        if (restaurant.imageUrl != null && restaurant.imageUrl.isNotEmpty()) {
            Picasso.get()
                .load(restaurant.imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivImage)
        } else {
            holder.ivImage.setImageResource(R.mipmap.ic_launcher)
        }
    }
}
