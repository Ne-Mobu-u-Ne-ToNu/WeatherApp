package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityMainBinding
import org.json.JSONObject

const val API_KEY = "1805e36d6c6344dca15143448231009"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGet.setOnClickListener {
            getResult("Moscow")
        }
    }

    private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/" +
                "current.json?key=$API_KEY&q=$name&aqi=no"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, url,
            {
                response ->
                Log.d("MyLog", "Response: $response")
                val temp = JSONObject(response).getJSONObject("current")
                Toast.makeText(this, "Temp in $name: ${temp.getString("temp_c")}", Toast.LENGTH_SHORT).show()
            },
            {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequest)
    }
}