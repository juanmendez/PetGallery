package info.juanmendez.breedgallery.api

import android.os.Handler
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import info.juanmendez.breedgallery.BreedGalleryApp
import info.juanmendez.breedgallery.models.BreedListResponse
import info.juanmendez.breedgallery.models.BreedResponse
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.App
import org.androidannotations.annotations.EBean
import java.io.IOException

@EBean
class BreedClientHttp {

    @App
    lateinit var app: BreedGalleryApp
    private lateinit var client: OkHttpClient
    private lateinit var handler: Handler

    @AfterInject
    fun afterInject() {
        client = OkHttpClient()
        handler = Handler(app.mainLooper)
    }

    fun getBreeds(breedCall: BreedCall<BreedListResponse>) {

        val request = Request.Builder()
                .url("https://api.myjson.com/bins/de3kw")
                .build()

        val moshi = Moshi.Builder()
                    .add(CustomAdapter())
                    .add(KotlinJsonAdapterFactory())
                    .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                handler.post {
                    breedCall.onError(e)
                }
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.isSuccessful) {
                    response.body()?.string()?.let { json ->
                        val jsonAdapter = moshi.adapter(BreedListResponse::class.java)
                        val response = jsonAdapter.fromJson(json)

                        handler.post {
                            response?.let {
                                breedCall.onResponse(it)
                            }
                        }
                    }
                } else {
                    this.onFailure(call, IOException("call was not successful"))
                }

            }
        })
    }

    fun getPicsByBreed(breedName: String, breedCall: BreedCall<List<String>>) {

        val moshi = Moshi.Builder().build()
        val url = "${BreedRoutes.URL}${BreedRoutes.PICS_BY_BREED}".replace("{breed}", breedName)
        val request = Request.Builder()
                .url(url)
                .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                handler.post {
                    breedCall.onError(e)
                }
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.isSuccessful) {
                    response.body()?.string()?.let { json ->
                        val jsonAdapter = moshi.adapter(BreedResponse::class.java)

                        val breedResponse = jsonAdapter.fromJson(json)

                        handler.post {
                            breedResponse?.message?.let {
                                breedCall.onResponse(it)
                            }
                        }

                    }
                } else {
                    this.onFailure(call, IOException("call was not successful"))
                }
            }
        })
    }
}