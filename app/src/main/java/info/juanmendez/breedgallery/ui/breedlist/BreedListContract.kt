package info.juanmendez.breedgallery.ui.breedlist

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.content.Intent
import info.juanmendez.breedgallery.ui.services.BreedListObservable

interface BreedListContract {

    interface View:LifecycleOwner {
        val breedListComponent: BreedListComponent
        fun getLifeCycle(): Lifecycle
        fun getBreadListObservable(): BreedListObservable
        fun startActivity(intent: Intent)
    }

    interface Presenter: LifecycleObserver {
        fun refreshPetList()
    }
}