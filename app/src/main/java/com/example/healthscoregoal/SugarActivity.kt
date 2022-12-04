package com.example.healthscoregoal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject

private const val apiKey = BuildConfig.apiKey
private const val TAG = "SugarActivity/"

class SugarActivity : AppCompatActivity() {
    private val sugar = mutableListOf<ApiTest>()
    lateinit var sRV: RecyclerView
    lateinit var apiFoodAdapter: ApiFoodAdapter
    lateinit var fButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sugar)

        val layoutManager = GridLayoutManager(this, 2)
        fButton = findViewById(R.id.homeButton)
        sRV = findViewById(R.id.sugarRV)
        sRV.layoutManager = layoutManager
        apiFoodAdapter = ApiFoodAdapter(sugar)
        sRV.adapter = apiFoodAdapter
        fButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = apiKey
        params["minSugar"] = "1"
        params["maxSugar"] = "50"
        params["number"] = "10"

        // Using the client, perform the HTTP request
        client[
                "https://api.spoonacular.com/recipes/complexSearch",
                params,
                object : JsonHttpResponseHandler()
                {
                    /*
                     * The onSuccess function gets called when
                     * HTTP response status is "200 OK"
                     */
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JSON
                    ) {
                        val resultsJSON : JSONObject = json.jsonObject.get("results") as JSONObject
                        val sugarRawJSON : String = resultsJSON.get("sugar").toString()
                        val gson = Gson()
                        val arrayTutorialType = object : TypeToken<List<ApiTest>>() {}.type
                        val models : List<ApiTest> = gson.fromJson(sugarRawJSON, arrayTutorialType)
                        sRV.adapter = ApiFoodAdapter(models)

                        // Look for this in Logcat:
                        Log.d("SugarActivity", "response successful")
                    }

                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("SugarActivity", errorResponse)
                        }
                    }
                }]
    }
}