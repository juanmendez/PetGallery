package info.juanmendez.breedgallery.ui.services

import android.arch.lifecycle.ViewModel

/**
 * Created by Juan Mendez on 2/13/18.
 * viewModel helps the breedListView to retain its data instead of having to pull it on each rotation
 */
class BreedListVM : ViewModel() {

    /**
     * https://stackoverflow.com/a/47930515
     * This demo embeds the bindingObservable instead of using liveData
     */
    var breedListObservable = BreedListObservable()
}