package com.example.decide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddecider.Recipe
import com.squareup.picasso.Picasso

class RecipeAdapter(private val recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvRecipeName)
        val ivImage: ImageView = view.findViewById(R.id.ivRecipeImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.tvName.text = recipe.name
        if (recipe.imageUrl != null && recipe.imageUrl.isNotEmpty()) {
            Picasso.get()
                .load(recipe.imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivImage)
        } else {
            holder.ivImage.setImageResource(R.mipmap.ic_launcher)
        }
    }
}
