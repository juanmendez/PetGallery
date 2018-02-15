package info.juanmendez.petgallery.ui.petlist.adapter

import android.support.v7.widget.RecyclerView
import info.juanmendez.petgallery.api.PetClientHttp_
import info.juanmendez.petgallery.api.models.Breed
import info.juanmendez.petgallery.databinding.ViewPetItemBinding

/**
 * Created by juan on 2/14/18.
 */
class PetAdapterHolder(var binding:ViewPetItemBinding ): RecyclerView.ViewHolder(binding.root) {
    val petClientHttp = PetClientHttp_.getInstance_( binding.root.context )

    fun setBreed( breed: Breed ){
        binding.breed = breed
    }
}