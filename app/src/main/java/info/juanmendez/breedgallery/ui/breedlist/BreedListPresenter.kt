package info.juanmendez.breedgallery.ui.breedlist

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import info.juanmendez.breedgallery.data.repository.breed.BreedDataSourceRemote
import info.juanmendez.breedgallery.data.repository.breed.BreedRepository
import info.juanmendez.breedgallery.data.schedulers.RunOn
import info.juanmendez.breedgallery.data.schedulers.SchedulerType
import info.juanmendez.breedgallery.ui.breedlist.BreedListContract.Presenter
import info.juanmendez.breedgallery.ui.breedlist.BreedListContract.View
import javax.inject.Inject

class BreedListPresenter @Inject constructor(
    val listView:View,
    val http: BreedRepository,
    @RunOn(SchedulerType.COMPUTATION) val computationScheduler: io.reactivex.Scheduler,
    @RunOn(SchedulerType.UI) val uiScheduler: io.reactivex.Scheduler

) : Presenter {

    init {
        listView.getLifeCycle().addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {

        /**
         * ViewModel has a list of breeds, so initially we detect
         * if we need to go and fetch the content
         */
        if(listView.getBreadListObservable().breedList.isEmpty()) {
            refreshPetList()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
    }

    override fun refreshPetList() {

        //gotcha, app broke due to a late call from its view while being destroyed
        if(!listView.getLifeCycle().currentState.equals(Lifecycle.State.RESUMED))
            return



        http.getBreeds().subscribeOn( computationScheduler )
            .observeOn(uiScheduler)
            .subscribe{
                listView.getBreadListObservable().breedList = it
            }

        /*http.getBreeds(object : BreedCall<List<Breed>> {
            override fun onError(exception: Exception) {
                //TODO: implement an error display in the View
                Timber.e(exception.message)
            }

            override fun onResponse(response: List<Breed>) {
                listView.getBreadListObservable().breedList = response
            }
        })*/
    }
}