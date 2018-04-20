package info.juanmendez.breedgallery.ui.breedlist.adapter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.databinding.Observable
import android.databinding.Observable.OnPropertyChangedCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import info.juanmendez.breedgallery.BR
import info.juanmendez.breedgallery.databinding.ItemPetBinding
import info.juanmendez.breedgallery.ui.breedlist.BreedListContract.View

class BreedListAdapter(private val inflater: LayoutInflater, private val view: View) : RecyclerView.Adapter<BreedItemHolder>(), LifecycleObserver {
    private val listObservable = view.getBreadListObservable()
    private lateinit var callback: OnPropertyChangedCallback

    init {
        view.getLifeCycle().addObserver(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BreedItemHolder {
        var binding = ItemPetBinding.inflate(inflater, parent, false)
        binding.breedListObservable = listObservable
        return BreedItemHolder(view, binding)
    }

    override fun getItemCount(): Int = listObservable.breedList.size

    override fun onBindViewHolder(holder: BreedItemHolder?, position: Int) {
        holder?.setBreed(listObservable.breedList[position])
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {

        callback = object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable?, br: Int) {
                if(br == BR.breedList) {
                    notifyDataSetChanged()
                }
            }
        }

        listObservable.addOnPropertyChangedCallback(callback)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        listObservable.removeOnPropertyChangedCallback(callback)
    }
}