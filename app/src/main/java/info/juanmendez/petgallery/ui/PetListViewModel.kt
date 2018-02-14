package info.juanmendez.petgallery.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import info.juanmendez.petgallery.api.PetCall
import info.juanmendez.petgallery.api.PetClientHttp
import info.juanmendez.petgallery.api.PetClientHttp_
import info.juanmendez.petgallery.api.models.Breed
import timber.log.Timber

/**
 * Created by juan on 2/13/18.
 */
class PetListViewModel(application:Application): AndroidViewModel(application) {
    private var mPetClientHttp: PetClientHttp = PetClientHttp_.getInstance_( application )
    var liveBreedList = MutableLiveData<List<Breed>>()

    init{
        refresh()
    }

    fun refresh(){
        mPetClientHttp.getBreeds( object: PetCall<List<Breed>> {
            override fun onError(exception: Exception) {
                Timber.e( exception.message )
            }

            override fun onResponse(response: List<Breed>) {
                liveBreedList.value = response
            }
        })
    }
}