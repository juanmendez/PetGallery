package info.juanmendez.breedgallery.ui.breedlist

import android.arch.lifecycle.Lifecycle
import android.content.Intent
import info.juanmendez.breedgallery.ui.services.BreedListObservable

/**
 * Created by Juan Mendez on 2/14/18.
 * This interface is intended for a presenter to decouple association with an Activity or a Fragment
 */
interface BreedListView {
    fun getLifeCycle(): Lifecycle
    fun getBreadListObservable(): BreedListObservable
    fun startActivity(intent: Intent)
}