package info.juanmendez.breedgallery.data.api

import info.juanmendez.breedgallery.data.api.models.Breed
import info.juanmendez.breedgallery.data.api.models.BreedListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BreedDataSourceRemote {

    private var retrofit: Retrofit = Retrofit.Builder().baseUrl(BreedRoutes.URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    private var breedService: BreedService = retrofit.create(BreedService::class.java)

    fun getBreeds(breedCall: BreedCall<List<Breed>>) {
        var call: Call<BreedListResponse> = breedService.getBreedList()

        call.enqueue(object : Callback<BreedListResponse> {
            override fun onFailure(call: Call<BreedListResponse>?, t: Throwable?) {
                breedCall.onError(Exception(t?.message ?: "${BreedRoutes.ALL_BREEDS}Error"))
            }

            override fun onResponse(call: Call<BreedListResponse>?, response: Response<BreedListResponse>?) {
                val breedList = response!!.body().message
                breedCall.onResponse(breedList.map { Breed(it) })
            }
        })
    }

    fun getPicsByBreed(breedName: String, breedCall: BreedCall<List<String>>) {
        var call: Call<BreedListResponse> = breedService.getPicsByBreed(breedName)

        call.enqueue(object : Callback<BreedListResponse> {
            override fun onFailure(call: Call<BreedListResponse>?, t: Throwable?) {
                breedCall.onError(Exception(t?.message ?: "${BreedRoutes.PICS_BY_BREED}.Error"))
            }

            override fun onResponse(call: Call<BreedListResponse>?, response: Response<BreedListResponse>?) {
                val breedList = response!!.body().message
                breedCall.onResponse(breedList)
            }
        })
    }
}