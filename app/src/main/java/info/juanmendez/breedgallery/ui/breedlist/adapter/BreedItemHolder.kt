package info.juanmendez.breedgallery.ui.breedlist.adapter

import android.support.v7.widget.RecyclerView
import com.android.databinding.library.baseAdapters.BR
import info.juanmendez.breedgallery.databinding.ItemPetBinding
import info.juanmendez.breedgallery.model.Breed
import info.juanmendez.breedgallery.ui.breedlist.viewmodel.BreedObservable

class BreedItemHolder(
        var adapter: BreedListAdapter,
        var binding: ItemPetBinding
) : RecyclerView.ViewHolder(binding.root) {


    init {
        binding.breedObservable = BreedObservable()
    }

    fun setBreed(breed: Breed) {
        binding.breedObservable?.breed = breed

        if (breed.pictureList.isEmpty()) {

            adapter.getPicsByBreed(breed.name, {
                breed.pictureList = it
                binding.breedObservable?.notifyPropertyChanged(BR.breed)
            })
        }
    }
}