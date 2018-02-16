package info.juanmendez.petgallery.api

import info.juanmendez.petgallery.api.models.BreedListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by juan on 2/13/18.
 */
interface BreedApi {
    @GET(BreedRoutes.ALL_BREEDS)
    fun getBreedList():Call<BreedListResponse>

    @GET( BreedRoutes.PICS_BY_BREED)
    fun getPicsByBreed( @Path("breed") breedName:String):Call<BreedListResponse>
}