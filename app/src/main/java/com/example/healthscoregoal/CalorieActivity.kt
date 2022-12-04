package com.example.healthscoregoal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers

fun createJson3() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val apiKey = BuildConfig.apiKey
private const val TAG = "CalorieActivity/"
class CalorieActivity : AppCompatActivity() {
    private val cal = mutableListOf<ApiTest>()
    lateinit var calRV: RecyclerView
    lateinit var apiFoodAdapter: ApiFoodAdapter
    lateinit var calButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calorie)
        val layoutManager = LinearLayoutManager(this)
        calButton = findViewById(R.id.home3Button)
        calRV = findViewById(R.id.calorieRV)
        calRV.layoutManager = layoutManager
        apiFoodAdapter = ApiFoodAdapter(cal)
        calRV.adapter = apiFoodAdapter
        calButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["apiKey"] = apiKey
        params["minCalories"] = "1"
        params["maxCalories"] = "200"
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
                        val parsedJson = createJson3().decodeFromString(
                            BaseResponse.serializer(),
                            json.jsonObject.toString()
                        )

                        parsedJson.results?.let { list ->
                            calRV.adapter = ApiFoodAdapter(list)
                        }

                        // Look for this in Logcat:
                        Log.d("CalorieActivity", json.toString())
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
                            Log.e("CalorieActivity", errorResponse)
                        }
                    }
                }]
    }
}