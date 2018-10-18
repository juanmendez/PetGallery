package info.juanmendez.breedgallery.models

import com.squareup.moshi.Json
import info.juanmendez.breedgallery.api.BreedList

data class BreedListResponse(var status: String, @BreedList @Json(name = "message") var list: List<Breed>)

data class RawBreedListResponse(var status: String, var message: Map<String, List<String>>)