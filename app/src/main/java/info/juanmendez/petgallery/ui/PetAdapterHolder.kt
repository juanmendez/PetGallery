package info.juanmendez.petgallery.ui

import android.support.v7.widget.RecyclerView
import info.juanmendez.petgallery.api.models.Breed
import info.juanmendez.petgallery.databinding.ViewPetitemBinding

/**
 * Created by juan on 2/14/18.
 */
class PetAdapterHolder(var binding:ViewPetitemBinding ): RecyclerView.ViewHolder(binding.root) {

    fun setBreed( breed: Breed ){
        binding.breed = breed
    }
}