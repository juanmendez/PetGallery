package info.juanmendez.petgallery.ui.petlist

import android.arch.lifecycle.Lifecycle
import android.content.Intent
import info.juanmendez.petgallery.ui.services.PetListObservable

/**
 * Created by juan on 2/14/18.
 */
interface PetListView {
    fun getLifeCycle():Lifecycle
    fun getPetsObservable(): PetListObservable
    fun startActivity( intent:Intent )
}