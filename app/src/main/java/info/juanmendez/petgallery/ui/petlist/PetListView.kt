package info.juanmendez.petgallery.ui.petlist

import android.arch.lifecycle.Lifecycle
import android.content.Intent
import info.juanmendez.petgallery.ui.services.PetsObservable

/**
 * Created by juan on 2/14/18.
 */
interface PetListView {
    fun getLifeCycle():Lifecycle
    fun getPetsObservable(): PetsObservable
    fun startActivity( intent:Intent )
}