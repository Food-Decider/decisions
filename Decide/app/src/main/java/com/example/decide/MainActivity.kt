package com.example.decide

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddecider.Restaurant
import com.example.decide.RestaurantAdapter
import com.loopj.android.http.*
import com.squareup.picasso.Picasso
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var etFood: EditText
    private lateinit var btnFind: Button
    private lateinit var btnDecide: Button
    private lateinit var tvResult: TextView
    private lateinit var rvRestaurants: RecyclerView

    private val client = AsyncHttpClient()
    private val restaurants = mutableListOf<Restaurant>()
    private lateinit var adapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFood = findViewById(R.id.etFood)
        btnFind = findViewById(R.id.btnFind)
        btnDecide = findViewById(R.id.btnDecide)
        tvResult = findViewById(R.id.tvResult)
        rvRestaurants = findViewById(R.id.rvRestaurants)


        adapter = RestaurantAdapter(restaurants)
        rvRestaurants.layoutManager = LinearLayoutManager(this)
        rvRestaurants.adapter = adapter

        btnFind.setOnClickListener {
            val term = etFood.text.toString()
            if (term.isNotBlank()) {
                searchYelp(term)
            } else {
                Toast.makeText(this, "Please enter a craving", Toast.LENGTH_SHORT).show()
            }
        }

        btnDecide.setOnClickListener {
            decideRestaurant()
        }

        val changeButton = findViewById<Button>(R.id.btnChangeToCooking)

        // Set click listener to launch Cooking activity
        changeButton.setOnClickListener {
            val intent = Intent(this, Cooking::class.java)
            startActivity(intent)
        }
    }

    private fun searchYelp(term: String) {
        val url = "https://api.yelp.com/v3/businesses/search"
        val params = RequestParams()
        params.put("term", term)
        params.put("location", "Los Angeles")
        params.put("limit", 3)

        client.removeAllHeaders()
        client.addHeader("Authorization", "Bearer " + getString(R.string.yelp_api_key))

        client.get(url, params, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
                restaurants.clear()
                val businesses = response.getJSONArray("businesses")
                for (i in 0 until businesses.length()) {
                    val biz = businesses.getJSONObject(i)
                    val name = biz.getString("name")
                    val imageUrl = if (biz.has("image_url")) biz.getString("image_url") else null
                    restaurants.add(Restaurant(name, imageUrl))
                }
                adapter.notifyDataSetChanged()
                rvRestaurants.visibility = View.VISIBLE
                btnDecide.visibility = View.VISIBLE
                tvResult.text = ""
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>?, throwable: Throwable?, errorResponse: JSONObject?) {
                Toast.makeText(this@MainActivity, "Failed to fetch from Yelp", Toast.LENGTH_SHORT).show()
                Log.e("YELP", "API failure", throwable)
            }
        })
    }

    private fun decideRestaurant() {
        if (restaurants.isEmpty()) {
            Toast.makeText(this, "No restaurants to choose from", Toast.LENGTH_SHORT).show()
            return
        }

        // Pick a random restaurant
        val choiceIndex = (restaurants.indices).random()
        val chosen = restaurants[choiceIndex]

        val yesNoUrl = "https://yesno.wtf/api"
        client.get(yesNoUrl, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
                val answer = response.getString("answer")
                val message = "${chosen.name}\nShould you go here? $answer"
                tvResult.text = message
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>?, throwable: Throwable?, errorResponse: JSONObject?) {
                tvResult.text = "${chosen.name}\n(Yes/No service failed)"
            }
        })
    }
}
