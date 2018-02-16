package info.juanmendez.breedgallery.ui.breedlist

import android.arch.lifecycle.Lifecycle
import android.content.Intent
import info.juanmendez.breedgallery.ui.services.BreedListObservable

/**
 * Created by juan on 2/14/18.
 */
interface BreedListView {
    fun getLifeCycle():Lifecycle
    fun getBreadListObservable(): BreedListObservable
    fun startActivity( intent:Intent )
}