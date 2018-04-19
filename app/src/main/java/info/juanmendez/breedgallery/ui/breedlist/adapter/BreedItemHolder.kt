package info.juanmendez.breedgallery.ui.breedlist.adapter

import android.support.v7.widget.RecyclerView
import com.android.databinding.library.baseAdapters.BR
import info.juanmendez.breedgallery.api.BreedCall
import info.juanmendez.breedgallery.api.BreedClientHttp_
import info.juanmendez.breedgallery.api.models.Breed
import info.juanmendez.breedgallery.databinding.ViewPetItemBinding
import info.juanmendez.breedgallery.ui.breedlist.viewmodel.BreedObservable

/**
 * Created by Juan Mendez on 2/14/18.
 */
class BreedItemHolder(var binding: ViewPetItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val mHttp = BreedClientHttp_.getInstance_(binding.root.context)

    init {
        binding.breedObservable = BreedObservable()
    }

    fun setBreed(breed: Breed) {

        binding.breedObservable?.breed = breed
        if(breed.pictureList.isEmpty()) {
            mHttp.getPicsByBreed(breed.name, object : BreedCall<List<String>> {
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