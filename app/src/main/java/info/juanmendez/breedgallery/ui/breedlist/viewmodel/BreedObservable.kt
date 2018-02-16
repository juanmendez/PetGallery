package info.juanmendez.breedgallery.ui.breedlist.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import info.juanmendez.breedgallery.BR
import info.juanmendez.breedgallery.api.models.Breed

/**
 * Created by juan on 2/15/18.
 */
class BreedObservable : BaseObservable() {
    private var _breed = Breed("")

    var breed:Breed
        @Bindable get() = _breed
        set(value) {
            _breed = value
            notifyPropertyChanged( BR.breed )
        }
}