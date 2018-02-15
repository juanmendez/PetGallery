package info.juanmendez.petgallery.api

import info.juanmendez.petgallery.api.models.BreedListResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by juan on 2/13/18.
 */
interface PetApi {
    @GET(PetRoutes.ALL_BREEDS)
    fun getBreedList():Call<BreedListResponse>
}