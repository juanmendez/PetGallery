package info.juanmendez.breedgallery.data.repository.breed

import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable

interface BreedDataSource {
    fun getBreeds(): Flowable<List<Breed>>
    fun getPicsByBreed(breedName: String): Flowable<List<String>>
}