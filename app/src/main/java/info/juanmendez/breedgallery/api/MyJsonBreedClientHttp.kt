package info.juanmendez.breedgallery.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import info.juanmendez.breedgallery.BreedGalleryApp
import info.juanmendez.breedgallery.models.BreedListResponse
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.App
import org.androidannotations.annotations.EBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.Callback as OkCallback

@EBean
class MyJsonBreedClientHttp {

    @App
    lateinit var app: BreedGalleryApp

    private lateinit var retrofit: Retrofit
    private lateinit var breedApi: AltBreedApi

    @AfterInject
    fun afterInject() {

        val moshi = Moshi.Builder()
                .add(CustomAdapter())
                .add(KotlinJsonAdapterFactory())
                .build()

        retrofit = Retrofit
                .Builder()
                .baseUrl(BreedRoutes.MYJSON_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

        breedApi = retrofit.create(AltBreedApi::class.java)
    }

    fun getBreeds(breedCall: BreedCall<BreedListResponse>) {

        val call: Call<BreedListResponse> = breedApi.getBreedList()

        call.enqueue(object : Callback<BreedListResponse> {
            override fun onFailure(call: Call<BreedListResponse>?, t: Throwable?) {
                breedCall.onError(Exception(t?.message ?: "${BreedRoutes.ALL_BREEDS}Error"))
            }

            override fun onResponse(call: Call<BreedListResponse>?, response: Response<BreedListResponse>?) {
                response?.let {
                    breedCall.onResponse(it.body())
                }
            }
        })
    }
}