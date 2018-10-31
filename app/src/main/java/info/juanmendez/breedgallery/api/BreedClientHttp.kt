package info.juanmendez.breedgallery.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import info.juanmendez.breedgallery.BreedGalleryApp
import info.juanmendez.breedgallery.models.BreedListResponse
import info.juanmendez.breedgallery.models.BreedResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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
class BreedClientHttp {

    @App
    lateinit var app: BreedGalleryApp

    private lateinit var retrofit: Retrofit
    private lateinit var breedApi: BreedApi

    @AfterInject
    fun afterInject() {

        val moshi = Moshi.Builder()
                .add(CustomAdapter())
                .add(KotlinJsonAdapterFactory())
                .build()

        // Define the interceptor to append "always" url-param
        val interceptor = Interceptor { chain ->
            var request = chain.request()
            val httpUrl = request.url()
                    .newBuilder()
                    .addQueryParameter("always", "true")
                    .build()

            request = request.newBuilder().url(httpUrl).build()

            chain.proceed(request)
        }

        val client = OkHttpClient.Builder().apply {
            addNetworkInterceptor(StethoInterceptor())
            interceptors().add(interceptor)
        }.build()

        retrofit = Retrofit
                .Builder()
                .baseUrl(BreedRoutes.URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client) //client embeds our interceptors
                .build()

        breedApi = retrofit.create(BreedApi::class.java)
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

    fun getPicsByBreed(breedName: String, breedCall: BreedCall<List<String>>) {

        val call: Call<BreedResponse> = breedApi.getPicsByBreed(breedName, breedName)

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