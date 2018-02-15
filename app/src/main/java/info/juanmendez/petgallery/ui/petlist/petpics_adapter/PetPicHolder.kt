package info.juanmendez.petgallery.ui.petlist.petpics_adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import timber.log.Timber

/**
 * Created by juan on 2/15/18.
 */
class PetPicHolder(view:View): RecyclerView.ViewHolder(view) {

    fun displayPicture( picture:String ){
        Timber.i( picture )
    }
}