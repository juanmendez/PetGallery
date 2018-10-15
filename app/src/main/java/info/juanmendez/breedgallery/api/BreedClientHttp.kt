package info.juanmendez.breedgallery.api

import com.squareup.moshi.Moshi
import info.juanmendez.breedgallery.models.Breed
import info.juanmendez.breedgallery.models.BreedListResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.EBean
import timber.log.Timber
import java.io.IOException
import okhttp3.Callback as OkCallBack

/**
 * Created by Juan Mendez on 2/13/18.
 */
@EBean
class BreedClientHttp {
    private lateinit var client: OkHttpClient

    @AfterInject
    fun afterInject() {
        client = OkHttpClient()
    }

    fun getBreeds(breedCall: BreedCall<List<Breed>>) {

        val request = Request.Builder()
                .url("${BreedRoutes.URL}${BreedRoutes.ALL_BREEDS}")
                .build()

        client.newCall(request).enqueue(object : OkCallBack {
            override fun onFailure(call: okhttp3.Call, e: IOException) {

            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                response.body()?.string()?.let {json ->
                    val moshi = Moshi.Builder().build()
                    val jsonAdapter = moshi.adapter(BreedListResponse::class.java)
                    val breedList = jsonAdapter.fromJson(json)

                    breedList?.let {
                        breedCall.onResponse(it.message.map { Breed(it.key) })
                    }

                }
            }
        })


       /* var call: Call<BreedListResponse> = mBreedApi.getBreedList()

        call.enqueue(object : Callback<BreedListResponse> {
            override fun onFailure(call: Call<BreedListResponse>?, t: Throwable?) {
                breedCall.onError(Exception(t?.message ?: "${BreedRoutes.ALL_BREEDS}Error"))
            }

            override fun onResponse(call: Call<BreedListResponse>?, response: Response<BreedListResponse>?) {
                response?.let {
                    val breedList = it.body().message
                    breedCall.onResponse(breedList.map { Breed(it.key) })
                }
            }
        })*/
    }

    fun getPicsByBreed(breedName: String, breedCall: BreedCall<List<String>>) {
        /*var call: Call<BreedResponse> = mBreedApi.getPicsByBreed(breedName)

        call.enqueue(object : Callback<BreedResponse> {
            override fun onFailure(call: Call<BreedResponse>?, t: Throwable?) {
                breedCall.onError(Exception(t?.message ?: "${BreedRoutes.PICS_BY_BREED}.Error"))
            }

            override fun onResponse(call: Call<BreedResponse>?, response: Response<BreedResponse>?) {
                response?.let {
                    breedCall.onResponse(it.body().message)
                }
            }
        })*/
    }
}