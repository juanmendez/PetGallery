package info.juanmendez.breedgallery.data.repository.breed

import info.juanmendez.breedgallery.data.api.BreedCall
import info.juanmendez.breedgallery.data.api.models.Breed

interface BreedDataSource {
    fun getBreeds(breedCall: BreedCall<List<Breed>>)
    fun getPicsByBreed(breedName: String, breedCall: BreedCall<List<String>>)
}