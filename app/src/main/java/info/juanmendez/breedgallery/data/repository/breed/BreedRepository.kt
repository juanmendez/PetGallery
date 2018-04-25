package info.juanmendez.breedgallery.data.repository.breed

import com.loumalnatis.android.data.repository.Local
import com.loumalnatis.android.data.repository.Remote
import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable
import io.realm.RealmList
import javax.inject.Inject

class BreedRepository @Inject constructor(
    @Remote private val breedDataSourceRemote: BreedDataSource,
    @Local private val breedDataSourceLocal: BreedDataSource
): BreedDataSource {

    override fun getBreeds(): Flowable<List<Breed>> {
        return breedDataSourceLocal.getBreeds()
            .filter { it.isNotEmpty() }
            .switchIfEmpty( doRefresh() )
    }

    override fun getPicsByBreed(breedName: String): Flowable<RealmList<String>> {
        return breedDataSourceRemote.getPicsByBreed(breedName)
    }

    override fun addBreed(breed: Breed) {
        breedDataSourceLocal.addBreed( breed )
    }

    override fun deleteAllBreeds() {
        breedDataSourceLocal.deleteAllBreeds()
    }

    override fun addPicsByBreed(breedName: String, pics: RealmList<String>) {
        breedDataSourceLocal.addPicsByBreed( breedName, pics )
    }

    private fun doRefresh():Flowable<List<Breed>>{

        return breedDataSourceRemote.getBreeds()
            .doOnNext{ breedDataSourceLocal.deleteAllBreeds() }
            .flatMap { Flowable.fromIterable( it ) }
            .doOnNext{
                breedDataSourceLocal.addBreed( it )
            }
            .toList()
            .toFlowable()
    }
}