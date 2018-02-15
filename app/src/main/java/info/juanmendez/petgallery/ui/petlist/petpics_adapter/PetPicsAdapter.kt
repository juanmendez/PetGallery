package info.juanmendez.petgallery.ui.petlist.petpics_adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import info.juanmendez.petgallery.R

/**
 * Created by juan on 2/15/18.
 */
class PetPicsAdapter(private val inflater: LayoutInflater, view:View): RecyclerView.Adapter<PetPicHolder>() {
    private var mPics = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PetPicHolder {
        var view:View = inflater.inflate( R.layout.view_pet_item, parent, false )

        return PetPicHolder( view )
    }

    override fun getItemCount(): Int = mPics.size

    override fun onBindViewHolder(holder: PetPicHolder?, position: Int) {
        holder?.displayPicture( mPics[position] )
    }

    fun refresh(pics: List<String>) {
        mPics = pics
        notifyDataSetChanged()
    }
}