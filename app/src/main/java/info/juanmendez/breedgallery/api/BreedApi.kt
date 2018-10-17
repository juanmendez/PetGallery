package info.juanmendez.breedgallery.api

import info.juanmendez.breedgallery.models.BreedListResponse
import info.juanmendez.breedgallery.models.BreedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Juan Mendez on 2/13/18.
 */
interface BreedApi {
    @GET(BreedRoutes.PICS_BY_BREED)
    fun getPicsByBreed(@Path("breed") breedName: String): Call<BreedResponse>
}

interface AltBreedApi {
    @GET(BreedRoutes.ALL_BREEDS)
    fun getBreedList(): Call<BreedListResponse>
}