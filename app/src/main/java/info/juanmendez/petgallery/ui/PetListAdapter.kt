package info.juanmendez.petgallery.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.databinding.Observable
import android.databinding.Observable.OnPropertyChangedCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import info.juanmendez.petgallery.databinding.ViewPetitemBinding

/**
 * Created by juan on 2/14/18.
 */
class PetListAdapter(private val inflater:LayoutInflater, view:PetListView): RecyclerView.Adapter<PetAdapterHolder>(),
                                                                                LifecycleObserver {
    private val petsObservable = view.getPetsObservable()
    private lateinit var callBack:OnPropertyChangedCallback

    init {
        view.getLifeCycle().addObserver( this )
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PetAdapterHolder {
        var binding = ViewPetitemBinding.inflate(inflater, parent, false )
        binding.petsObservable = petsObservable
        return PetAdapterHolder(binding )
    }

    override fun getItemCount(): Int = petsObservable.petList.size

    override fun onBindViewHolder(holder: PetAdapterHolder?, position: Int) {
        holder?.setBreed( petsObservable.petList[position] )
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){

        callBack = object: OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable?, br: Int) {
                if( br == BR.petList ){
                    notifyDataSetChanged()
                }
            }
        }

        petsObservable.addOnPropertyChangedCallback(callBack)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        petsObservable.removeOnPropertyChangedCallback( callBack )
    }
}