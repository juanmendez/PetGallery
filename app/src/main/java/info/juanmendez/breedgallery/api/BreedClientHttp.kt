package info.juanmendez.breedgallery.api

import android.os.Handler
import com.squareup.moshi.Moshi
import info.juanmendez.breedgallery.BreedGalleryApp
import info.juanmendez.breedgallery.models.Breed
import info.juanmendez.breedgallery.models.BreedListResponse
import info.juanmendez.breedgallery.models.BreedResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.App
import org.androidannotations.annotations.EBean
import java.io.IOException
import okhttp3.Callback

@EBean
class BreedClientHttp {

    @App lateinit var app: BreedGalleryApp
    private lateinit var client: OkHttpClient
    private lateinit var handler: Handler
    private val moshi = Moshi.Builder().build()

    @AfterInject
    fun afterInject() {
        client = OkHttpClient()
        handler = Handler(app.mainLooper)
    }

    fun getBreeds(breedCall: BreedCall<List<Breed>>) {

        val request = Request.Builder()
                .url("${BreedRoutes.URL}${BreedRoutes.ALL_BREEDS}")
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
                        val breedList = jsonAdapter.fromJson(json)

                        handler.post {
                            breedList?.let {
                                breedCall.onResponse(it.message.map { Breed(it.key) })
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
                            breedResponse?.let {
                                breedCall.onResponse(it.message)
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