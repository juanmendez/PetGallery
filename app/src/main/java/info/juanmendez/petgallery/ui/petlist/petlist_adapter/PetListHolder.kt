package info.juanmendez.petgallery.ui.petlist.petlist_adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.LinearLayout
import info.juanmendez.petgallery.BR
import info.juanmendez.petgallery.R
import info.juanmendez.petgallery.api.PetCall
import info.juanmendez.petgallery.api.PetClientHttp_
import info.juanmendez.petgallery.api.models.Breed
import info.juanmendez.petgallery.databinding.ViewPetItemBinding
import info.juanmendez.petgallery.ui.petlist.petpics_adapter.PetPicsAdapter

/**
 * Created by juan on 2/14/18.
 */
class PetListHolder(var binding:ViewPetItemBinding, private val inflater: LayoutInflater): RecyclerView.ViewHolder(binding.root) {
    val petClientHttp = PetClientHttp_.getInstance_( binding.root.context )
    val petListRecyclerView:RecyclerView = binding.root.findViewById( R.id.petlist_rv )
    val adapter = PetPicsAdapter(inflater, binding.root)

    init {
        var linearLayoutManager = LinearLayoutManager( binding.root.context )
        linearLayoutManager.orientation = LinearLayout.HORIZONTAL
        petListRecyclerView.layoutManager = linearLayoutManager
        petListRecyclerView.adapter = adapter
    }

    fun setBreed( breed: Breed ){
        binding.breed = breed

        if( breed.pictureList.isEmpty() ){
            petClientHttp.getPicsByBreed( breed.name, object:PetCall<List<String>>{
                override fun onResponse(response: List<String>) {
                    breed.pictureList = response
                    binding.notifyPropertyChanged( BR.petList )
                    adapter.refresh( response.take(3))
                }

                override fun onError(exception: Exception) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        }
    }
}