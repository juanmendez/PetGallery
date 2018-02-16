package info.juanmendez.petgallery.ui.petlist

import android.arch.lifecycle.Lifecycle
import android.content.Intent
import info.juanmendez.petgallery.ui.services.BreedListObservable

/**
 * Created by juan on 2/14/18.
 */
interface BreedListView {
    fun getLifeCycle():Lifecycle
    fun getPetsObservable(): BreedListObservable
    fun startActivity( intent:Intent )
}