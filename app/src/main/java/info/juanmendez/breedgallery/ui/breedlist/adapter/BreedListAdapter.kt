package info.juanmendez.breedgallery.ui.breedlist.adapter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.databinding.Observable
import android.databinding.Observable.OnPropertyChangedCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import info.juanmendez.breedgallery.databinding.ViewPetItemBinding
import info.juanmendez.breedgallery.ui.breedlist.BreedListView

/**
 * Created by juan on 2/14/18.
 */
class BreedListAdapter(private val inflater:LayoutInflater, view: BreedListView): RecyclerView.Adapter<BreedListHolder>(),
                                                                                LifecycleObserver {
    private val mObservable = view.getPetsObservable()
    private lateinit var mCallBack:OnPropertyChangedCallback

    init {
        view.getLifeCycle().addObserver( this )
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BreedListHolder {
        var binding = ViewPetItemBinding.inflate(inflater, parent, false )
        binding.breedListObservable = mObservable
        return BreedListHolder(binding, inflater)
    }

    override fun getItemCount(): Int = mObservable.breedList.size

    override fun onBindViewHolder(holder: BreedListHolder?, position: Int) {
        holder?.setBreed( mObservable.breedList[position] )
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){

        mCallBack = object: OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable?, br: Int) {
                if( br == BR.breedList ){
                    notifyDataSetChanged()
                }
            }
        }

        mObservable.addOnPropertyChangedCallback(mCallBack)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        mObservable.removeOnPropertyChangedCallback(mCallBack)
    }
}