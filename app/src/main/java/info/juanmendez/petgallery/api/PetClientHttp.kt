package info.juanmendez.petgallery.api

import info.juanmendez.petgallery.api.models.Breed
import info.juanmendez.petgallery.api.models.BreedListResponse
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.EBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by juan on 2/13/18.
 */
@EBean
class PetClientHttp {
    private lateinit var retrofit: Retrofit
    private lateinit var petApi: PetApi

    @AfterInject
    fun afterInject(){
        retrofit = Retrofit
                        .Builder()
                        .baseUrl(PetRoutes.PET_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                    .build()

        petApi = retrofit.create( PetApi::class.java)
    }

    fun getBreeds(petCall: PetCall<List<Breed>>){
        var call:Call<BreedListResponse> = petApi.getBreedList()

        call.enqueue( object:Callback<BreedListResponse>{
            override fun onFailure(call: Call<BreedListResponse>?, t: Throwable?) {
                petCall.onError( Exception( t?.message ?: "getBreeds.Error") )
            }

            override fun onResponse(call: Call<BreedListResponse>?, response: Response<BreedListResponse>?) {
                val breedList = response!!.body().message
                petCall.onResponse(breedList.map { Breed(it) })
            }
        })
    }
}