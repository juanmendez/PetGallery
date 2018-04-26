package info.juanmendez.breedgallery.data.repository.breed

import com.loumalnatis.android.data.repository.Local
import com.loumalnatis.android.data.repository.Remote
import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable
import io.realm.RealmList
import javax.inject.Inject

class BreedRepository @Inject constructor(
    @Remote private val breedDataSourceRemote: BreedDataSource, @Local private val breedDataSourceLocal: BreedDataSource
) : BreedDataSource {

    override fun getBreeds(forceRemote: Boolean): Flowable<List<Breed>> {

        return if(forceRemote) {
            doRefreshBreeds()
        } else {
            breedDataSourceLocal.getBreeds(forceRemote).flatMap {
                if(it.isEmpty()) {
                    doRefreshBreeds()
                } else {
                    Flowable.just(it)
                }
            }
        }
    }

    override fun getPicsByBreed(breedName: String): Flowable<RealmList<String>> {

        return breedDataSourceLocal.getPicsByBreed(breedName).flatMap {
            if(it.isEmpty()) {
                doRefreshPics(breedName)
            } else {
                Flowable.just(it)
            }
        }
    }

    override fun addBreed(breed: Breed) {
        breedDataSourceLocal.addBreed(breed)
    }

    override fun deleteAllBreeds() {
        breedDataSourceLocal.deleteAllBreeds()
    }

    override fun addPicsByBreed(breedName: String, pics: RealmList<String>) {
        breedDataSourceLocal.addPicsByBreed(breedName, pics)
    }

    private fun doRefreshBreeds(): Flowable<List<Breed>> {

        return breedDataSourceRemote.getBreeds(true)
            .doOnNext { breedDataSourceLocal.deleteAllBreeds() }
            .flatMap { Flowable.fromIterable(it) }.doOnNext {
                breedDataSourceLocal.addBreed(it)
            }.toList().toFlowable()
    }

    private fun doRefreshPics(breedName: String): Flowable<RealmList<String>> {
        return breedDataSourceRemote.getPicsByBreed(breedName).doOnNext {
            breedDataSourceLocal.addPicsByBreed(breedName, it)
        }
    }
}