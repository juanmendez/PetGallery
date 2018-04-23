package info.juanmendez.breedgallery.data.database

import android.content.Context
import info.juanmendez.breedgallery.data.repository.breed.BreedDataSource
import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable
import javax.inject.Inject

class BreedRealm @Inject constructor( val context:Context ):BreedDataSource{

    override fun addBreed(breed: Breed) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBreeds(): Flowable<List<Breed>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAllBreeds() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addPicsByBreed(breed: String, pics: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPicsByBreed(breedName: String): Flowable<List<String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}