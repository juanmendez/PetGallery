package info.juanmendez.petgallery.ui

import android.arch.lifecycle.*
import android.support.v4.app.FragmentActivity
import info.juanmendez.petgallery.api.models.Breed
import org.androidannotations.annotations.EBean
import timber.log.Timber

/**
 * Created by juan on 2/13/18.
 */
@EBean
class PetListPresenter: LifecycleObserver {

    private lateinit var mView:FragmentActivity
    private lateinit var viewModel:PetListViewModel
    private lateinit var observer:Observer<List<Breed>>

    fun register( view:FragmentActivity ):PetListPresenter{
        mView = view
        mView.lifecycle.addObserver(this)
        viewModel = ViewModelProviders.of( mView ).get( PetListViewModel::class.java)

        return this
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){

        observer = Observer<List<Breed>> {
            Timber.i( "on data change " + Thread.currentThread().name )
        }

        viewModel.mutableBreedList.observe(mView, observer)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){

        viewModel.mutableBreedList.removeObserver( observer )
    }
}