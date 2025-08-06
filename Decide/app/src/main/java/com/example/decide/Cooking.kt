// Cooking.kt
package com.example.decide

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddecider.Recipe
import com.loopj.android.http.*
import com.squareup.picasso.Picasso
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import org.json.JSONArray

class Cooking : AppCompatActivity() {

    private lateinit var tvIngredient: TextView
    private lateinit var btnBeef: Button
    private lateinit var btnChicken: Button
    private lateinit var btnPork: Button
    private lateinit var btnLamb: Button
    private lateinit var tvResult: TextView
    private lateinit var rvRecipes: RecyclerView

    private val client = AsyncHttpClient()
    private val recipes = mutableListOf<Recipe>()
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cooking)

        tvIngredient = findViewById(R.id.tvIngredient)
        btnBeef = findViewById(R.id.btnBeef)
        btnChicken = findViewById(R.id.btnChicken)
        btnPork = findViewById(R.id.btnPork)
        btnLamb = findViewById(R.id.btnLamb)

        rvRecipes = findViewById(R.id.rvRecipes)


        adapter = RecipeAdapter(recipes)
        rvRecipes.layoutManager = LinearLayoutManager(this)
        rvRecipes.adapter = adapter

        btnBeef.setOnClickListener {
            searchTheMealDB("beef")
        }

        btnChicken.setOnClickListener {
            searchTheMealDB("chicken")
        }

        btnPork.setOnClickListener {
            searchTheMealDB("pork")
        }

        btnLamb.setOnClickListener {
            searchTheMealDB("lamb")
        }
    }

    private fun searchTheMealDB(ingredient: String) {
        val url = "https://www.themealdb.com/api/json/v1/1/filter.php?i=${ingredient}"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
                recipes.clear()
                val meals = response.getJSONArray("meals")
                for (i in 0 until meals.length()) {
                    val meal = meals.getJSONObject(i)
                    val name = meal.getString("strMeal")
                    val imageUrl = if (meal.has("strMealThumb")) meal.getString("strMealThumb") else null
                    recipes.add(Recipe(name, imageUrl))
                }
                adapter.notifyDataSetChanged()
                rvRecipes.visibility = View.VISIBLE
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>?, throwable: Throwable?, errorResponse: JSONObject?) {
                Toast.makeText(this@Cooking, "Failed to fetch from The Meal DB", Toast.LENGTH_SHORT).show()
                Log.e("THEMEALDB", "API failure", throwable)
            }
        })
    }
}

