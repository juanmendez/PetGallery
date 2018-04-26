package info.juanmendez.breedgallery.data.repository.breed

import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable
import io.realm.RealmList

interface BreedDataSource {
    fun addBreed(breed: Breed)
    fun getBreeds(forceRemote: Boolean): Flowable<List<Breed>>
    fun deleteAllBreeds()

    fun addPicsByBreed(breed: String, pics: RealmList<String>)
    fun getPicsByBreed(breedName: String): Flowable<RealmList<String>>
}