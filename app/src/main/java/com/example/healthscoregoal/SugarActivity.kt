package com.example.healthscoregoal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isEmpty
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject
import com.example.healthscoregoal.MainActivity.Companion.minS
import com.example.healthscoregoal.MainActivity.Companion.maxS
import kotlin.math.max

fun createJson1() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val apiKey = BuildConfig.apiKey
private const val TAG = "SugarActivity/"

class SugarActivity : AppCompatActivity() {
    private val sugar = mutableListOf<ApiTest>()
    lateinit var sRV: RecyclerView
    lateinit var apiFoodAdapter: ApiFoodAdapter
    lateinit var fButton: Button
    lateinit var noneFound: ImageView
    lateinit var notTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sugar)

        noneFound = findViewById(R.id.no_sugar)
        notTV = findViewById(R.id.message_sugar)

        val layoutManager = LinearLayoutManager(this)
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
        params["apiKey"] = apiKey
        params["minSugar"] = minS.toString()
        params["maxSugar"] = maxS.toString()
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
                        val parsedJson = createJson1().decodeFromString(
                            BaseResponse.serializer(),
                            json.jsonObject.toString()
                        )

                        parsedJson.results?.let { list ->
                            if(list.isEmpty()) {
                                noneFound.isVisible = true
                                notTV.isVisible = true
                            }
                            sRV.adapter = ApiFoodAdapter(list)

                        }

                        // Look for this in Logcat:
                        Log.d("SugarActivity", json.toString())
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