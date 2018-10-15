package info.juanmendez.breedgallery.api

import info.juanmendez.breedgallery.models.Breed
import info.juanmendez.breedgallery.models.BreedListResponse
import info.juanmendez.breedgallery.models.BreedResponse
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.EBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Juan Mendez on 2/13/18.
 */
@EBean
class BreedClientHttp {
    private lateinit var mRetrofit: Retrofit
    private lateinit var mBreedApi: BreedApi

    @AfterInject
    fun afterInject() {
        mRetrofit = Retrofit
                .Builder()
                .baseUrl(BreedRoutes.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mBreedApi = mRetrofit.create(BreedApi::class.java)
    }

    fun getBreeds(breedCall: BreedCall<List<Breed>>) {
        var call: Call<BreedListResponse> = mBreedApi.getBreedList()

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
        })
    }

    fun getPicsByBreed(breedName: String, breedCall: BreedCall<List<String>>) {
        var call: Call<BreedResponse> = mBreedApi.getPicsByBreed(breedName)

        call.enqueue(object : Callback<BreedResponse> {
            override fun onFailure(call: Call<BreedResponse>?, t: Throwable?) {
                breedCall.onError(Exception(t?.message ?: "${BreedRoutes.PICS_BY_BREED}.Error"))
            }

            override fun onResponse(call: Call<BreedResponse>?, response: Response<BreedResponse>?) {
                response?.let {
                    breedCall.onResponse(it.body().message)
                }
            }
        })
    }
}