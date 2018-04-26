package info.juanmendez.breedgallery.ui.services

import android.databinding.BaseObservable
import android.databinding.Bindable
import info.juanmendez.breedgallery.BR
import info.juanmendez.breedgallery.model.Breed

class BreedListObservable : BaseObservable() {

    private var _petList = listOf<Breed>()

    var breedList: List<Breed>
        @Bindable get() = _petList
        set(value){
            _petList = value
            notifyPropertyChanged(BR.breedList)
        }
}