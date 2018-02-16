package info.juanmendez.petgallery.ui.services

import android.databinding.BaseObservable
import android.databinding.Bindable
import info.juanmendez.petgallery.BR
import info.juanmendez.petgallery.api.models.Breed

/**
 * Created by juan on 2/14/18.
 */
class PetListObservable : BaseObservable() {
    private var _petList = listOf<Breed>()

    var petList:List<Breed>
    @Bindable get() = _petList
    set(value) {
        _petList = value
        notifyPropertyChanged( BR.petList )
    }
}