package info.juanmendez.breedgallery.data.api

import info.juanmendez.breedgallery.data.api.models.BreedListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedService {
    @GET(BreedRoutes.ALL_BREEDS)
    fun getBreedList(): Call<BreedListResponse>

    @GET(BreedRoutes.PICS_BY_BREED)
    fun getPicsByBreed(@Path("breed") breedName: String): Call<BreedListResponse>
}