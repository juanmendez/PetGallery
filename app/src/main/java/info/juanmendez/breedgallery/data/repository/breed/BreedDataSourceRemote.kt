package info.juanmendez.breedgallery.data.repository.breed

import info.juanmendez.breedgallery.data.api.BreedService
import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable
import io.realm.RealmList
import javax.inject.Inject

class BreedDataSourceRemote @Inject constructor(val breedService: BreedService) : BreedDataSource {

    override fun getBreeds(forceRemote: Boolean): Flowable<List<Breed>> {

        return breedService.getBreedList().map { it.message }.flatMap { Flowable.fromIterable(it) }
                .filter { it.isNotEmpty() }.map { Breed(it) }.toList().toFlowable()
    }

    override fun getPicsByBreed(breedName: String): Flowable<RealmList<String>> {

        return breedService.getPicsByBreed(breedName).map { it.message }.filter { it.isNotEmpty() }
                .map {
                    RealmList<String>().apply { this.addAll(it) }
                }
    }

    override fun addBreed(breed: Breed) {
        throw UnsupportedOperationException("Unsupported operation")
    }

    override fun deleteAllBreeds() {
        throw UnsupportedOperationException("Unsupported operation")
    }

    override fun addPicsByBreed(breed: String, pics: RealmList<String>) {
        throw UnsupportedOperationException("Unsupported operation")
    }
}