package info.juanmendez.breedgallery.ui.breedlist

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import info.juanmendez.breedgallery.data.repository.breed.BreedRepository
import info.juanmendez.breedgallery.data.schedulers.RunOn
import info.juanmendez.breedgallery.data.schedulers.SchedulerType
import info.juanmendez.breedgallery.ui.breedlist.BreedListContract.Presenter
import info.juanmendez.breedgallery.ui.breedlist.BreedListContract.View
import javax.inject.Inject

class BreedListPresenter @Inject constructor(
    val view:View,
    val breedRepository: BreedRepository,
    @RunOn(SchedulerType.COMPUTATION) val computationScheduler: io.reactivex.Scheduler,
    @RunOn(SchedulerType.UI) val uiScheduler: io.reactivex.Scheduler

) : Presenter {

    init {
        view.getLifeCycle().addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {

        /**
         * ViewModel has a list of breeds, so initially we detect
         * if we need to go and fetch the content
         */
        if(view.getBreadListObservable().breedList.isEmpty()) {
            refreshPetList()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
    }

    override fun refreshPetList() {

        //gotcha, app broke due to a late call from its view while being destroyed
        if(!view.getLifeCycle().currentState.equals(Lifecycle.State.RESUMED))
            return



        breedRepository.getBreeds().subscribeOn( computationScheduler )
            .observeOn(uiScheduler)
            .bindToLifecycle( view )
            .subscribe{
                view.getBreadListObservable().breedList = it
            }
    }
}