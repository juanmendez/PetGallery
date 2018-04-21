package info.juanmendez.breedgallery.data.repository.breed

import info.juanmendez.breedgallery.data.api.BreedCall
import info.juanmendez.breedgallery.data.api.models.Breed

class BreedDataSourceLocal: BreedDataSource {

    override fun getBreeds(breedCall: BreedCall<List<Breed>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPicsByBreed(breedName: String, breedCall: BreedCall<List<String>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}