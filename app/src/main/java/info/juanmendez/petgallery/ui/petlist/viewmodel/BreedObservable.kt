package info.juanmendez.petgallery.ui.petlist.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import info.juanmendez.petgallery.BR
import info.juanmendez.petgallery.api.models.Breed

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