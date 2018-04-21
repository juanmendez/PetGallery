package info.juanmendez.breedgallery.data.repository.breed

import com.loumalnatis.android.data.repository.Remote
import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable
import javax.inject.Inject

class BreedRepository @Inject constructor( @Remote val breedDataSourceRemote: BreedDataSourceRemote): BreedDataSource {
    override fun getBreeds(): Flowable<List<Breed>> {
        return breedDataSourceRemote.getBreeds()
    }

    override fun getPicsByBreed(breedName: String): Flowable<List<String>> {
        return breedDataSourceRemote.getPicsByBreed(breedName)
    }
}