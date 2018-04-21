package info.juanmendez.breedgallery.data.repository.breed

import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable

class BreedDataSourceLocal: BreedDataSource {
    override fun getBreeds(): Flowable<List<Breed>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPicsByBreed(breedName: String): Flowable<List<String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}