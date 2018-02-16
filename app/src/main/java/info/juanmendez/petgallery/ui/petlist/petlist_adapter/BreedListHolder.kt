package info.juanmendez.petgallery.ui.petlist.petlist_adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.android.databinding.library.baseAdapters.BR
import info.juanmendez.petgallery.api.BreedCall
import info.juanmendez.petgallery.api.BreedClientHttp_
import info.juanmendez.petgallery.api.models.Breed
import info.juanmendez.petgallery.databinding.ViewPetItemBinding
import info.juanmendez.petgallery.ui.petlist.viewmodel.BreedObservable

/**
 * Created by juan on 2/14/18.
 */
class BreedListHolder(var binding:ViewPetItemBinding, private val inflater: LayoutInflater): RecyclerView.ViewHolder(binding.root) {
    private val mHttp = BreedClientHttp_.getInstance_( binding.root.context )


    init {
        var linearLayoutManager = LinearLayoutManager( binding.root.context )
        linearLayoutManager.orientation = LinearLayout.HORIZONTAL

        binding.breedObservable = BreedObservable()
    }

    fun setBreed( breed: Breed ){

        binding.breedObservable?.breed = breed
        if( breed.pictureList.isEmpty() ){
            mHttp.getPicsByBreed( breed.name, object: BreedCall<List<String>>{
                override fun onResponse(response: List<String>) {
                    breed.pictureList = response
                    binding.breedObservable?.notifyPropertyChanged( BR.breed )
                }

                override fun onError(exception: Exception) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        }
    }
}