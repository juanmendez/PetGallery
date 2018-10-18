package info.juanmendez.breedgallery.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson
import info.juanmendez.breedgallery.models.Breed

class CustomAdapter {
    @FromJson
    @BreedList
    fun breedsFromJson(map: Map<String, List<String>?>): List<Breed> {
        val breeds = mutableListOf<Breed>()

        map.forEach {
            breeds.add(Breed(it.key, it.value))
        }

        return breeds
    }

    @ToJson
    fun breedsToJson(@BreedList breeds: List<Breed>): Map<String, List<String>?> {
        val map = mutableMapOf<String, List<String>?>()

        breeds.forEach {
            map.put(it.name, it.subBreed)
        }

        return map
    }
}

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class BreedList