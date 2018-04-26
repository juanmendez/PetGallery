package info.juanmendez.breedgallery.ui.breedlist.adapter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.databinding.Observable
import android.databinding.Observable.OnPropertyChangedCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import info.juanmendez.breedgallery.BR
import info.juanmendez.breedgallery.data.repository.breed.BreedRepository
import info.juanmendez.breedgallery.databinding.ItemPetBinding
import info.juanmendez.breedgallery.services.NetworkService
import info.juanmendez.breedgallery.ui.breedlist.BreedListContract.View
import info.juanmendez.breedgallery.utils.schedulers.RunOn
import info.juanmendez.breedgallery.utils.schedulers.SchedulerType
import io.reactivex.disposables.Disposable
import io.reactivex.internal.operators.flowable.FlowableSubscribeOn
import io.realm.RealmList
import java.util.function.Consumer
import javax.inject.Inject

class BreedListAdapter(private val inflater: LayoutInflater, private val view: View) : RecyclerView.Adapter<BreedItemHolder>(), LifecycleObserver {
    private val listObservable = view.getBreadListObservable()
    private lateinit var callback: OnPropertyChangedCallback

    @Inject lateinit var breedRepository: BreedRepository
    @Inject @field:RunOn(SchedulerType.COMPUTATION) lateinit var computationScheduler: io.reactivex.Scheduler
    @Inject @field:RunOn(SchedulerType.UI) lateinit var uiScheduler: io.reactivex.Scheduler

    init {
        view.getLifeCycle().addObserver(this)
        view.breedListComponent.inject( this )
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BreedItemHolder {
        var binding = ItemPetBinding.inflate(inflater, parent, false)
        binding.breedListObservable = listObservable
        return BreedItemHolder(this, binding)
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

    fun getPicsByBreed( breedName:String, consumer: (RealmList<String>)->Unit  ){

        breedRepository.getPicsByBreed(breedName)
            .subscribeOn( computationScheduler )
            .observeOn(uiScheduler)
            .bindToLifecycle( view )
            .subscribe(consumer)
    }
}