package info.juanmendez.petgallery.ui.services

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

/**
 * Created by juan on 2/13/18.
 */
class BreedListVM(application:Application): AndroidViewModel(application) {
    var petListObservable = BreedListObservable()
}