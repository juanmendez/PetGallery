package info.juanmendez.petgallery.ui.petlist.petlist_adapter

import android.support.v7.widget.RecyclerView
import info.juanmendez.petgallery.BR
import info.juanmendez.petgallery.api.PetCall
import info.juanmendez.petgallery.api.PetClientHttp_
import info.juanmendez.petgallery.api.models.Breed
import info.juanmendez.petgallery.databinding.ViewPetItemBinding

/**
 * Created by juan on 2/14/18.
 */
class PetListHolder(var binding:ViewPetItemBinding ): RecyclerView.ViewHolder(binding.root) {
    val petClientHttp = PetClientHttp_.getInstance_( binding.root.context )

    fun setBreed( breed: Breed ){
        binding.breed = breed

        if( breed.pictureList.isEmpty() ){
            petClientHttp.getPicsByBreed( breed.name, object:PetCall<List<String>>{
                override fun onResponse(response: List<String>) {
                    breed.pictureList = response
                    binding.notifyPropertyChanged( BR.petList )
                }

                override fun onError(exception: Exception) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        }
    }
}