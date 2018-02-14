package info.juanmendez.petgallery.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import info.juanmendez.petgallery.api.PetCall
import info.juanmendez.petgallery.api.PetHTTPClient
import info.juanmendez.petgallery.api.models.Breed
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean
import timber.log.Timber

/**
 * Created by juan on 2/13/18.
 */
@EBean
class PetListPresenter: LifecycleObserver {

    @Bean
    lateinit var petHttpClient: PetHTTPClient

    private lateinit var viewModel:PetListViewModel

    fun register( view:FragmentActivity ):PetListPresenter{
        view.lifecycle.addObserver(this)
        viewModel = ViewModelProviders.of( view ).get( PetListViewModel::class.java)

        return this
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){

        petHttpClient.getBreeds( object:PetCall<List<Breed>>{
            override fun onError(exception: Exception) {
                Timber.i( exception.message )
            }

            override fun onResponse(response: List<Breed>) {
                Timber.i( response.toString() )

                viewModel.mutableBreedList
            }

        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
    }
}