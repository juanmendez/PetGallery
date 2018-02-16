package info.juanmendez.breedgallery.ui.services

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

/**
 * Created by Juan Mendez on 2/13/18.
 * viewModel helps the breedListView to retain its data instead of having to pull it on each rotation
 */
class BreedListVM(application:Application): AndroidViewModel(application) {

    /**
     * https://stackoverflow.com/a/47930515
     * This demo embeds the bindingObservable instead of using liveData
     */
    var breedListObservable = BreedListObservable()
}