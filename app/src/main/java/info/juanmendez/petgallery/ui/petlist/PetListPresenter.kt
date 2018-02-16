package info.juanmendez.petgallery.ui.petlist

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import info.juanmendez.petgallery.api.PetCall
import info.juanmendez.petgallery.api.PetClientHttp
import info.juanmendez.petgallery.api.models.Breed
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean
import timber.log.Timber

/**
 * Created by juan on 2/13/18.
 */
@EBean
class PetListPresenter: LifecycleObserver {

    private lateinit var mView: PetListView

    @Bean
    lateinit var mHttp: PetClientHttp

    fun register( view: PetListView): PetListPresenter {
        mView = view
        mView.getLifeCycle().addObserver(this)

        return this
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){

        if( mView.getPetsObservable().petList.isEmpty() ){
            refreshPetList()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){

    }

    fun refreshPetList(){

        mHttp.getBreeds( object: PetCall<List<Breed>> {
            override fun onError(exception: Exception) {
                Timber.e( exception.message )
            }

            override fun onResponse(response: List<Breed>) {
                mView.getPetsObservable().petList = response
            }
        })
    }
}