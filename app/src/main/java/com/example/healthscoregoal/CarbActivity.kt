package com.example.healthscoregoal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.Headers

fun createJson2() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val apiKey = BuildConfig.apiKey
private const val TAG = "CarbActivity/"

class CarbActivity : AppCompatActivity() {
    private val carb = mutableListOf<ApiTest>()
    lateinit var cRV: RecyclerView
    lateinit var apiFoodAdapter: ApiFoodAdapter
    lateinit var cButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carb)

        val layoutManager = LinearLayoutManager(this)
        cButton = findViewById(R.id.home2Button)
        cRV = findViewById(R.id.carbRV)
        cRV.layoutManager = layoutManager
        apiFoodAdapter = ApiFoodAdapter(carb)
        cRV.adapter = apiFoodAdapter
        cButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["apiKey"] = apiKey
        params["minCarbs"] = "1"
        params["maxCarbs"] = "50"
        params["number"] = "10"

        val carbInfo = intent.getSerializableExtra("CARB_ENTRY") as APICarb?
        if(carbInfo != null) {
            Log.d(TAG, "got extra")
            lifecycleScope.launch(Dispatchers.IO) {
                (application as FitnessApplication).db.fitnessDao().insertCarbs(
                    APICarbs(
                        id = carbInfo.id,
                        exMinCarb = carbInfo.minCarb,
                        exMaxCarb = carbInfo.maxCarb
                    )
                )
            }
        }
        params["minCarbs"] = carbInfo?.minCarb
        params["maxCarbs"] = carbInfo?.maxCarb

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
                        val parsedJson = createJson2().decodeFromString(
                            BaseResponse.serializer(),
                            json.jsonObject.toString()
                        )

                        parsedJson.results?.let { list ->
                            cRV.adapter = ApiFoodAdapter(list)
                        }

                        // Look for this in Logcat:
                        Log.d("CarbActivity", json.toString())
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
                            Log.e("CarbActivity", errorResponse)
                        }
                    }
                }]
    }
}