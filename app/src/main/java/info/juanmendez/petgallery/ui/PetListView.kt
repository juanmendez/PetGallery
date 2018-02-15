package info.juanmendez.petgallery.ui

import android.arch.lifecycle.Lifecycle

/**
 * Created by juan on 2/14/18.
 */
interface PetListView {
    fun getLifeCycle():Lifecycle
    fun getPetsObservable():PetsObservable
}