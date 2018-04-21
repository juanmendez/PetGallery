package info.juanmendez.breedgallery.data.api

import info.juanmendez.breedgallery.model.BreedListResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedService {
    @GET(BreedRoutes.ALL_BREEDS)
    fun getBreedList(): Flowable<BreedListResponse>

    @GET(BreedRoutes.PICS_BY_BREED)
    fun getPicsByBreed(@Path("breed") breedName: String): Flowable<BreedListResponse>
}