package info.juanmendez.breedgallery.data.repository.breed

import info.juanmendez.breedgallery.data.api.BreedService
import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable
import javax.inject.Inject

class BreedDataSourceRemote @Inject constructor( val breedService: BreedService ) : BreedDataSource {

    override fun getBreeds(): Flowable<List<Breed>> {

        return breedService.getBreedList().map { it.message }
            .flatMap{  Flowable.fromIterable(it) }
            .filter{ it.isNotEmpty() }
            .map { Breed(it) }
            .toList()
            .toFlowable()
    }

    override fun getPicsByBreed(breedName: String): Flowable<List<String>> {

        return breedService.getPicsByBreed(breedName).map { it.message }
            .filter{ it.isNotEmpty() }
    }
}