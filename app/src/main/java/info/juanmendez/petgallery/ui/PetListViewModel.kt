package info.juanmendez.petgallery.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import info.juanmendez.petgallery.api.models.Breed

/**
 * Created by juan on 2/13/18.
 */
class PetListViewModel(application:Application): AndroidViewModel(application) {
    var mutableBreedList = MutableLiveData<List<Breed>>()
}