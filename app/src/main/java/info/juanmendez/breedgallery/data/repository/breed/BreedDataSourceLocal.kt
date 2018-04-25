package info.juanmendez.breedgallery.data.repository.breed

import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmList
import javax.inject.Inject

class BreedDataSourceLocal @Inject constructor() : BreedDataSource {

    val breedList:MutableList<Breed> = mutableListOf()
    val realm: Realm = Realm.getDefaultInstance()

    override fun getBreeds(): Flowable<List<Breed>> {
        return Flowable.just(breedList)
    }

    override fun getPicsByBreed(breedName: String): Flowable<RealmList<String>> {
        val list: RealmList<String> = breedList.filter { it.name == breedName }.first().pictureList

        return Flowable.just( list )
    }

    override fun addBreed(breed: Breed) {
        breedList.add( breed )
    }

    override fun deleteAllBreeds() {
        breedList.clear()
    }

    override fun addPicsByBreed(breedName: String, pics: RealmList<String>) {
        breedList.filter { it.name == breedName }.first().pictureList = pics
    }
}