package info.juanmendez.breedgallery.api

import com.squareup.moshi.FromJson
import info.juanmendez.breedgallery.models.Breed
import info.juanmendez.breedgallery.models.BreedListResponse
import info.juanmendez.breedgallery.models.RawBreedListResponse

class BreedListAdapter {
    @FromJson
    fun fromJson(raw: RawBreedListResponse): BreedListResponse {
        val breeds = mutableListOf<Breed>()
        raw.message.forEach {
            breeds.add(Breed(it.key, it.value))
        }

        return BreedListResponse(raw.status, breeds.toList())
    }
}