package info.juanmendez.breedgallery.ui.breedlist.adapter

import android.support.v7.widget.RecyclerView
import info.juanmendez.breedgallery.model.Breed
import info.juanmendez.breedgallery.data.repository.breed.BreedDataSourceRemote
import info.juanmendez.breedgallery.data.repository.breed.BreedRepository
import info.juanmendez.breedgallery.databinding.ItemPetBinding
import info.juanmendez.breedgallery.ui.breedlist.BreedListContract
import info.juanmendez.breedgallery.ui.breedlist.viewmodel.BreedObservable
import javax.inject.Inject

class BreedItemHolder( var view: BreedListContract.View, var binding: ItemPetBinding) : RecyclerView.ViewHolder(binding.root) {
    @Inject
    lateinit var breedDataSourceRemote: BreedRepository

    init {
        binding.breedObservable = BreedObservable()
        view.breedListComponent.inject( this )
    }

    fun setBreed(breed: Breed) {
        binding.breedObservable?.breed = breed
        if(breed.pictureList.isEmpty()) {
            /*breedDataSourceRemote.getPicsByBreed(breed.name, object : BreedCall<List<String>> {
                override fun onResponse(response: List<String>) {
                    breed.pictureList = response
                    binding.breedObservable?.notifyPropertyChanged(BR.breed)
                }

                override fun onError(exception: Exception) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })*/
        }
    }
}