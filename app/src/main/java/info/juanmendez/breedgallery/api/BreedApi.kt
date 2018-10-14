package info.juanmendez.breedgallery.api

import info.juanmendez.breedgallery.ui.breedlist.models.BreedListResponse
import info.juanmendez.breedgallery.ui.breedlist.models.BreedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Juan Mendez on 2/13/18.
 */
interface BreedApi {
    @GET(BreedRoutes.ALL_BREEDS)
    fun getBreedList(): Call<BreedListResponse>

    @GET(BreedRoutes.PICS_BY_BREED)
    fun getPicsByBreed(@Path("breed") breedName: String): Call<BreedResponse>
}