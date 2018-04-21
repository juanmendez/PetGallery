package info.juanmendez.breedgallery.data.repository.breed

import com.loumalnatis.android.data.repository.Remote
import info.juanmendez.breedgallery.data.api.BreedCall
import info.juanmendez.breedgallery.data.api.models.Breed
import javax.inject.Inject

class BreedRepository @Inject constructor( @Remote val breedDataSourceRemote: BreedDataSourceRemote): BreedDataSource {
    override fun getBreeds(breedCall: BreedCall<List<Breed>>) {
        return breedDataSourceRemote.getBreeds( breedCall )
    }

    override fun getPicsByBreed(breedName: String, breedCall: BreedCall<List<String>>) {
        return breedDataSourceRemote.getPicsByBreed( breedName, breedCall )
    }
}