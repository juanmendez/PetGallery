package info.juanmendez.petgallery.ui.petlist.petlist_adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.android.databinding.library.baseAdapters.BR
import info.juanmendez.petgallery.api.PetCall
import info.juanmendez.petgallery.api.PetClientHttp_
import info.juanmendez.petgallery.api.models.Breed
import info.juanmendez.petgallery.databinding.ViewPetItemBinding
import info.juanmendez.petgallery.ui.petlist.viewmodel.PetObservable

/**
 * Created by juan on 2/14/18.
 */
class PetListHolder(var binding:ViewPetItemBinding, private val inflater: LayoutInflater): RecyclerView.ViewHolder(binding.root) {
    private val mHttp = PetClientHttp_.getInstance_( binding.root.context )


    init {
        var linearLayoutManager = LinearLayoutManager( binding.root.context )
        linearLayoutManager.orientation = LinearLayout.HORIZONTAL

        binding.petObservable = PetObservable()
    }

    fun setBreed( breed: Breed ){

        binding.petObservable?.breed = breed
        if( breed.pictureList.isEmpty() ){
            mHttp.getPicsByBreed( breed.name, object:PetCall<List<String>>{
                override fun onResponse(response: List<String>) {
                    breed.pictureList = response
                    binding.petObservable?.notifyPropertyChanged( BR.breed )
                }

                override fun onError(exception: Exception) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        }
    }
}