package info.juanmendez.breedgallery.data.repository.breed

import android.content.Context
import info.juanmendez.breedgallery.data.database.BreedRealm
import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class BreedDataSourceLocal @Inject constructor( val breedRealm: BreedRealm, val realmConfiguration: RealmConfiguration ) : BreedDataSource {

    val breedList:MutableList<Breed> = mutableListOf()
    val realm: Realm = Realm.getDefaultInstance()

    init {
        println( "just created")
    }

    override fun getBreeds(): Flowable<List<Breed>> {
        return Flowable.just(breedList)
    }

    override fun getPicsByBreed(breedName: String): Flowable<List<String>> {
        val list: List<String> = breedList.filter { it.name == breedName }.first().pictureList

        return Flowable.just( list )
    }

    override fun addBreed(breed: Breed) {
        breedList.add( breed )
    }

    override fun deleteAllBreeds() {
        breedList.clear()
    }

    override fun addPicsByBreed(breedName: String, pics: List<String>) {
        breedList.filter { it.name == breedName }.first().pictureList = pics
    }
}