package info.juanmendez.breedgallery.ui.breedlist.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import info.juanmendez.breedgallery.BR
import info.juanmendez.breedgallery.ui.breedlist.models.Breed

/**
 * Created by Juan Mendez on 2/15/18.
 *
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