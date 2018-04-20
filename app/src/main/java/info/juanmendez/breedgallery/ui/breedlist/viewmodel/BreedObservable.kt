package info.juanmendez.breedgallery.ui.breedlist.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import info.juanmendez.breedgallery.BR
import info.juanmendez.breedgallery.data.api.models.Breed

/**
 * This is an observable used for recyclerView holder and its view
 */

class BreedObservable : BaseObservable() {
    private var _breed = Breed("")

    var breed: Breed
        @Bindable get() = _breed
        set(value) {
            _breed = value
            notifyPropertyChanged(BR.breed)
        }
}