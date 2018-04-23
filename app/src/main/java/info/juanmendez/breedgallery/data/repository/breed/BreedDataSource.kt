package info.juanmendez.breedgallery.data.repository.breed

import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable

interface BreedDataSource {
    fun addBreed( breed: Breed )
    fun getBreeds(): Flowable<List<Breed>>
    fun deleteAllBreeds()

    fun addPicsByBreed( breed:String, pics:List<String> )
    fun getPicsByBreed(breedName: String): Flowable<List<String>>
}