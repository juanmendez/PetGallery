package info.juanmendez.breedgallery.ui.breedlist.adapter

import android.support.v7.widget.RecyclerView
import com.android.databinding.library.baseAdapters.BR
import info.juanmendez.breedgallery.data.api.BreedCall
import info.juanmendez.breedgallery.data.api.BreedClientHttp
import info.juanmendez.breedgallery.data.api.models.Breed
import info.juanmendez.breedgallery.databinding.ViewPetItemBinding
import info.juanmendez.breedgallery.ui.breedlist.BreedListActivity
import info.juanmendez.breedgallery.ui.breedlist.BreedListContract
import info.juanmendez.breedgallery.ui.breedlist.viewmodel.BreedObservable
import javax.inject.Inject

/**
 * Created by Juan Mendez on 2/14/18.
 */
class BreedItemHolder( var view: BreedListContract.View, var binding: ViewPetItemBinding) : RecyclerView.ViewHolder(binding.root) {
    @Inject
    lateinit var breedClientHttp: BreedClientHttp

    init {
        binding.breedObservable = BreedObservable()
        view.breedListComponent.inject( this )
    }

    fun setBreed(breed: Breed) {

        binding.breedObservable?.breed = breed
        if(breed.pictureList.isEmpty()) {
            breedClientHttp.getPicsByBreed(breed.name, object : BreedCall<List<String>> {
                override fun onResponse(response: List<String>) {
                    breed.pictureList = response
                    binding.breedObservable?.notifyPropertyChanged(BR.breed)
                }

                override fun onError(exception: Exception) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        }
    }
}